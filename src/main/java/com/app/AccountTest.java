package main.java.com.app;

import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
    @Test
    public void testAccount() {
        String username = "user123";
        String firstName = "John";
        String lastName = "Doe";
        String password = "myp@ssw0rd";

        // Test constructor and getters
        Account account = new Account(username, firstName, lastName, password);

        assertEquals(username, account.getUsername());
        assertEquals(firstName, account.getFirstName());
        assertEquals(lastName, account.getLastName());
        assertEquals(password, account.getPassword());

        // Test setters
        int id = 1;
        String newUsername = "user456";
        String newFirstName = "Jane";
        String newLastName = "Smith";
        String newPassword = "newP@ssw0rd";

        account.setId(id);
        account.setUsername(newUsername);
        account.setFirstName(newFirstName);
        account.setLastName(newLastName);
        account.setPassword(newPassword);

        assertEquals(id, account.getId());
        assertEquals(newUsername, account.getUsername());
        assertEquals(newFirstName, account.getFirstName());
        assertEquals(newLastName, account.getLastName());
        assertEquals(newPassword, account.getPassword());
    }
}
