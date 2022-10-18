package com.dhbw.RestaurantsReservation.dataManagerImpl;
import com.dhbw.RestaurantsReservation.dataManager.UserManager;
import com.dhbw.RestaurantsReservation.model.user.User;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PostgresUserManagerImpl implements UserManager {

    String databaseURL = "jdbc:postgresql://ec2-34-252-216-149.eu-west-1.compute.amazonaws.com:5432/d9ggrcte17fo7h";
    String username = "pbyofpxpstoaup";
    String password = "349a5066ac0eef81cd2f2771a0b722448f2b9222f4832d66a8e78be1627a006b";
    BasicDataSource basicDataSource;

    static PostgresUserManagerImpl postgresUserManager = null;

    private PostgresUserManagerImpl() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseURL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }

    static public PostgresUserManagerImpl getPostgresUserManagerImpl() {
        if (postgresUserManager == null)
            postgresUserManager = new PostgresUserManagerImpl();
        return postgresUserManager;
    }






    @Override
    public Collection<User> getAllUsers(User user) {

        List<User> users = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                users.add(
                        new User(
                                rs.getString("userName"),
                                rs.getString("userSureName"),
                                rs.getString("userPassword"),
                                rs.getString("eMail"),
                                rs.getInt("userID")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return users;
    }

    @Override
    public void addUser(User user1, User user2) {

        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into users (userName, userSureName, userPassword, eMail, userID) VALUES (" +
                    "'" + user2.getUserName() + "', " +
                    "'" + user2.getUserSureName() + "', " +
                    "'" + user2.getUserPassword() + "', " +
                    "'" + user2.geteMail() + "', " +
                    "'" + user2.getUserID() + "')";

            stmt.executeUpdate(udapteSQL);

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public void createTableUser() {

        // Be carefull: It deletes data if table already exists.
        //

        Statement stmt = null;
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            String dropTable = "DROP TABLE IF EXISTS users";
            stmt.executeUpdate(dropTable);

            String createTable = "CREATE TABLE users (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name varchar(100) NOT NULL, " +
                    "description varchar(250) NOT NULL, " +
                    "priority int NOT NULL)";
            stmt.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();


        }
    }
}
