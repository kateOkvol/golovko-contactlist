package servlets;

import org.apache.log4j.Logger;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@WebServlet(name = "application", urlPatterns = {"/application"})
@MultipartConfig
public class Server extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Server.class);

    @Override
    public void init(){
        logger.info(" \n \nServer started\n ");
    }

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
            logger.error("Request encoding exception:\n\t" + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }
        String command = request.getQueryString();
        new CommandMapper().forward(command, request, response);
        logger.info("Command \"" + command + "\" finished");
    }
}

