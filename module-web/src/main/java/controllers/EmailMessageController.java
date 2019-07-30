package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.EmailMessageDTO;
import services.EmailMessageService;
import utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class EmailMessageController {
    private final ControllerUtils util;

    public EmailMessageController() {
        this.util = new ControllerUtils();
    }

    public void getTemplates(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map templates = new EmailMessageService().getTemplatesMap();
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(
                mapper.writeValueAsString(templates));
    }

    public void sendEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        EmailMessageService service = new EmailMessageService();
        EmailMessageDTO email = mapper.convertValue(util.prepareToDTO(request), EmailMessageDTO.class);
        service.sendEmail(email);
        response.setStatus(200);
    }
}
