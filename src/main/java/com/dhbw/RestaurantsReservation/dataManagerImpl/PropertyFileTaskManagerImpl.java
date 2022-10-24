package com.dhbw.RestaurantsReservation.dataManagerImpl;

import com.dhbw.RestaurantsReservation.dataManager.TaskManager;
import com.dhbw.RestaurantsReservation.model.restaurant.Restaurant;
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

        // I am ignoring the user and retrieve all usertasks from the file

        List<Task> usertasks = new ArrayList<>();

        Properties properties = new Properties();
        int i = 1;
        try {
            properties.load(new FileInputStream(fileName));

           while(properties.containsKey("Task."+ i +".name")) {
               System.out.println("Bin in der list.");
               usertasks.add(
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


        return usertasks;
    }

    @Override
    public void addTask(Task task, User user) {
        Collection<Task> usertasks = getAllTasks(user);
        usertasks.add(task);
        storeAllTasks(usertasks, user);
    }



    public void storeAllTasks(Collection<Task> usertasks, User user) {

        // I am ignoring the student and storing all usertasks to the file

        Properties properties = new Properties();
        AtomicInteger i = new AtomicInteger(0);
        usertasks.forEach(
                task -> {
                    properties.setProperty("Task."+ i.incrementAndGet() + ".name", task.getName());
                    properties.setProperty("Task."+ i.get() + ".surname", task.getSurname());
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





    @Override
    public Collection<Restaurant> getAllRestaurants(Restaurant restaurant) {

        // I am ignoring the user and retrieve all restauranttasks from the file

        List<Restaurant> restauranttasks = new ArrayList<>();

        Properties properties = new Properties();
        int i = 1;
        try {
            properties.load(new FileInputStream(fileName));

            while(properties.containsKey("Restaurant."+ i +".rName")) {
                System.out.println("Bin in der list.");
                restauranttasks.add(
                        new Restaurant(
                                properties.getProperty("Restaurant."+ i +".rName"),
                                Integer.parseInt(properties.getProperty("Restaurant."+ i +".rSeats")),
                                Integer.parseInt(properties.getProperty("Restaurant."+ i +".rZipcode")),
                                properties.getProperty("Restaurant."+ i +".rAddress"),
                                properties.getProperty("Restaurant."+ i +".rCategory"),
                                Integer.parseInt(properties.getProperty("Restaurant."+ i +".rPhone")),
                                properties.getProperty("Restaurant."+ i +".rEmail"),
                                properties.getProperty("Restaurant."+ i +".rPassword"))
                );
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return restauranttasks;
    }


    @Override
    public void addRestaurant(Restaurant restaurant) {
        Collection<Restaurant> restauranttasks = getAllRestaurants(restaurant);
        restauranttasks.add(restaurant);
        storeAllRestaurants(restauranttasks);
    }


    public void storeAllRestaurants(Collection<Restaurant> restauranttasks) {

        // I am ignoring the student and storing all usertasks to the file

        Properties properties = new Properties();
        AtomicInteger i = new AtomicInteger(0);
        restauranttasks.forEach(
                restaurant -> {
                    properties.setProperty("Restaurant."+ i.incrementAndGet() + ".rName", restaurant.getrName());
                    properties.setProperty("Restaurant."+ i.get() + ".rSeats",""+ restaurant.getrSeats());
                    properties.setProperty("Restaurant."+ i.get() + ".rZipcode",""+ restaurant.getrZipcode());
                    properties.setProperty("Restaurant."+ i.get() + ".rAddress", restaurant.getrAddress());
                    properties.setProperty("Restaurant."+ i.get() + ".rCategory", restaurant.getrCategory());
                    properties.setProperty("Restaurant."+ i.get() + ".rPhone",""+ restaurant.getrPhone());
                    properties.setProperty("Restaurant."+ i.get() + ".rEmail", restaurant.getrEmail());
                    properties.setProperty("Restaurant."+ i.get() + ".rPassword",""+ restaurant.getrPassword());
                }
        );
        try{
            properties.store(new FileOutputStream(fileName), "Store data to file.");
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    @Override
    public Collection<User> getAllUsers(User user) {

        // I am ignoring the user and retrieve all restauranttasks from the file

        List<User> usertasks = new ArrayList<>();

        Properties properties = new Properties();
        int i = 1;
        try {
            properties.load(new FileInputStream(fileName));

            while(properties.containsKey("User."+ i +".firstName")) {
                System.out.println("Bin in der list.");
                usertasks.add(
                        new User(
                                properties.getProperty("User."+ i +".firstName"),
                                properties.getProperty("User."+ i +".lastName"),
                                properties.getProperty("User."+ i +".eMail"),
                                properties.getProperty("User."+ i +".phoneNumber"),
                                properties.getProperty("User."+ i +".password"))
                );
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return usertasks;
    }

    @Override
    public void addUser(User user) {
        Collection<User> usertasks = getAllUsers(user);
        usertasks.add(user);
        storeAllUser(usertasks);
    }

    private void storeAllUser(Collection<User> usertasks) {

        Properties properties = new Properties();
        AtomicInteger i = new AtomicInteger(0);
        usertasks.forEach(
                user -> {
                    properties.setProperty("User."+ i.incrementAndGet() + ".firstName", user.getFirstName());
                    properties.setProperty("User."+ i.get() + ".lastName", user.getLastName());
                    properties.setProperty("User."+ i.get() + ".eMail", user.getEMail());
                    properties.setProperty("User."+ i.get() + ".phoneNumber", user.getPhoneNumber());
                    properties.setProperty("User."+ i.get() + ".password", user.getPassword());
                }
        );
        try{
            properties.store(new FileOutputStream(fileName), "Store data to file.");
        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
