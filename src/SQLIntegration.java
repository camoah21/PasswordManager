
/*
import java.sql.*;

public class SQLIntegration {

    private static String username = "admin";
    private static String password = "itsc4155group3";

    private static String connectionString = "jdbc:mysql://capstoneproject.ck3v6fmgoed4.us-east-2.rds.amazonaws.com/PasswordManager";
    private static Connection connection;
    private static Statement command;
    private static ResultSet data;
    public void executeSQL(String webURL, String webName, String webUName, String webUPassword, String PMUsername, String PMPassword) {
        try {
            connection = DriverManager.getConnection(connectionString, username, password);
            command = connection.createStatement();
            command.execute("INSERT INTO usersForPM VALUES (" + PMUsername + ", " + PMPassword + ")");
            command.execute("INSERT INTO userPasswords VALUES (" + webURL + ", " + webName + ", " + webUName + ", " + webUPassword + ", " + PMUsername + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}  	
*/
import java.sql.*;
import java.util.ArrayList;

public class SQLIntegration {

    private static String username = "admin";
    private static String password = "itsc4155group3";
    private static String connectionString = "jdbc:mysql://capstoneproject.ck3v6fmgoed4.us-east-2.rds.amazonaws.com/PasswordManager";

    public void createUserSQL(String username, String firstName, String lastName, String password) {
        try {
            Connection connection = DriverManager.getConnection(connectionString, username, password);

            // Insert into 'usersForPM' table using a prepared statement
            String insertUserSql = "INSERT INTO accountmanager (username, password, firstname, lastname) VALUES (?, ?, ?, ?)";
            PreparedStatement insertUserStmt = connection.prepareStatement(insertUserSql);
            insertUserStmt.setString(1, username);
            insertUserStmt.setString(2, password);
            insertUserStmt.setString(3, firstName);
            insertUserStmt.setString(4, lastName);
            insertUserStmt.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createPasswordSQL(int userid, String webURL, String webName, String webUName,
            String webUPassword) {
        try {
            Connection connection = DriverManager.getConnection(connectionString, username, password);
            // Insert into 'userPasswords' table using a prepared statement
            String insertPasswordSql = "INSERT INTO userPasswords (websiteURL, websiteName, webUsername, webPassword,userid) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertPasswordStmt = connection.prepareStatement(insertPasswordSql);
            insertPasswordStmt.setString(1, webURL);
            insertPasswordStmt.setString(2, webName);
            insertPasswordStmt.setString(3, webUName);
            insertPasswordStmt.setString(4, webUPassword);
            insertPasswordStmt.setInt(5, userid);
            insertPasswordStmt.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeSQL(String webURL, String webName, String webUName, String webUPassword, String PMUsername,
            String PMPassword) {
        try {
            Connection connection = DriverManager.getConnection(connectionString, username, password);

            // Insert into 'usersForPM' table using a prepared statement
            String insertUserSql = "INSERT INTO usersForPM (usernameForPM, userPasswordForPM) VALUES (?, ?)";
            PreparedStatement insertUserStmt = connection.prepareStatement(insertUserSql);
            insertUserStmt.setString(1, PMUsername);
            insertUserStmt.setString(2, PMPassword);
            insertUserStmt.executeUpdate();

            // Insert into 'userPasswords' table using a prepared statement
            String insertPasswordSql = "INSERT INTO userPasswords (websiteURL, websiteName, webUsername, webPassword, usernameForPM) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertPasswordStmt = connection.prepareStatement(insertPasswordSql);
            insertPasswordStmt.setString(1, webURL);
            insertPasswordStmt.setString(2, webName);
            insertPasswordStmt.setString(3, webUName);
            insertPasswordStmt.setString(4, webUPassword);
            insertPasswordStmt.setString(5, PMUsername);
            insertPasswordStmt.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateSQL(String webURL, String webName, String webUName, String webUPassword, int user,
            int passwordid) {
        try {
            Connection connection = DriverManager.getConnection(connectionString, username, password);

            // Insert into 'userPasswords' table using a prepared statement
            String updatePasswordSql = "UPDATE userPasswords SET websiteURL = ?, websiteName = ?, webUsername = ?, webPassword = ? WHERE userid = "
                    + user + " AND id = " + passwordid;
            PreparedStatement insertPasswordStmt = connection.prepareStatement(updatePasswordSql);
            insertPasswordStmt.setString(1, webURL);
            insertPasswordStmt.setString(2, webName);
            insertPasswordStmt.setString(3, webUName);
            insertPasswordStmt.setString(4, webUPassword);
            insertPasswordStmt.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteSQL(int passwordid) {
        try {
            Connection connection = DriverManager.getConnection(connectionString, username, password);

            // Insert into 'userPasswords' table using a prepared statement
            String deletePasswordSql = "delete From userPasswords WHERE id= " + passwordid;
            PreparedStatement insertPasswordStmt = connection.prepareStatement(deletePasswordSql);
            insertPasswordStmt.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User getUserSQL(String Username, String Password) {
        User user;
        try {
            Connection connection = DriverManager.getConnection(connectionString, username, password);

            String query = "SELECT id, username, password, firstname, lastname WHERE username = " + Username
                    + " AND password=" + Password;
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String userName = rs.getString(2);
                String password = rs.getString(3);
                String firstname = rs.getString(4);
                String lastname = rs.getString(5);
                user = new User(id, userName, password, firstname, lastname);
                System.out.println(user);
                connection.close();
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<password> PasswordSQL(int userid) {
        ArrayList<password> temp = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(connectionString, username, password);

            String query = "SELECT id, websiteName, websiteUrl, Password, userName from userPasswords WHERE userid = "
                    + userid;
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String websiteName = rs.getString(2);
                String websiteUrl = rs.getString(3);
                String Password = rs.getString(4);
                String Username = rs.getString(5);
                password password = new password(id, websiteName, websiteUrl, Username, Password);
                temp.add(password);
            }
            connection.close();
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

}