package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.utils.ControllerUtils;
import dto.MainNumberDTO;
import services.MainNumberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainNumberController {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private final ControllerUtils<MainNumberDTO> util;

    public MainNumberController() {
        this.util = new ControllerUtils<>(MainNumberDTO.class);
    }

    public MainNumberController(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.util = new ControllerUtils<>(MainNumberDTO.class);
    }

    public void searchPhones() throws IOException {
        Integer contactId = util.getContactId(request);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(
                mapper.writeValueAsString(
                        new MainNumberService().findAll(contactId)));
    }
}
