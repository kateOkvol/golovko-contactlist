package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import services.MainNumberService;
import utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainNumberController {
    private final ControllerUtils util;

    public MainNumberController() {
        this.util = new ControllerUtils();
    }

    public void searchPhones(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer contactId = util.processId("contactId", request);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(
                mapper.writeValueAsString(
                        new MainNumberService().findAll(contactId)));
    }
}
