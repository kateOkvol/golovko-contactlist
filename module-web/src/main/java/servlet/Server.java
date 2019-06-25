package servlet;

import controllers.MainContactsController;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="application", urlPatterns = {"/application"})
public class Server extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String command = request.getParameter("command");
        switch (command){
            case "mainContacts":
                mainContacts(request, response);
        }
    }

    private void mainContacts(HttpServletRequest request, HttpServletResponse response){
        MainContactsController main = new MainContactsController();
        ObjectMapper mapper = new ObjectMapper();
        try {
            response.getWriter().write(mapper.writeValueAsString(main.searchContacts()));
        } catch (IOException e) {
            System.out.println("эррор при записи в респонз. Сделай свой эксепшн! и логи добавь наконец");
        }
    }
}
