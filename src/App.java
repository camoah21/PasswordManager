import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//GZA
public class App {
    public String webURL, webName, webUName, webUPassword, PMUsername, PMPassword;

    public static void main(String[] args) {
        new App().start();
    }

    public void start() {
        Login log = new Login();
        log.setBounds(400, 200, 500, 300);
        log.setVisible(true);
        /*
         * PMwindow.button.addActionListener(new ActionListener() {
         * 
         * @Override
         * public void actionPerformed(ActionEvent e) {
         * if (e.getSource() == PMwindow.button) {
         * webURL = PMwindow.urlText.getText();
         * webName = PMwindow.websiteNameText.getText();
         * webUName = PMwindow.webUsernameText.getText();
         * webUPassword = PMwindow.webPasswordText.getText();
         * PMUsername = PMwindow.PMUsernameText.getText();
         * PMPassword = PMwindow.PMPasswordText.getText();
         * SQLIntegration sqlIntegration = new SQLIntegration();
         * sqlIntegration.executeSQL(webURL, webName, webUName, webUPassword,
         * PMUsername, PMPassword);
         * }
         * }
         * });
         */
    }

}
