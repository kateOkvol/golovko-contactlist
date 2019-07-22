package servlets;

import email.Quartz;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class QuartsListener implements ServletContextListener {
    private Scheduler scheduler;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        JobDetail job = JobBuilder.newJob(Quartz.class)
                        .withIdentity("job", "group").build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("job", "group")
                .withSchedule(CronScheduleBuilder.cronSchedule("10 0 * * * ?"))   //at midnight everyday
                .build();
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job,trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
