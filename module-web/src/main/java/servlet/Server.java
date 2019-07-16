package servlet;

import controllers.AttachmentController;
import controllers.ContactsController;
import controllers.MainContactsController;
import controllers.MainNumberController;
import controllers.NumberController;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        response.addHeader("Access-Control-Allow-Origin", "*");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String command = request.getParameter("command");
        System.out.println(command);
        try {
            switch (command) {
                case "mainContacts":
                    mainContacts(response);
                    break;
                case "deleteContacts":
                    deleteContact(request);
                    break;
                case "createContact":
                    createContact(request, response);
                    break;
                case "updateContact":
                    updateContact(request);
                    break;
                case "getContactById":
                    getContactById(request, response);
                    break;
                case "mainPhones":
                    mainPhones(request, response);
                    break;
                case "deletePhone":
                    deletePhone(request);
                    break;
                case "getPhoneById":
                    getPhoneById(request, response);
                    break;
                case "createPhone":
                    createPhone(request);
                    break;
                case "updatePhone":
                    updatePhone(request);
                    break;
                case "mainAttaches":
                    mainAttaches(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mainContacts(HttpServletResponse response) throws IOException {
        MainContactsController main = new MainContactsController(response);
        main.searchContacts();
    }

    private void createContact(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ContactsController controller = new ContactsController(request, response);
        controller.createContact();
    }

    private void updateContact(HttpServletRequest request) throws IOException {
        ContactsController controller = new ContactsController(request);
        controller.updateContact();
    }

    private void deleteContact(HttpServletRequest request) throws IOException {
        ContactsController controller = new ContactsController(request);
        controller.deleteContact();
    }

    private void getContactById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ContactsController controller = new ContactsController(request, response);
        controller.getContact();
    }

    private void mainPhones(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MainNumberController main = new MainNumberController(request, response);
        main.searchPhones();
    }

    private void deletePhone(HttpServletRequest request) throws IOException {
        NumberController controller = new NumberController(request);
        controller.deletePhone();
    }

    private void getPhoneById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        NumberController controller = new NumberController(request, response);
        controller.getPhone();
    }

    private void createPhone(HttpServletRequest request) throws IOException {
        NumberController controller = new NumberController(request);
        controller.createPhone();
    }

    private void updatePhone(HttpServletRequest request) throws IOException {
        NumberController controller = new NumberController(request);
        controller.updatePhone();
    }

    private void mainAttaches(HttpServletRequest request) throws IOException {
        AttachmentController controller = new AttachmentController(request);
        controller.searchAttaches();
    }
}

