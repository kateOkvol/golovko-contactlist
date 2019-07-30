package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dto.PhoneNumberDTO;
import services.PhoneNumberService;
import utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class NumberController {
    private final ControllerUtils util;


    public NumberController() {
        this.util = new ControllerUtils();
    }


    public void createPhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PhoneNumberDTO[] dto = new ObjectMapper().convertValue(
                util.prepareToDTO(request), PhoneNumberDTO[].class);;
        for (PhoneNumberDTO phone : dto) {
            new PhoneNumberService(phone).createPhoneNumber();
        }
        response.setStatus(200);
    }

    public void deletePhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList array = mapper.convertValue(util.prepareToDTO(request), ArrayList.class);
        PhoneNumberService service = new PhoneNumberService();
        for (Object element : array) {
            service.deletePhoneNumber(Integer.parseInt((String) element));
        }
        response.setStatus(200);
    }

    public void getPhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PhoneNumberService service = new PhoneNumberService();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        Integer id = util.processId("id", request);

        response.getWriter().write(
                mapper.writeValueAsString(
                        service.getById(id)));
    }

    public void updatePhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PhoneNumberDTO[] dto = new ObjectMapper().convertValue(
                util.prepareToDTO(request), PhoneNumberDTO[].class);
        for (PhoneNumberDTO phone : dto) {
            new PhoneNumberService(phone).updatePhoneNumber();
        }
        response.setStatus(200);
    }

}
