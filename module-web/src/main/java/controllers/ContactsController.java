package controllers;

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
        int id = service.createContact();
        service.setAvatar(dto.getAvatar(), id);
        String jsonString = "[" + id + "]";
        response.getWriter().write(jsonString);
        System.out.println(jsonString);
    }

    public void updateContact(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ContactDTO dto = new ObjectMapper().convertValue(
                util.prepareToDTO(request), ContactDTO.class);
        new ContactService(dto).updateContact();
    }

    public void deleteContacts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filePath = properties.getProperty("file_path");
        String avaPath = properties.getProperty("ava_upload_path");
        ContactService service = new ContactService();
        ObjectMapper mapper = new ObjectMapper();
        ArrayList array = mapper.convertValue(util.prepareToDTO(request), ArrayList.class);
        for (Object element : array) {
            Integer id = Integer.parseInt((String) element);
            List<String> files = service.deleteContact(id);
            new File(avaPath + files.get(files.size()-1)).delete();
            files.remove(files.size()-1);
            for (String path : files) {
                new File(filePath + path).delete();
            }
        }
        response.setStatus(200);
    }

    public void getContact(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Integer id = util.processId("id", request);
        ContactService service = new ContactService();
        response.getWriter().write(
                mapper.writeValueAsString(
                        service.getById(id)));
    }

    public void uploadAvatar(HttpServletRequest request, HttpServletResponse response) {
        String path = properties.getProperty("ava_upload_path");
        util.uploadAttach(path, request, response);
    }

    public void getAvatar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = properties.getProperty("ava_upload_path");
        Integer id = util.processId("id", request);
        String avatar = new ContactService().getById(id).getAvatar();
        util.downloadImg(avatar, path, response);
    }
}
