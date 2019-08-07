package servlets;

import email.QuartzJob;
import org.apache.log4j.Logger;
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
import java.util.Arrays;

public class QuartsListener implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(QuartsListener.class);
    private Scheduler scheduler;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("Quarts Listener started.");
        try {
            JobDetail job = JobBuilder.newJob(QuartzJob.class)
                    .withIdentity("job", "group").build();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger", "group")
                    .forJob(job)
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 * * ?"))   //at midnight everyday
                    .build();

            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.scheduleJob(job, trigger);
            scheduler.start();

        } catch (SchedulerException e) {
            logger.error("Scheduler jobs exception:\n\t" + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            logger.error("Can't shutdown scheduler :\n\t" + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }
    }
}