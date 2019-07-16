package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import controllers.utils.ControllerUtils;
import dto.ContactDTO;
import services.ContactService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ContactsController {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private final ControllerUtils<ContactDTO> util;


    public ContactsController() {
        this.util = new ControllerUtils<>(ContactDTO.class);
    }

    public ContactsController(HttpServletRequest request){
        this.request = request;
        this.util = new ControllerUtils<>(ContactDTO.class);
    }

    public ContactsController(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.util = new ControllerUtils<>(ContactDTO.class);
    }

    public void createContact() throws IOException {
        ContactDTO dto = util.parseIntoDTO(request);
        ContactService service = new ContactService(dto);
        service.createContact();
        String jsonString = "["+dto.getId()+"]";
        response.getWriter().write(jsonString);
        System.out.println(jsonString);
    }

    public void updateContact() throws IOException {
        ContactDTO dto = util.parseIntoDTO(request);
        new ContactService(dto).updateContact();
    }

    public void deleteContact() throws IOException {
        String jsonString = util.processRequest(request);
        ContactService service = new ContactService();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonString);
        System.out.println(jsonNode);
        ArrayList array = mapper.convertValue(jsonNode, ArrayList.class);

        for (Object element : array) {
            service.deleteContact(Integer.parseInt((String) element));
        }
    }

    public void getContact() throws IOException {
        String jsonString = util.processRequest(request);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        JsonNode jsonNode = mapper.readTree(jsonString);
        System.out.println(jsonNode.get("id").asInt());

        response.getWriter().write(
                mapper.writeValueAsString(
                        new ContactService().getById(
                                jsonNode.get("id").asInt())));
    }


}
