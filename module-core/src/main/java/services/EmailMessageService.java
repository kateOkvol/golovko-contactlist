package services;

import dao.DAOImpl.ContactDAOImpl;
import dto.EmailMessageDTO;
import entities.Contact;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;
import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class EmailMessageService {
    private static final Logger logger = Logger.getLogger(EmailMessageService.class);

    public EmailMessageService() {
    }

    public static void sendMessage(String recipient, String message, String topic) throws IOException, MessagingException {
        Properties properties = new Properties();
        properties.load(EmailMessageService.class.getClassLoader().getResourceAsStream("email.properties"));
        String user = properties.getProperty("mail.smtps.user");
        String password = properties.getProperty("mail.smtps.password");

        Session session = Session.getDefaultInstance(properties);
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(user));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        mimeMessage.setText(message, "UTF-8");
        mimeMessage.setSubject(topic, "UTF-8");

        try(Transport transport = session.getTransport()) {
            logger.info("Transport for sending email is open.");
            transport.connect(null, password);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            logger.info("Email sent to: " + Arrays.toString(mimeMessage.getAllRecipients()));
        }finally {
            logger.info("Transport for sending email is closed.");
        }
    }

    public Map<String, String> getTemplatesMap(){
        Properties properties = new Properties();
        try {
            properties.load(EmailMessageService.class.getClassLoader().getResourceAsStream("templates.properties"));
        } catch (IOException e) {
            logger.error("Can't load properties:\n\t" + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }
        Map<String, String> templatesMap = new HashMap<>();
        templatesMap.put("birthday", properties.getProperty("birthday"));
        templatesMap.put("newYear", properties.getProperty("new-year"));
        return templatesMap;
    }

    public void sendEmail(EmailMessageDTO email){
        List<String> recipientsId = email.getRecipients();
        List<String> recipientsEmail = new ArrayList<>();
        List<String> recipientsNames = new ArrayList<>();

        ContactDAOImpl dao = new ContactDAOImpl();

        for (String id : recipientsId) {
            Contact contact = dao.getById(Integer.parseInt(id));
            recipientsEmail.add(contact.getEmail());
            recipientsNames.add(contact.getFirstName());
        }
        String text = email.getMessage();
        if (!text.equals("")) {
            int i = 0;
            for (String mail : recipientsEmail) {
                StringTemplate temp = new StringTemplate(text, DefaultTemplateLexer.class);
                temp.setAttribute("name", recipientsNames.get(i));
                if (!mail.equals("")) {
                    try {
                        sendMessage(mail, temp.toString(), email.getTopic());
                    } catch (IOException e) {
                        logger.error("IO exception sending email:\n\t" + Arrays.toString(e.getStackTrace()));
                        e.printStackTrace();
                    } catch (MessagingException e) {
                        logger.error("Messaging exception:\n\t" + Arrays.toString(e.getStackTrace()));
                        e.printStackTrace();
                    }
                }
                i++;
            }
        }
    }
}
