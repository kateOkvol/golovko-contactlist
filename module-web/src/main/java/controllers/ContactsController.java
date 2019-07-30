package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ContactDTO;
import services.ContactService;
import utils.ControllerUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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

    public void updateContact(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ContactDTO dto = new ObjectMapper().convertValue(
                util.prepareToDTO(request), ContactDTO.class);
        new ContactService(dto).updateContact();
    }

    public void deleteContacts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filePath = properties.getProperty("file_path");
        ContactService service = new ContactService();
        ObjectMapper mapper = new ObjectMapper();
        ArrayList array = mapper.convertValue(util.prepareToDTO(request), ArrayList.class);
        for (Object element : array) {
            List<String> files = service.deleteContact(Integer.parseInt((String) element));
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
        String path = service.getById(id).getAvatar();
        response.getWriter().write(
                mapper.writeValueAsString(
                        service.getDto()));
    }

    public void uploadAvatar(HttpServletRequest request, HttpServletResponse response) {
        String path = properties.getProperty("ava_upload_path");
        util.uploadAttach(path, request, response);
    }

    public void getAvatar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = properties.getProperty("ava_upload_path");
        Integer id = util.processId("id", request);
        String avatar = new ContactService().getById(id).getAvatar();
        String type = avatar.split(".+\\.", 2)[1];
        String fileName = path + avatar;
        BufferedImage image = ImageIO.read(new File(fileName));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(image, type, stream);
        byte[] array = stream.toByteArray();
        response.getWriter().write(
                new ObjectMapper().writeValueAsString(array));
    }
}
