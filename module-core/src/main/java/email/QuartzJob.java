package email;

import dao.DAOImpl.ContactDAOImpl;
import db.DataBaseConnection;
import entities.Contact;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import services.EmailMessageService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class QuartzJob implements Job {
    private final static String EMAIL = "contactslist.okvol@gmail.com";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Date date = new Date(System.currentTimeMillis());
        String messageText = "Today is the birthday of:\n$list$";
        List<Contact> list;
        try {
            list = new ContactDAOImpl(DataBaseConnection.getConnection()).getByBirthDate(date);

            if (!list.isEmpty()) {
                StringBuilder builder = new StringBuilder();
                for (Contact contact : list) {
                    builder.append(contact.getFirstName()).append(" ")
                            .append(contact.getMiddleName()).append(" ")
                            .append(contact.getLastName()).append("\n");
                }
                StringTemplate message = new StringTemplate(messageText, DefaultTemplateLexer.class);
                message.setAttribute("list", builder.toString());
                try {
                    EmailMessageService.sendMessage(EMAIL, message.toString(), "");
                } catch (IOException | MessagingException e) {
                    e.printStackTrace();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
