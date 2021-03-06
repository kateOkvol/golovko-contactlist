package utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ControllerUtils {
    private static final org.apache.log4j.Logger logger = Logger.getLogger(ControllerUtils.class);

    public ControllerUtils() {
    }

    public JsonNode prepareToDTO(HttpServletRequest request) throws IOException {
        String jsonString = processRequest(request);
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        logger.info("JSON from client:\n\t" + jsonString);
        return mapper.readTree(jsonString);
    }

    public String processRequest(HttpServletRequest request) throws IOException {
        String line;
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
        logger.info("id JSON from client:\n\t" + jsonString);
        return jsonNode.get(idName).asInt();
    }

    public void writeAttachResponse(String fileName, HttpServletResponse response) throws IOException {
        BASE64Encoder encoder = new BASE64Encoder();
        encoder.encode(new FileInputStream(new File(fileName)), response.getOutputStream());
        logger.info("File "+fileName+" was successfully written to the response.");
    }

    public void uploadAttach(String path, HttpServletRequest request, HttpServletResponse response) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 3);//3 MB
        factory.setRepository(new File(path));
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List items = upload.parseRequest(request);
            for (Object item : items) {
                FileItem fileItem = (FileItem) item;
                if (fileItem.getName() != null) {
                    if (new File(path, fileItem.getName()).isFile()) {
                        new File(path, fileItem.getName()).delete();
                        logger.info("Old file was deleted");
                    }
                    File file = new File(path, fileItem.getName());
                    fileItem.write(file);
                    logger.info(file.getName() + " was added at folder " + path);
                    response.setStatus(HttpServletResponse.SC_OK);
                }
            }
        } catch (FileUploadException e) {
            logger.error("Can't upload file:\n\t"+ Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("FileItem write exception:\n\t"+ Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }
    }

    public void downloadImg(String name, String filePath, HttpServletResponse response) throws IOException {
        String type = name.split(".+\\.", 2)[1];
        String fileName = filePath + name;
        BufferedImage image = ImageIO.read(new File(fileName));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(image, type, stream);
        byte[] array = stream.toByteArray();
        response.getWriter().write(
                new ObjectMapper().writeValueAsString(array));
    }
}