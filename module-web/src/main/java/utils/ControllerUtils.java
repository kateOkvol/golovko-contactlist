package utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ControllerUtils/*<T extends DTO>*/ {
//    private final Class<T> typeClass;

    public ControllerUtils(/*Class<T> typeClass*/) {
//        this.typeClass = typeClass;
    }



    public JsonNode prepareToDTO(HttpServletRequest request) throws IOException {
        String jsonString = processRequest(request);

        System.out.println(jsonString);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        return mapper.readTree(jsonString);
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

    public Integer processId(String idName, HttpServletRequest request) throws IOException {
        String jsonString = processRequest(request);
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        JsonNode jsonNode = mapper.readTree(jsonString);
        System.out.println(jsonNode);
        return jsonNode.get(idName).asInt();
    }

    public void writeAttachResponse(String fileName, HttpServletResponse response) throws IOException {
        FileInputStream reader = new FileInputStream(fileName);
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        PrintWriter responseWriter = response.getWriter();
        int i;
        while ((i = reader.read()) != -1) {
            responseWriter.write(i);
        }
    }

    public void uploadAttach(String path, HttpServletRequest request, HttpServletResponse response){
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 3);//3 MB
        factory.setRepository(new File(path));
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List items = upload.parseRequest(request);
            for (Object item : items) {
                FileItem fileItem = (FileItem) item;
                File file = new File(path, fileItem.getName());
                fileItem.write(file);
                System.out.println(file.getName() + " was added at folder");
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}