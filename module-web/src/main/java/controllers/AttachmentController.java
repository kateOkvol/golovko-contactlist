package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.AttachmentDTO;
import services.AttachmentService;
import utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class AttachmentController {
    private final ControllerUtils util;
    private Properties properties = new Properties();


    public AttachmentController() {
        this.util = new ControllerUtils();
        try {
            this.properties.load(AttachmentController.class.getClassLoader().getResourceAsStream("config-web.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchAttaches(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer contactId = util.processId("contactId", request);
        System.out.println(contactId);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(
                mapper.writeValueAsString(
                        new AttachmentService().findAll(contactId)));
    }

    public void getAttach(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AttachmentService service = new AttachmentService();
        ObjectMapper mapper = new ObjectMapper();
        Integer id = util.processId("id", request);

        response.getWriter().write(
                mapper.writeValueAsString(
                        service.getById(id)));
    }

    public void createAttach(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AttachmentDTO[] elements = new ObjectMapper().convertValue(
                util.prepareToDTO(request), AttachmentDTO[].class);
        ArrayList<Integer> list = new ArrayList<>();
        for (AttachmentDTO dto : elements) {
            AttachmentService service = new AttachmentService(dto);
            int id = service.createAttach();
            service.setPath(dto.getPath(), id);

            list.add(id);
            // String jsonString = "[" + id + "]";
//            response.getWriter().write(jsonString);
//            System.out.println(jsonString);

        }
        response.getWriter().write(new ObjectMapper().writeValueAsString(list));
        response.setStatus(200);
        System.out.println(list.toString());
    }

    public void deleteAttach(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filePath = properties.getProperty("file_path");
        ObjectMapper mapper = new ObjectMapper();
        ArrayList array = mapper.convertValue(util.prepareToDTO(request), ArrayList.class);
        AttachmentService service = new AttachmentService();
        for (Object element : array) {
            String path = service.deleteAttach(Integer.parseInt((String) element));
            new File(filePath + path).delete();
        }
        response.setStatus(200);
    }

    public void uploadAttach(HttpServletRequest request, HttpServletResponse response) {
        String path = properties.getProperty("file_path");
        util.uploadAttach(path, request, response);
    }

    public void updateAttach(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AttachmentDTO[] elements = new ObjectMapper().convertValue(
                util.prepareToDTO(request), AttachmentDTO[].class);
        for (AttachmentDTO dto : elements) {
            new AttachmentService(dto).updateAttach();
        }
        response.setStatus(200);
    }

    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AttachmentService service = new AttachmentService();
        Integer id = util.processId("id", request);
        String name = service.getById(id).getPath();
        String filePath = properties.getProperty("file_path");
        String fileName = filePath + name;
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        util.writeAttachResponse(fileName, response);
    }
}