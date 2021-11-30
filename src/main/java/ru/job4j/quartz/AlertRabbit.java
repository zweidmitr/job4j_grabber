package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.InputStream;
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

    public static void main(String[] args) {
        Properties config = loadProperties();
        int timeInterval = Integer.parseInt(config.getProperty("rabbit.interval"));
        try {
            /* 1. Конфигурирование */
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            /* В объект Scheduler мы будем добавлять задачи,
            которые хотим выполнять периодически. */

            /* 2. Создание задачи */
            JobDetail job = newJob(Rabbit.class).build();
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
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    /* Внутри этого класса нужно описать требуемые действия.
    В нашем случае - это вывод на консоль текста. */
    public static class Rabbit implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Rabbit runs here ... hello");
        }
    }
}
