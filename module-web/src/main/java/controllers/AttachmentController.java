package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.utils.ControllerUtils;
import dto.AttachmentDTO;
import services.AttachmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AttachmentController {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private final ControllerUtils<AttachmentDTO> util;


    public AttachmentController() {
        this.util = new ControllerUtils<>(AttachmentDTO.class);
    }

    public AttachmentController(HttpServletRequest request) {
        this.request = request;
        this.util = new ControllerUtils<>(AttachmentDTO.class);
    }

    public AttachmentController(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.util = new ControllerUtils<>(AttachmentDTO.class);
    }


    public void searchAttaches() throws IOException {
        Integer contactId = util.getContactId(request);
        System.out.println(contactId);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(
                mapper.writeValueAsString(
                        new AttachmentService().findAll(contactId)));
    }

    public void createAttach(AttachmentDTO attachmentDTO) {
        new AttachmentService(attachmentDTO).createAttach();
    }

    public void deleteAttach(Integer id) {
        new AttachmentService().deleteAttach(id);
    }

    public AttachmentDTO getAttach(Integer id) {
        return new AttachmentService().getById(id);
    }

    public void updateAttach(AttachmentDTO contactDTO) {
        new AttachmentService(contactDTO).updateAttach();
    }
}