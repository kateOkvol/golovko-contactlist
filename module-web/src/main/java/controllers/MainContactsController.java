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

    public void getPageInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String type = request.getHeader("Content-type");
        JsonNode json = new ControllerUtils().prepareToDTO(request);
        String query = "";
        if (json != null) {
            query = " where " + parseQuery(json, request);
        }
        response.addHeader("Content-type", type);
        response.getWriter().write(
                new ObjectMapper().writeValueAsString(
                        new MainContactService().getPageInfo(query)));
    }

    public void searchContacts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonNode json = new ControllerUtils().prepareToDTO(request);
        if (!request.getParameterMap().isEmpty()) {
            String query = parseQuery(json, request);
            int page = json.get("page").asInt();
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(
                    new MainContactService().findAllMatches(page, query)));
        } else {
            response.setStatus(500);
        }
    }

    private String parseQuery(JsonNode json, HttpServletRequest request) {
        String query = "";
        if (!request.getParameterMap().isEmpty()) {
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

            String filter = json.get("filter").textValue();
            if (filter != null && !filter.equals("")) {
                query += "birthDate" +
                        (filter.equals("more") ? ">" : "<")
                        + "'" + json.get("birthDate").textValue() + "'";
            } else {
                if (!query.equals("")) {
                    query = query.substring(0, query.length() - 4); //4 = 'a','n','d',' '
                }
            }
        }
        return query;
    }

    private String setParameter(JsonNode json, String parameter) {
        String query = "";
        String value = json.get(parameter).textValue();
        if (value != null && !value.equals("")) {
            String parameterName = toUnderscore(parameter);
            query += parameterName + "='" + value + "' and ";
        }
        return query;
    }

    private String toUnderscore(String parameter) {
        return parameter.replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase();
    }
}
