package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.MainContactDTO;
import services.MainContactService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class MainContactsController {
    private HttpServletResponse response;

    public MainContactsController() {
    }

    public MainContactsController(HttpServletResponse response) {
        this.response = response;
    }

    public void searchContacts() throws IOException {
        List<MainContactDTO> list = new MainContactService().findAll();
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(
                mapper.writeValueAsString(list));
    }
}
