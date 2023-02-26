import java.util.ArrayList;

public class User {
    private int id;
    private String Username;
    private String Password;
    private String firstname;
    private String lastname;
    private ArrayList<password> passwords = new ArrayList<>();

    public User(int Userid) {
        this.id = Userid;
        SQLIntegration.PasswordSQL(this.id);
        System.out.println(passwords);
    }

    public User() {

    }

    public User(int id2, String userName2, String password2, String firstname2, String lastname2) {
        this.id = id2;
        this.Username = userName2;
        this.Password = password2;
        this.firstname = firstname2;
        this.lastname = lastname2;
        this.updatePasswords();
    }

    public int getID() {
        return this.id;
    }

    public void updatePasswords() {
        SQLIntegration.PasswordSQL(this.id);
    }

    public ArrayList<password> getPasswords() {
        return this.passwords;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
