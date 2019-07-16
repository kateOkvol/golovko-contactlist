package controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import controllers.utils.ControllerUtils;
import dto.PhoneNumberDTO;
import services.PhoneNumberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class NumberController {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private final ControllerUtils<PhoneNumberDTO> util;


    public NumberController() {
        this.util = new ControllerUtils<>(PhoneNumberDTO.class);
    }

    public NumberController(HttpServletRequest request){
        this.request = request;
        this.util = new ControllerUtils<>(PhoneNumberDTO.class);
    }

    public NumberController(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.util = new ControllerUtils<>(PhoneNumberDTO.class);
    }

    public void createPhone() throws IOException {
        PhoneNumberDTO[] dto = parseIntoPhoneDTO(request);
        for (PhoneNumberDTO phone : dto) {
            new PhoneNumberService(phone).createPhoneNumber();
        }
    }

    public void deletePhone() throws IOException {
        String jsonString = util.processRequest(request);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonString);
        System.out.println(jsonNode);
        ArrayList array = mapper.convertValue(jsonNode, ArrayList.class);

        PhoneNumberService service = new PhoneNumberService();

        for (Object element : array) {
            service.deletePhoneNumber(Integer.parseInt((String) element));
        }
    }

    public void getPhone() throws IOException {
        PhoneNumberService service = new  PhoneNumberService();
        String jsonString = util.processRequest(request);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        JsonNode jsonNode = mapper.readTree(jsonString);
        System.out.println(jsonNode.get("id").asInt());

        response.getWriter().write(
                mapper.writeValueAsString(
                        service.getById(
                                jsonNode.get("id").asInt())));
    }

    public void updatePhone() throws IOException {
        PhoneNumberDTO[] dto = parseIntoPhoneDTO(request);
        for (PhoneNumberDTO phone : dto) {
            new PhoneNumberService(phone).updatePhoneNumber();
        }
    }

    private PhoneNumberDTO[] parseIntoPhoneDTO(HttpServletRequest request) throws IOException {
        String jsonString = util.processRequest(request);

        System.out.println(jsonString);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        JsonNode jsonNode = mapper.readTree(jsonString);

        return mapper.convertValue(jsonNode, PhoneNumberDTO[].class);
    }
}
