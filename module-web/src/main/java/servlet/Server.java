package servlet;

import controllers.ContactsController;
import controllers.MainContactsController;
import dto.ContactDTO;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        //response.setCharacterEncoding("UTF-8");
        String command = request.getParameter("command");
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
        System.out.println(request.toString());
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

    private void getContactById(HttpServletResponse response, HttpServletRequest request) {
        ContactsController controller = new ContactsController();
        ObjectMapper mapper = new ObjectMapper();
        try {
            response.getWriter().write(
                    mapper.writeValueAsString(
                            controller.getContact(
                                    Integer.parseInt(request.getParameter("id")))));
        } catch (IOException e) {
            System.out.println("эррор при записи в респонз. Сделай свой эксепшн! и логи добавь наконец");
        }
    }
}
