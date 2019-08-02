package servlets;

import controllers.AttachmentController;
import controllers.ContactsController;
import controllers.EmailMessageController;
import controllers.MainContactsController;
import controllers.MainNumberController;
import controllers.NumberController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

class CommandMapper {
    private static HashMap<String, Method> map = new HashMap<>();

    private static void initMap() throws NoSuchMethodException {
        map.put("mainContacts", MainContactsController.class.getMethod("getContacts", HttpServletRequest.class, HttpServletResponse.class));
        map.put("deleteContacts", ContactsController.class.getMethod("deleteContacts", HttpServletRequest.class, HttpServletResponse.class));
        map.put("createContact", ContactsController.class.getMethod("createContact", HttpServletRequest.class, HttpServletResponse.class));
        map.put("updateContact", ContactsController.class.getMethod("updateContact", HttpServletRequest.class, HttpServletResponse.class));
        map.put("getContactById", ContactsController.class.getMethod("getContact", HttpServletRequest.class, HttpServletResponse.class));
        map.put("mainPhones", MainNumberController.class.getMethod("searchPhones", HttpServletRequest.class, HttpServletResponse.class));
        map.put("deletePhone", NumberController.class.getMethod("deletePhone", HttpServletRequest.class, HttpServletResponse.class));
        map.put("getPhoneById", NumberController.class.getMethod("getPhone", HttpServletRequest.class, HttpServletResponse.class));
        map.put("createPhone", NumberController.class.getMethod("createPhone", HttpServletRequest.class, HttpServletResponse.class));
        map.put("updatePhone", NumberController.class.getMethod("updatePhone", HttpServletRequest.class, HttpServletResponse.class));
        map.put("mainAttaches", AttachmentController.class.getMethod("searchAttaches", HttpServletRequest.class, HttpServletResponse.class));
        map.put("getAttachById", AttachmentController.class.getMethod("getAttach", HttpServletRequest.class, HttpServletResponse.class));
        map.put("createAttach", AttachmentController.class.getMethod("createAttach", HttpServletRequest.class, HttpServletResponse.class));
        map.put("uploadAttach", AttachmentController.class.getMethod("uploadAttach", HttpServletRequest.class, HttpServletResponse.class));
        map.put("uploadAva", ContactsController.class.getMethod("uploadAvatar", HttpServletRequest.class, HttpServletResponse.class));
        map.put("deleteAttach", AttachmentController.class.getMethod("deleteAttach", HttpServletRequest.class, HttpServletResponse.class));
        map.put("updateAttach", AttachmentController.class.getMethod("updateAttach", HttpServletRequest.class, HttpServletResponse.class));
        map.put("downloadAttach", AttachmentController.class.getMethod("download", HttpServletRequest.class, HttpServletResponse.class));
        map.put("getTemplates", EmailMessageController.class.getMethod("getTemplates", HttpServletRequest.class, HttpServletResponse.class));
        map.put("sendEmail", EmailMessageController.class.getMethod("sendEmail", HttpServletRequest.class, HttpServletResponse.class));
        map.put("searchContacts", MainContactsController.class.getMethod("searchContacts", HttpServletRequest.class, HttpServletResponse.class));
        map.put("getAvatar", ContactsController.class.getMethod("getAvatar", HttpServletRequest.class, HttpServletResponse.class));
        map.put("getPageInfo", MainContactsController.class.getMethod("getPageInfo", HttpServletRequest.class, HttpServletResponse.class));
    }

    void forward(String url, HttpServletRequest request, HttpServletResponse response) {
        try {
            initMap();
            Method method = map.get(url);
            Object type = method.getDeclaringClass().newInstance();
            method.invoke(type, request, response);
            response.setStatus(200);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            response.setStatus(500);
            e.printStackTrace();
        }
    }
}
