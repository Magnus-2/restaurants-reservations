package com.dhbw.RestaurantsReservation.dataManager;

import com.dhbw.RestaurantsReservation.model.reservations.Reservations;
import com.dhbw.RestaurantsReservation.model.restaurant.Restaurant;
import com.dhbw.RestaurantsReservation.model.user.User;
import com.dhbw.RestaurantsReservation.model.task.Task;

import java.util.Collection;

public interface TaskManager {


    Collection<Task> getAllTasks(User user);
    void addTask(Task task, User user);


    Collection<Restaurant> getAllRestaurants(Restaurant restaurant);
    void addRestaurant(Restaurant restaurant);


    Collection<User> getAllUsers(User user);
    void addUser(User user);
    boolean loginUser(User user);


    Collection<Reservations> getAllReservation(Reservations reservations);
    void addReservations(Reservations reservation);


    // TODO
    // removeTask, getTasksInOrder, getTaskByTaskID, ...

    // TODO
    // Make the TaskManager handling students.

}
