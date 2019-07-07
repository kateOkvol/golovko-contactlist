package servlet;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import controllers.ContactsController;
import controllers.MainContactsController;
import dto.ContactDTO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

@WebServlet(name = "application", urlPatterns = {"/application"})
public class Server extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String command = request.getParameter("command");
        System.out.println(command);
        switch (command) {
            case "mainContacts":
                mainContacts(request, response);
                break;
            case "deleteContact":
                try {
                    deleteContact(request, response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "createContact":
                try {
                    createContact(request, response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "updateContact":
                try {
                    updateContact(request, response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "getContactById":
                try {
                    getContactById(request, response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void mainContacts(HttpServletRequest request, HttpServletResponse response) {
        MainContactsController main = new MainContactsController();
        ObjectMapper mapper = new ObjectMapper();
        try {
            response.getWriter().write(
                    mapper.writeValueAsString(
                            main.searchContacts()));
        } catch (IOException e) {
            System.out.println("эррор при записи в респонз. Сделай свой эксепшн! и логи добавь наконец");
        }
    }

    private void createContact(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ContactDTO dto = parseIntoDTO(request);
        new ContactsController().createContact(dto);
    }

    private void updateContact(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ContactDTO dto = parseIntoDTO(request);
        new ContactsController().updateContact(dto);
    }

    private void deleteContact(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ContactsController controller = new ContactsController();
        String jsonString = processRequest(request);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonString);
        System.out.println(jsonNode);
        ArrayList array = mapper.convertValue(jsonNode, ArrayList.class);

        for (Object element : array) {
            controller.deleteContact(Integer.parseInt((String) element));
        }
    }

    private void getContactById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jsonString = processRequest(request);

        ContactsController controller = new ContactsController();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        JsonNode jsonNode = mapper.readTree(jsonString);
        System.out.println(jsonNode.get("id").asInt());
        try {
            response.getWriter().write(
                    mapper.writeValueAsString(
                            controller.getContact(
                                    jsonNode.get("id").asInt())));

        } catch (IOException e) {
            System.out.println("эррор при записи в респонз. Сделай свой эксепшн! и логи добавь наконец");
        }
    }

    private String processRequest(HttpServletRequest request) throws IOException {
        String line = null;
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    private ContactDTO parseIntoDTO(HttpServletRequest request) throws IOException {
        String jsonString = processRequest(request);

        System.out.println(jsonString);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
        mapper.disable(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS);

        JsonNode jsonNode = mapper.readTree(jsonString);

        ContactDTO dto = mapper.convertValue(jsonNode, ContactDTO.class);
        System.out.println(dto.toString());

        return dto;
    }
}

