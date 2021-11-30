package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

import static org.quartz.JobBuilder.newJob;

public class AlertRabbit {

    public static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream in = AlertRabbit.class.getClassLoader().getResourceAsStream(
                "rabbit.properties")) {
            properties.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void main(String[] args) throws Exception {
        Properties config = loadProperties();
        int timeInterval = Integer.parseInt(config.getProperty("rabbit.interval"));
        Class.forName(config.getProperty("driver-class-name"));

        try (Connection connection = DriverManager.getConnection(
                config.getProperty("url"),
                config.getProperty("username"),
                config.getProperty("password")
        )) {
            /* 1. Конфигурирование */
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            /* В объект Scheduler мы будем добавлять задачи,
            которые хотим выполнять периодически. */

            /* При создании Job мы указываем параметры data.
            В них мы передаем ссылку на store.  */
            JobDataMap data = new JobDataMap();
            data.put("connection", connection);

            /* 2. Создание задачи */
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            /* quartz каждый раз создает объект с типом org.quartz.Job. */

            /* 3. Создание расписания */
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(timeInterval)
                    .repeatForever();
            /* Конструкция выше настраивает периодичность запуска.
            В нашем случае, мы будем запускать задачу через 10 секунд и делать это бесконечно. */

            /* 4. Задача выполняется через триггер. */
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            /* Здесь можно указать, когда начинать запуск. Мы хотим сделать это сразу. */

            /* 5. Загрузка задачи и триггера в планировщик */
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(10000);
            scheduler.shutdown();
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    /* Внутри этого класса нужно описать требуемые действия.
    В нашем случае - это вывод на консоль текста. */
    public static class Rabbit implements Job {
        public Rabbit() {
            System.out.println(hashCode());
        }

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Rabbit runs here ... hello");

            Connection connection = (Connection) context
                    .getJobDetail()
                    .getJobDataMap()
                    .get("connection");

            try (PreparedStatement prep = connection.prepareStatement(
                    "INSERT INTO rabbit(created) VALUES (?)")) {
                prep.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                prep.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }

            /* Чтобы получить объекты из context используется следующий вызов. */
            List<Long> store = (List<Long>) context.getJobDetail().getJobDataMap().get("store");
            store.add(System.currentTimeMillis());
        }
    }
}
