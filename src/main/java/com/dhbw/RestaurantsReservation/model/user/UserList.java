package com.dhbw.RestaurantsReservation.model.user;

import com.dhbw.RestaurantsReservation.dataManager.TaskManager;
import com.dhbw.RestaurantsReservation.dataManager.UserManager;
import com.dhbw.RestaurantsReservation.dataManagerImpl.PostgresTaskManagerImpl;
import com.dhbw.RestaurantsReservation.dataManagerImpl.PostgresUserManagerImpl;
import com.dhbw.RestaurantsReservation.model.task.Task;
import com.dhbw.RestaurantsReservation.model.user.User;

import java.util.Collection;

public class UserList {

    private User user;
    private Collection<User> users;

    public UserList() { }

    public UserList(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers() {
        UserManager userManager = PostgresUserManagerImpl.getPostgresUserManagerImpl();
        users = userManager.getAllUsers(new User("Max", "Muster", "test",
                "Muster@mail.com", 0));
        this.users = users;
    }

    @SuppressWarnings("deprecation")
    public void addUser(User user) {
        UserManager userManager = PostgresUserManagerImpl.getPostgresUserManagerImpl();
        userManager.addUser(user, new User("Max", "Muster", "test",
                "Muster@mail.com", 0));
    }

}
