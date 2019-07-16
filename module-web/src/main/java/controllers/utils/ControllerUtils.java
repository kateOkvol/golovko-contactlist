package controllers.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.DTO;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class ControllerUtils<T extends DTO> {
    private final Class<T> typeClass;

    public ControllerUtils(Class<T> typeClass) {
        this.typeClass = typeClass;
    }

    public Integer getContactId(HttpServletRequest request) throws IOException {
        String jsonString = processRequest(request);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonString);
        System.out.println(jsonNode);
        return jsonNode.get("contactId").asInt();
    }

    public T parseIntoDTO(HttpServletRequest request) throws IOException {
        String jsonString = processRequest(request);

        System.out.println(jsonString);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        JsonNode jsonNode = mapper.readTree(jsonString);

        T dto = mapper.convertValue(jsonNode, typeClass);
        System.out.println(dto.toString());

        return dto;
    }

    public String processRequest(HttpServletRequest request) throws IOException {
        String line = null;
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }
}
