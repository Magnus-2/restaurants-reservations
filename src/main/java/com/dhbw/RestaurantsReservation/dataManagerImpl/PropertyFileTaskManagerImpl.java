package com.dhbw.RestaurantsReservation.dataManagerImpl;

import com.dhbw.RestaurantsReservation.dataManager.TaskManager;
import com.dhbw.RestaurantsReservation.model.student.Student;
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
    public Collection<Task> getAllTasks(Student student) {

        // I am ignoring the student and retrieve all tasks from the file

        List<Task> tasks = new ArrayList<>();

        Properties properties = new Properties();
        int i = 1;
        try {
            properties.load(new FileInputStream(fileName));

           while(properties.containsKey("Task."+ i +".name")) {
               System.out.println("Bin in der list.");
               tasks.add(
                       new Task(
                               properties.getProperty("Task."+ i +".name"),
                               properties.getProperty("Task."+ i +".surname"),
                               properties.getProperty("Task."+ i +".description"),
                               Integer.parseInt(properties.getProperty("Task."+ i +".priority")))
               );
               i++;
           }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return tasks;
    }

    @Override
    public void addTask(Task task, Student student) {
        Collection<Task> tasks = getAllTasks(student);
        tasks.add(task);
        storeAllTasks(tasks, student);
    }


    public void storeAllTasks(Collection<Task> tasks, Student student) {

        // I am ignoring the student and storing all tasks to the file

        Properties properties = new Properties();
        AtomicInteger i = new AtomicInteger(0);
        tasks.forEach(
                task -> {
                    properties.setProperty("Task."+ i.incrementAndGet() + ".name", task.getName());
                    properties.setProperty("Task."+ i.incrementAndGet() + ".surname", task.getName());
                    properties.setProperty("Task."+ i.get() + ".description", task.getDescription());
                    properties.setProperty("Task."+ i.get() + ".priority",""+ task.getPriority());
                }
        );
        try{
            properties.store(new FileOutputStream(fileName), "Store data to file.");
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
