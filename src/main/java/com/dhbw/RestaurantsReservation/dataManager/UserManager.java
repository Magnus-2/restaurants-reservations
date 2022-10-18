package com.dhbw.RestaurantsReservation.dataManager;

import com.dhbw.RestaurantsReservation.model.task.Task;
import com.dhbw.RestaurantsReservation.model.user.User;

import java.util.Collection;

public interface UserManager {
    // getAllStudents, getSpecificStudent, logStudentOn, logStudentOff, ...

    Collection<User> getAllUsers(User user);
    void addUser(User user1, User user2);
    //zum Probieren ob dennoch alles geht
}
