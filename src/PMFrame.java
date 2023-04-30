import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PMFrame extends JFrame implements ActionListener{

    JLabel urlLabel;
    JTextField urlText;
    JLabel websiteNameLabel;
    JTextField websiteNameText;
    JLabel webUsernameLabel;
    JTextField webUsernameText;
    JLabel webPasswordLabel;
    JPasswordField webPasswordText;
    JLabel PMUsernameLabel;
    JTextField PMUsernameText;
    JButton button;
    JButton viewPasswordsButton;
    JLabel PMPasswordLabel;
    JPasswordField PMPasswordText;

    public String webURL, webName, webUName, webUPassword, PMUsername, PMPassword;
    public String website, username;
    

    PMFrame() {

        JPanel panel = new JPanel();
        panel.setLayout(null);

        this.add(panel);
        this.setSize(600, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Password Manager");
        this.setVisible(true);
        

        urlLabel = new JLabel("Website URL: ");
        urlLabel.setBounds( 10, 20, 120, 25);
        panel.add(urlLabel);

        urlText = new JTextField(20);
        urlText.setBounds(200, 20, 300, 25);
        panel.add(urlText);

        websiteNameLabel = new JLabel("Website Name: ");
        websiteNameLabel.setBounds( 10, 50, 120, 25);
        panel.add(websiteNameLabel);

        websiteNameText = new JTextField(20);
        websiteNameText.setBounds(200, 50, 300, 25);
        panel.add(websiteNameText);

        webUsernameLabel = new JLabel("Website Username: ");
        webUsernameLabel.setBounds( 10, 80, 120, 25);
        panel.add(webUsernameLabel);

        webUsernameText = new JTextField(20);
        webUsernameText.setBounds(200, 80, 300, 25);
        panel.add(webUsernameText);

        webPasswordLabel = new JLabel("Website Password: ");
        webPasswordLabel.setBounds( 10, 110, 120, 25);
        panel.add(webPasswordLabel);

        webPasswordText = new JPasswordField();
        webPasswordText.setBounds(200, 110, 300, 25);
        panel.add(webPasswordText);

        PMUsernameLabel = new JLabel("Password Manager Username: ");
        PMUsernameLabel.setBounds( 10, 140, 200, 25);
        panel.add(PMUsernameLabel);

        PMUsernameText = new JTextField();
        PMUsernameText.setBounds(200, 140, 300, 25);
        panel.add(PMUsernameText);

        button = new JButton("Save Password");
        button.setBounds(10, 210, 140, 25);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==button) {
                    webURL = urlText.getText();
                    webName = websiteNameText.getText();
                    webUName = webUsernameText.getText();
                    webUPassword = webPasswordText.getText();
                    PMUsername = PMUsernameText.getText();
                    SQLIntegration sqlIntegration = new SQLIntegration();
                    sqlIntegration.executeSQL(webURL, webName, webUName, webUPassword, PMUsername, PMPassword);
                }
            }
        });

        viewPasswordsButton = new JButton("View Passwords");
        viewPasswordsButton.setBounds(160, 210, 140, 25);
        panel.add(viewPasswordsButton);
        viewPasswordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == viewPasswordsButton) {
                    String webUrl = urlText.getText();
                    String webName = websiteNameText.getText();
                    String webUName = webUsernameText.getText();
                    char[] password = webPasswordText.getPassword();
                    String webPassword = new String(password);
                    SQLIntegration.updateSQL(webUrl, webName, webUName, webPassword, website, username);
                    new PasswordListFrame(webUrl, webName, webUName, webPassword);
                }
            }
        });
          
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    

}


