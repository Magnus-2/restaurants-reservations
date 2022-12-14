package com.dhbw.RestaurantsReservation.dataManagerImpl;

import com.dhbw.RestaurantsReservation.dataManager.TaskManager;
import com.dhbw.RestaurantsReservation.model.reservations.Reservations;
import com.dhbw.RestaurantsReservation.model.restaurant.Restaurant;
import com.dhbw.RestaurantsReservation.model.user.User;
import com.dhbw.RestaurantsReservation.model.task.Task;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PostgresTaskManagerImpl implements TaskManager {

    String databaseURL = "jdbc:postgresql://ec2-34-246-25-222.eu-west-1.compute.amazonaws.com:5432/d4mqokc6ki8nc8";
    String username = "pmaahduuykauhb";
    String password = "c85fee17ffab0a5eae1a44161e3dd90d2cc4b4ae239fa17ad1342677807b2362";
    BasicDataSource basicDataSource;
    BasicDataSource basicDataSource2;

    static PostgresTaskManagerImpl postgresTaskManager = null;

    private PostgresTaskManagerImpl() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseURL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        basicDataSource2 = new BasicDataSource();
        basicDataSource2.setUrl(databaseURL);
        basicDataSource2.setUsername(username);
        basicDataSource2.setPassword(password);
    }

    static public PostgresTaskManagerImpl getPostgresTaskManagerImpl() {
        if (postgresTaskManager == null)
            postgresTaskManager = new PostgresTaskManagerImpl();
        return postgresTaskManager;
    }






    @Override
    public Collection<Task> getAllTasks(User user) {

        List<Task> usertasks = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usertasks");
            while (rs.next()) {
                usertasks.add(
                        new Task(
                                rs.getString("name"),
                                rs.getString("surname"),
                                rs.getString("description"),
                                rs.getInt("priority")
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


        return usertasks;
    }

    @Override
    public void addTask(Task task, User user) {

        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into usertasks (name, surname, description, priority) VALUES (" +
                    "'" + task.getName() + "', " +
                    "'" + task.getSurname() + "', " +
                    "'" + task.getDescription() + "', " +
                    "'" + task.getPriority() + "')";

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


    public void createTableTask() {

        // Be carefull: It deletes data if table already exists.
        //

            // if chooser == ture an usertable is created
            Statement stmt = null;
            Connection connection = null;
            try {
                connection = basicDataSource.getConnection();
                stmt = connection.createStatement();

                String dropTable = "DROP TABLE IF EXISTS usertasks";
                //hier gegebenen Falls wieder auf usertasks ??ndern
                stmt.executeUpdate(dropTable);

                String createTable = "CREATE TABLE usertasks (" +
                        "id SERIAL PRIMARY KEY, " +
                        "name varchar(100) NOT NULL, " +
                        "surname varchar(250) NOT NULL, " +
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

    public void createTableRestaurant(){

        Statement stmtr = null;
        Connection connectionr = null;
        try {
            connectionr = basicDataSource2.getConnection();
            stmtr = connectionr.createStatement();

            String dropTable = "DROP TABLE IF EXISTS restaurant";
            stmtr.executeUpdate(dropTable);

            String createTable = "CREATE TABLE restaurant(" +
                    "id SERIAL PRIMARY KEY, " +
                    "rName varchar(100) NOT NULL, " +
                    "rSeats int NOT NULL, " +
                    "rZipcode int NOT NULL, " +
                    "rAddress varchar(250) NOT NULL, " +
                    "oHMo varchar(10) NOT NULL, " +
                    "oHTu varchar(10) NOT NULL, " +
                    "oHWe varchar(10) NOT NULL, " +
                    "oHTh varchar(10) NOT NULL, " +
                    "oHFr varchar(10) NOT NULL, " +
                    "oHSa varchar(10) NOT NULL, " +
                    "oHSu varchar(10) NOT NULL, " +
                    "rCategory varchar(250) NOT NULL, " +
                    "rPhone int NOT NULL, " +
                    "rEmail varchar(250) NOT NULL, " +
                    "rPassword varchar(250) NOT NULL)";
            stmtr.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmtr.close();
            connectionr.close();
        } catch (SQLException e) {
            e.printStackTrace();


        }


    }

    @Override
    public Collection<Restaurant> getAllRestaurants(Restaurant restaurant) {
        List<Restaurant> restauranttasks = new ArrayList<>();
        Statement stmtr = null;
        Connection connectionr = null;

        try {
            connectionr = basicDataSource2.getConnection();
            stmtr = connectionr.createStatement();
            ResultSet rsr = stmtr.executeQuery("SELECT * FROM restaurant");
            while (rsr.next()) {
                restauranttasks.add(
                        new Restaurant(
                                rsr.getString("rName"),
                                rsr.getInt("rSeats"),
                                rsr.getInt("rZipcode"),
                                rsr.getString("rAddress"),
                                rsr.getString("oHMo"),
                                rsr.getString("oHTu"),
                                rsr.getString("oHWe"),
                                rsr.getString("oHTh"),
                                rsr.getString("oHFr"),
                                rsr.getString("oHSa"),
                                rsr.getString("oHSu"),
                                rsr.getString("rCategory"),
                                rsr.getInt("rPhone"),
                                rsr.getString("rEmail"),
                                rsr.getString("rPassword")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmtr.close();
            connectionr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return restauranttasks;
    }

    @Override
    public void addRestaurant(Restaurant restaurant) {
        Statement stmtr = null;
        Connection connectionr = null;

        try {
            connectionr = basicDataSource2.getConnection();
            stmtr = connectionr.createStatement();
            String udapteSQL = "INSERT into restaurant(rName, rSeats, rZipcode, rAddress," +
                    "oHMo, oHTu, oHWe, oHTh, oHFr, oHSa, oHSu," +
                    "rCategory, rPhone, rEmail, rPassword) VALUES (" +
                    "'" + restaurant.getrName() + "', " +
                    "'" + restaurant.getrSeats() + "', " +
                    "'" + restaurant.getrZipcode() + "', " +
                    "'" + restaurant.getrAddress() + "', " +
                    "'" + restaurant.getoHMo() + "', " +
                    "'" + restaurant.getoHTu() + "', " +
                    "'" + restaurant.getoHWe() + "', " +
                    "'" + restaurant.getoHTh() + "', " +
                    "'" + restaurant.getoHFr() + "', " +
                    "'" + restaurant.getoHSa() + "', " +
                    "'" + restaurant.getoHSu() + "', " +
                    "'" + restaurant.getrCategory() + "', " +
                    "'" + restaurant.getrPhone() + "', " +
                    "'" + restaurant.getrEmail() + "', " +
                    "'" + restaurant.getrPassword() + "')";

            stmtr.executeUpdate(udapteSQL);

            stmtr.close();
            connectionr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmtr.close();
            connectionr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void createTableUser() {

        Statement stmtu = null;
        Connection connectionu = null;
        try {
            connectionu = basicDataSource2.getConnection();
            stmtu = connectionu.createStatement();

            String dropTable = "DROP TABLE IF EXISTS usertable";
            stmtu.executeUpdate(dropTable);

            String tableEncryption = "CREATE EXTENSION pgcrypto";
            stmtu.executeUpdate(tableEncryption);

            String createTable = "CREATE TABLE usertable(" +
                    "id SERIAL PRIMARY KEY, " +
                    "firstName varchar(100) NOT NULL, " +
                    "lastName varchar(100) NOT NULL, " +
                    "email varchar(250) NOT NULL, " +
                    "phoneNumber varchar(50) NOT NULL, " +
                    "password varchar(250) NOT NULL)";
            stmtu.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmtu.close();
            connectionu.close();
        } catch (SQLException e) {
            e.printStackTrace();


        }

    }

    @Override
    public Collection<User> getAllUsers(User user) {
        List<User> usertask = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource2.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usertable");
            while (rs.next()) {
                usertask.add(
                        new User(
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getString("email"),
                                rs.getString("phoneNumber"),
                                rs.getString("password")
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


        return usertask;
    }

    @Override
    public void addUser(User user) {
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource2.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into usertable(firstName, lastName, email, phoneNumber, password) VALUES (" +
                    "'" + user.getFirstName() + "', " +
                    "'" + user.getLastName() + "', " +
                    "'" + user.getEMail() + "', " +
                    "'" + user.getPhoneNumber() + "', " +
                    "'" + user.getPassword() + "')";

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

    @Override
    public boolean loginUser(User user) {
        boolean check = false;
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT CASE WHEN email ='" + user.getEMail() + "' AND password='" + user.getPassword() + "' THEN '" + user.setCheck2("true") + "' ELSE '" +  user.setCheck2("false")  + "'  END AS CHECK FROM usertable;");
            while (rs.next()) {
                if (check == false){
                check = rs.getBoolean("check");
                 }else break;
            }

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
        return check;
    }

    @Override
    public Collection<Reservations> getAllReservation(Reservations reservations) {
        List<Reservations> reservationstask = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM reservationstable");
            while (rs.next()) {
                reservationstask.add(
                        new Reservations(
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getString("email"),
                                rs.getString("phoneNumber"),
                                rs.getInt("userId"),
                                rs.getString("date"),
                                rs.getString("time"),
                                rs.getInt("rSeats"),
                                rs.getInt("restaurantId"),
                                rs.getString("rName"),
                                rs.getInt("rZipcode"),
                                rs.getString("rAddress"),
                                rs.getString("rPhoneNumber"),
                                rs.getString("rEmail")
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


        return reservationstask;
    }

    @Override
    public void addReservations(Reservations reservation) {
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into reservationstable(firstName, lastName, email, phoneNumber, userId, date," +
                    "time, rSeats, restaurantId, rName, rZipcode, rAddress, rPhoneNumber, rEmail) VALUES (" +
                    "'" + reservation.getFirstName() + "', " +
                    "'" + reservation.getLastName() + "', " +
                    "'" + reservation.getEmail() + "', " +
                    "'" + reservation.getPhoneNumber() + "', " +
                    "'" + reservation.getUserId() + "', " +
                    "'" + reservation.getDate() + "', " +
                    "'" + reservation.getTime() + "', " +
                    "'" + reservation.getrSeats() + "', " +
                    "'" + reservation.getRestaurantId() + "', " +
                    "'" + reservation.getrName() + "', " +
                    "'" + reservation.getrZipcode() + "', " +
                    "'" + reservation.getrAddress() + "', " +
                    "'" + reservation.getrPhoneNumber() + "', " +
                    "'" + reservation.getrEmail() + "')";

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

    public void createTableReservations() {
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            String dropTable = "DROP TABLE IF EXISTS reservationstable";
            stmt.executeUpdate(dropTable);

            String createTable = "CREATE TABLE reservationstable(" +
                    "id SERIAL PRIMARY KEY, " +
                    "firstName varchar(100) NOT NULL, " +
                    "lastName varchar(100) NOT NULL, " +
                    "email varchar(250) NOT NULL, " +
                    "phoneNumber varchar(20) NOT NULL, " +
                    "userId varchar(5) NOT NULL, " +
                    "date varchar(100) NOT NULL, " +
                    "time varchar(100) NOT NULL, " +
                    "rSeats varchar(100) NOT NULL, " +
                    "restaurantId varchar(5) NOT NULL, " +
                    "rName varchar(100) NOT NULL, " +
                    "rZipcode varchar(100) NOT NULL, " +
                    "rAddress varchar(250) NOT NULL, " +
                    "rPhoneNumber varchar(20) NOT NULL, " +
                    "rEmail varchar(250) NOT NULL)";
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
