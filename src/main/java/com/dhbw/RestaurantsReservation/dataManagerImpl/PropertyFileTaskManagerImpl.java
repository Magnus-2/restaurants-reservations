package com.dhbw.RestaurantsReservation.dataManagerImpl;

import com.dhbw.RestaurantsReservation.dataManager.TaskManager;
import com.dhbw.RestaurantsReservation.model.reservations.Reservations;
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
                                properties.getProperty("Restaurant."+ i +".oHMo"),
                                properties.getProperty("Restaurant."+ i +".oHTu"),
                                properties.getProperty("Restaurant."+ i +".oHWe"),
                                properties.getProperty("Restaurant."+ i +".oHTh"),
                                properties.getProperty("Restaurant."+ i +".oHFr"),
                                properties.getProperty("Restaurant."+ i +".oHSa"),
                                properties.getProperty("Restaurant."+ i +".oHSu"),
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
                    properties.setProperty("Restaurant."+ i.get() + ".oHMo", restaurant.getoHMo());
                    properties.setProperty("Restaurant."+ i.get() + ".oHTu", restaurant.getoHTu());
                    properties.setProperty("Restaurant."+ i.get() + ".oHWe", restaurant.getoHWe());
                    properties.setProperty("Restaurant."+ i.get() + ".oHTh", restaurant.getoHTh());
                    properties.setProperty("Restaurant."+ i.get() + ".oHFr", restaurant.getoHFr());
                    properties.setProperty("Restaurant."+ i.get() + ".oHSa", restaurant.getoHSa());
                    properties.setProperty("Restaurant."+ i.get() + ".oHSu", restaurant.getoHSu());
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
        Collection<User> usertasks = getLogin(user);
        usertasks.add(user);
        storeAllUser(usertasks);
    }

    private Collection<User> getLogin(User user) {
        List<User> userlogin = new ArrayList<>();

        Properties properties = new Properties();
        int i = 1;
        try {
            properties.load(new FileInputStream(fileName));

            while(properties.containsKey("User."+ i +".eMail")) {
                System.out.println("Bin in der list.");
                userlogin.add(
                        new User(
                                properties.getProperty("User."+ i +".eMail"),
                                properties.getProperty("User."+ i +".password"))
                );
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return userlogin;
    }

    @Override
    public boolean loginUser(User user) {
      /*  Collection<User> userlogin = getAllUsers(user);
        storeUser(userlogin);

      */
        return false;

    }

    private void storeUser(Collection<User> userlogin) {
        Properties properties = new Properties();
        AtomicInteger i = new AtomicInteger(0);
        userlogin.forEach(
                user -> {

                    properties.setProperty("User."+ i.get() + ".eMail", user.getEMail());
                    properties.setProperty("User."+ i.get() + ".password", user.getPassword());
                }
        );
        try{
            properties.store(new FileOutputStream(fileName), "Store data to file.");
        }catch (Exception e){
            e.printStackTrace();
        }


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


    @Override
    public void addReservations(Reservations reservations) {
        Collection<Reservations> reservationtasks = getAllReservation(reservations);
        reservationtasks.add(reservations);
        storeAllReservations(reservationtasks);

    }

    private void storeAllReservations(Collection<Reservations> reservationtasks) {
        Properties properties = new Properties();
        AtomicInteger i = new AtomicInteger(0);
        reservationtasks.forEach(
                reservations -> {
                    properties.setProperty("Reservations."+ i.incrementAndGet() + ".firstName", reservations.getFirstName());
                    properties.setProperty("Reservations."+ i.get() + ".lastName", reservations.getLastName());
                    properties.setProperty("Reservations."+ i.get() + ".eMail", reservations.getEmail());
                    properties.setProperty("Reservations."+ i.get() + ".phoneNumber", reservations.getPhoneNumber());
                    properties.setProperty("Reservations."+ i.get() + ".userId",""+ reservations.getUserId());
                    properties.setProperty("Reservations."+ i.get() + ".date", reservations.getDate());
                    properties.setProperty("Reservations."+ i.get() + ".time", reservations.getTime());
                    properties.setProperty("Reservations."+ i.get() + ".rSeats",""+ reservations.getrSeats());
                    properties.setProperty("Reservations."+ i.get() + ".restaurantId",""+ reservations.getRestaurantId());
                    properties.setProperty("Reservations."+ i.get() + ".rName", reservations.getrName());
                    properties.setProperty("Reservations."+ i.get() + ".rZipcode",""+ reservations.getrZipcode());
                    properties.setProperty("Reservations."+ i.get() + ".rAddress", reservations.getrAddress());
                    properties.setProperty("Reservations."+ i.get() + ".rPhoneNumber", reservations.getrPhoneNumber());
                    properties.setProperty("Reservations."+ i.get() + ".rEmail", reservations.getrEmail());
                }
        );
        try{
            properties.store(new FileOutputStream(fileName), "Store data to file.");
        }catch (Exception e){
            e.printStackTrace();
        }


    }



    public Collection<Reservations> getAllReservation(Reservations reservation) {
        List<Reservations> reservationtasks = new ArrayList<>();

        Properties properties = new Properties();
        int i = 1;
        try {
            properties.load(new FileInputStream(fileName));

            while (properties.containsKey("Reservations." + i + ".firstName")) {
                System.out.println("Bin in der list.");
                reservationtasks.add(
                        new Reservations(
                                properties.getProperty("Reservations." + i + ".firstName"),
                                properties.getProperty("Reservations." + i + ".lastName"),
                                properties.getProperty("Reservations." + i + ".email"),
                                properties.getProperty("Reservations." + i + ".phoneNumber"),
                                Integer.parseInt(properties.getProperty("Reservations." + i + ".userIed")),
                                properties.getProperty("Reservations." + i + ".date"),
                                properties.getProperty("Reservations." + i + ".time"),
                                Integer.parseInt(properties.getProperty("Reservations." + i + ".rSeats")),
                                Integer.parseInt(properties.getProperty("Reservations." + i + ".restaurantId")),
                                properties.getProperty("Reservations." + i + ".rName"),
                                Integer.parseInt(properties.getProperty("Reservations." + i + ".rZipcode")),
                                properties.getProperty("Reservations." + i + ".rAddress"),
                                properties.getProperty("Reservations." + i + ".rPhoneNumber"),
                                properties.getProperty("Reservations." + i + ".rEmail"))
                );
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return reservationtasks;
    }



}
