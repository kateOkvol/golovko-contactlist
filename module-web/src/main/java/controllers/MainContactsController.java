package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.MainContactDTO;
import services.MainContactService;
import utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class MainContactsController {

    public MainContactsController() {
    }

    public void getContacts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int page = Integer.parseInt(new ControllerUtils().processRequest(request));
        List<MainContactDTO> list = new MainContactService().findAll(page);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(
                mapper.writeValueAsString(list));
    }

    public void searchContacts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonNode json = new ControllerUtils().prepareToDTO(request);
        if (!request.getParameterMap().isEmpty()) {
            String query = "";
            query += setParameter(json, "firstName");
            query += setParameter(json, "middleName");
            query += setParameter(json, "lastName");
            query += setParameter(json, "gender");
            query += setParameter(json, "citizenship");
            query += setParameter(json, "maritalStatus");
            query += setParameter(json, "country");
            query += setParameter(json, "city");
            query += setParameter(json, "street");
            query += setParameter(json, "house");
            query += setParameter(json, "flat");
            query += setParameter(json, "zipCode");

            String filter = "";
            filter = json.get("filter").textValue();
            if (filter != null && !filter.equals("")) {
                query += "birthDate" +
                        (filter.equals("more") ? ">" : "<")
                        + "'" + json.get("birthDate").textValue() + "';";
            } else {
                if(!query.equals("")){
                    query = query.substring(0, query.length() - 4) + ";"; //4 = 'a','n','d',' '
                }
            }
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(
                    new MainContactService().findAllMatches(query)));
        } else {
            response.setStatus(500);
        }
    }

    private String setParameter(JsonNode json, String parameter) {
        String query = "";
            String value = json.get(parameter).textValue();
            if (value != null && !value.equals("")) {
                String parameterName = toUnderscore(parameter);
                query+= parameterName + "='" + value + "' and ";
            }
        return query;
    }

    private String toUnderscore(String parameter) {
        return parameter.replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase();
    }
}
