package main.java.com.app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import static org.junit.Assert.*;

public class PasswordDAOTest {

    private DatabaseConnection databaseConnection;
    private PasswordDAO passwordDAO;

    @Before
    public void setUp() throws SQLException {
        // Create an in-memory H2 database connection
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        databaseConnection = new DatabaseConnection(connection);

        // Create the userPasswords table
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE userPasswords (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "websiteUrl VARCHAR(255)," +
                    "websiteName VARCHAR(255)," +
                    "websiteUsername VARCHAR(255)," +
                    "websitePassword VARCHAR(255)," +
                    "userId VARCHAR(255))");
        }

        passwordDAO = new PasswordDAO(databaseConnection);
    }

    @After
    public void tearDown() throws SQLException {
        // Drop the userPasswords table and close the database connection
        try (Statement stmt = databaseConnection.getConnection().createStatement()) {
            stmt.execute("DROP TABLE userPasswords");
        }
        databaseConnection.getConnection().close();
    }

    @Test
    public void testPasswordDAO() throws SQLException {
        String websiteUrl = "https://example.com";
        String websiteName = "Example";
        String websiteUsername = "user123";
        String websitePassword = "myp@ssw0rd";
        String userId = "userId123";

        // Test addUserPassword
        passwordDAO.addUserPassword(websiteUrl, websiteName, websiteUsername, websitePassword, userId);

        // Test getUserPasswords
        List<Password> passwords = passwordDAO.getUserPasswords(userId);
        assertEquals(1, passwords.size());

        Password retrievedPassword = passwords.get(0);
        assertEquals(websiteUrl, retrievedPassword.getWebsiteUrl());
        assertEquals(websiteName, retrievedPassword.getWebsiteName());
        assertEquals(websiteUsername, retrievedPassword.getWebsiteUsername());
        assertEquals(websitePassword, retrievedPassword.getWebsitePassword());

        // Test updateUserPassword
        String newWebsiteUrl = "https://newexample.com";
        String newWebsiteName = "New Example";
        String newWebsiteUsername = "user456";
        String newWebsitePassword = "newP@ssw0rd";

        retrievedPassword.setWebsiteUrl(newWebsiteUrl);
        retrievedPassword.setWebsiteName(newWebsiteName);
        retrievedPassword.setWebsiteUsername(newWebsiteUsername);
        retrievedPassword.setWebsitePassword(newWebsitePassword);

        passwordDAO.updateUserPassword(retrievedPassword);

        List<Password> updatedPasswords = passwordDAO.getUserPasswords(userId);
        assertEquals(1, updatedPasswords.size());

        Password updatedPassword = updatedPasswords.get(0);
        assertEquals(newWebsiteUrl, updatedPassword.getWebsiteUrl());
        assertEquals(newWebsiteName, updatedPassword.getWebsiteName());
        assertEquals(newWebsiteUsername, updatedPassword.getWebsiteUsername());
        assertEquals(newWebsitePassword, updatedPassword.getWebsitePassword());

        // Test deleteUserPassword
        passwordDAO.deleteUserPassword(retrievedPassword.getId());
        List<Password> deletedPasswords = passwordDAO.getUserPasswords(userId);
        assertTrue(deletedPasswords.isEmpty());
    }
}
