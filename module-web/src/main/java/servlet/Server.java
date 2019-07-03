package servlet;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
                deleteContact(request, response);
                break;
            case "createContact":
                createContact(request, response);
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

    private void createContact(HttpServletRequest request, HttpServletResponse response) {
        //System.out.println(request.toString());
        ///////// take json, parse
        ContactDTO dto = new ContactDTO();
        new ContactsController().createContact(dto);
    }


    private void deleteContact(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        new ContactsController().deleteContact(id);
        try {
            response.sendRedirect("mainContacts");
        } catch (IOException e) {
            System.out.println("redirect deleteMainContact");
        }
    }

    private void getContactById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String line = null;
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = request.getReader();
        while((line = reader.readLine()) != null){
            buffer.append(line);
        }

        ContactsController controller = new ContactsController();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(buffer.toString());
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
}

