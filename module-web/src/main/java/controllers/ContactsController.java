package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ContactDTO;
import services.ContactService;
import utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ContactsController {
    private final ControllerUtils util;
    private Properties properties = new Properties();


    public ContactsController() {
        this.util = new ControllerUtils();
        try {
            this.properties.load(AttachmentController.class.getClassLoader().getResourceAsStream("config-web.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createContact(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ContactDTO dto = new ObjectMapper().convertValue(
                util.prepareToDTO(request), ContactDTO.class);
        ContactService service = new ContactService(dto);
        service.createContact();
        String jsonString = "[" + dto.getId() + "]";
        response.getWriter().write(jsonString);
        System.out.println(jsonString);
    }

    public void updateContact(HttpServletRequest request) throws IOException {
        ContactDTO dto = new ObjectMapper().convertValue(
                util.prepareToDTO(request), ContactDTO.class);
        new ContactService(dto).updateContact();
    }

    public void deleteContact(HttpServletRequest request) throws IOException {
        ContactService service = new ContactService();
        ObjectMapper mapper = new ObjectMapper();

        String jsonString = util.processRequest(request);
        JsonNode jsonNode = mapper.readTree(jsonString);
        System.out.println(jsonNode);
        ArrayList array = mapper.convertValue(jsonNode, ArrayList.class);

        String filePath = properties.getProperty("file_path");
        for (Object element : array) {
            List<String> files = service.deleteContact(Integer.parseInt((String) element));
            for (String path : files
            ) {
                new File(filePath + path).delete();
            }
        }
    }

    public void getContact(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Integer id = util.processId("id", request);
        ContactService service = new ContactService();
        String path = service.getById(id).getAvatar();
        if (path == null) {
            service.getDto().setAvatar(properties.getProperty("ava_path") + "noAva.jpg");
        } else {
            service.getDto().setAvatar(properties.getProperty("ava_path") + path);
        }
        response.getWriter().write(
                mapper.writeValueAsString(
                        service.getDto()));
    }

    public void uploadAvatar(HttpServletRequest request, HttpServletResponse response){
        String path = properties.getProperty("ava_upload_path");
        util.uploadAttach(path, request, response);
    }

//    public void getAvatar(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        ContactService service = new ContactService();
//        ObjectMapper mapper = new ObjectMapper();
//        Integer id = util.processId("id", request);
//        String filePath = properties.getProperty("ava_path") + "noAva.jpg";//service.getById(id).getAvatar();
//        response.getWriter().write(mapper.writeValueAsString(filePath));//util.writeAttachResponse(filePath, response);
//    }
}
