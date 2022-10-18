package com.dhbw.RestaurantsReservation.dataManagerImpl;

import com.dhbw.RestaurantsReservation.dataManager.TaskManager;
import com.dhbw.RestaurantsReservation.model.user.User;
import com.dhbw.RestaurantsReservation.model.task.Task;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class PropertyFileTaskManagerImpl implements TaskManager {

    String fileName;

    static PropertyFileTaskManagerImpl propertyFileTaskManager = null;

    private PropertyFileTaskManagerImpl(String fileName) {
        this.fileName = fileName;
    }

    static public PropertyFileTaskManagerImpl getPropertyFileTaskManagerImpl(String fileName) {
        if (propertyFileTaskManager == null)
            propertyFileTaskManager = new PropertyFileTaskManagerImpl(fileName);
        return propertyFileTaskManager;
    }



    @Override
    public Collection<Task> getAllTasks(User user) {

        // I am ignoring the Users and retrieve all tasks from the file

        List<Task> tasks = new ArrayList<>();

        Properties properties = new Properties();
        int i = 1;
        try {
            properties.load(new FileInputStream(fileName));

           while(properties.containsKey("Task."+ i +".userName")) {
               System.out.println("Bin in der list.");
               tasks.add(
                       new Task(
                               properties.getProperty("Task."+ i +".userName"),
                               properties.getProperty("Task."+ i +".userSureName"),
                               properties.getProperty("Task."+ i +".userPassword"),
                               properties.getProperty("Task."+ i +".eMail"),
                               Integer.parseInt(properties.getProperty("Task."+ i +".userID")))
               );
               i++;
           }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return tasks;
    }

    @Override
    public void addTask(Task task, User user) {
        Collection<Task> tasks = getAllTasks(user);
        tasks.add(task);
        storeAllTasks(tasks, user);
    }


    public void storeAllTasks(Collection<Task> tasks, User user) {

        // I am ignoring the student and storing all tasks to the file

        Properties properties = new Properties();
        AtomicInteger i = new AtomicInteger(0);
        tasks.forEach(
                task -> {
                    properties.setProperty("Task."+ i.incrementAndGet() + ".name", task.getUserName());
                    properties.setProperty("Task."+ i.get() + ".description", task.getUserSureName());
                    properties.setProperty("Task."+ i.get() + ".description", task.getUserPassword());
                    properties.setProperty("Task."+ i.get() + ".description", task.geteMail());
                    properties.setProperty("Task."+ i.get() + ".priority",""+ task.getUserID());
                }
        );
        try{
            properties.store(new FileOutputStream(fileName), "Store data to file.");
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
