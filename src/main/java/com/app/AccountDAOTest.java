package main.java.com.app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class AccountDAOTest {

    private DatabaseConnection databaseConnection;
    private AccountDAO accountDAO;

    @Before
    public void setUp() throws SQLException {
        // Create an in-memory H2 database connection
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        databaseConnection = new DatabaseConnection(connection);

        // Create the accountmanager table
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE accountmanager (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "username VARCHAR(255) UNIQUE," +
                    "firstname VARCHAR(255)," +
                    "lastname VARCHAR(255)," +
                    "password VARCHAR(255))");
        }

        accountDAO = new AccountDAO(databaseConnection);
    }

    @After
    public void tearDown() throws SQLException {
        // Drop the accountmanager table and close the database connection
        try (Statement stmt = databaseConnection.getConnection().createStatement()) {
            stmt.execute("DROP TABLE accountmanager");
        }
        databaseConnection.getConnection().close();
    }

    @Test
    public void testAccountDAO() throws SQLException {
        // Test addAccount and getAccount
        Account account = new Account("user123", "John", "Doe", "myp@ssw0rd");
        accountDAO.addAccount(account);
        Account retrievedAccount = accountDAO.getAccount("user123");

        assertEquals("user123", retrievedAccount.getUsername());
        assertEquals("John", retrievedAccount.getFirstName());
        assertEquals("Doe", retrievedAccount.getLastName());
        assertEquals("myp@ssw0rd", retrievedAccount.getPassword());

        // Test deleteAccount
        accountDAO.deleteAccount(retrievedAccount.getId());
        Account deletedAccount = accountDAO.getAccount("user123");
        assertNull(deletedAccount);
    }
}
