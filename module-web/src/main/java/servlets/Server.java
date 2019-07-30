package servlets;

import controllers.AttachmentController;
import controllers.ContactsController;
import controllers.EmailMessageController;
import controllers.MainContactsController;
import controllers.MainNumberController;
import controllers.NumberController;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet(name = "application", urlPatterns = {"/application"})
@MultipartConfig
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
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String command = request.getQueryString();
        try {
            switch (command) {
                case "mainContacts":
                    mainContacts(request, response);
                    break;
                case "deleteContacts":
                    deleteContacts(request);
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
                    mainAttaches(request, response);
                    break;
                case "getAttachById":
                    getAttachById(request, response);
                    break;
                case "createAttach":
                    createAttach(request);
                    break;
                case "uploadAttach":
                    uploadAttach(request, response);
                    break;
                case "uploadAva":
                    uploadAva(request, response);
                    break;
                case "deleteAttach":
                    deleteAttach(request);
                    break;
                case "updateAttach":
                    updateAttach(request);
                    break;
                case "downloadAttach":
                    downloadAttach(request, response);
                    break;
                case "getTemplates":
                    getTemplates(response);
                    break;
                case "sendEmail":
                    sendEmail(request);
                    break;
                case "searchContacts":
                    searchContacts(request, response);
                    break;
                case "getAvatar":
                    getAvatar(request, response);
                    break;
            }
            System.out.println(command + " finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mainContacts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new MainContactsController().getContacts(request, response);
    }

    private void createContact(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new ContactsController().createContact(request, response);
    }

    private void updateContact(HttpServletRequest request) throws IOException {
        new ContactsController().updateContact(request);
    }

    private void deleteContacts(HttpServletRequest request) throws IOException {
        new ContactsController().deleteContacts(request);
    }

    private void getContactById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new ContactsController().getContact(request, response);
    }

    private void mainPhones(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new MainNumberController().searchPhones(request, response);
    }

    private void deletePhone(HttpServletRequest request) throws IOException {
        new NumberController().deletePhone(request);
    }

    private void getPhoneById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new NumberController().getPhone(request, response);
    }

    private void createPhone(HttpServletRequest request) throws IOException {
        new NumberController().createPhone(request);
    }

    private void updatePhone(HttpServletRequest request) throws IOException {
        new NumberController().updatePhone(request);
    }

    private void mainAttaches(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new AttachmentController().searchAttaches(request, response);
    }

    private void getAttachById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new AttachmentController().getAttach(request, response);
    }

    private void createAttach(HttpServletRequest request) throws IOException {
        new AttachmentController().createAttach(request);
    }

    private void downloadAttach(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new AttachmentController().download(response, request);
    }

    private void uploadAttach(HttpServletRequest request, HttpServletResponse response) {
        new AttachmentController().uploadAttach(request, response);
    }

    private void deleteAttach(HttpServletRequest request) throws IOException {
        new AttachmentController().deleteAttach(request);
    }

    private void updateAttach(HttpServletRequest request) throws IOException {
        new AttachmentController().updateAttach(request);
    }

    private void uploadAva(HttpServletRequest request, HttpServletResponse response) {
        new ContactsController().uploadAvatar(request, response);
    }

    private void getTemplates(HttpServletResponse response) throws IOException {
        new EmailMessageController().getTemplates(response);
    }

    private void sendEmail(HttpServletRequest request) throws Exception {
        new EmailMessageController().sendEmail(request);
    }

    private void searchContacts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new MainContactsController().searchContacts(request, response);
    }

    private void getAvatar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new ContactsController().getAvatar(request, response);
    }
}

