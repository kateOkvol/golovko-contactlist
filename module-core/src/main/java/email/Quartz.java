package email;

import dao.DAOImpl.ContactDAOImpl;
import entities.Contact;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import services.EmailMessageService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class Quartz implements Job {
    private final static String EMAIL = "contactslist.okvol@gmail.com";
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Date date = new Date(System.currentTimeMillis());
        String messageText = "Today is the birthday of:\n$list$";
        List<Contact> list = new ContactDAOImpl().getByBirthDate(date);
        if (!list.isEmpty()){
            StringBuilder builder = new StringBuilder();
            for(Contact contact: list){
                builder.append(contact.getFirstName()).append(" ")
                        .append(contact.getMiddleName()).append(" ")
                        .append(contact.getLastName()).append("\n");
            }
            StringTemplate message = new StringTemplate(messageText, DefaultTemplateLexer.class);
            message.setAttribute("list", builder.toString());
            try {
                EmailMessageService.sendMessage(EMAIL, message.toString());
            } catch (IOException | MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
