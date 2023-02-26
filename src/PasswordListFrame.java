import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

public class PasswordListFrame extends JFrame {

    private JTable passwordTable;
    private DefaultTableModel passwordTableModel;
    JButton button;

    public PasswordListFrame(User user) {

        PMFrame PMwindow = new PMFrame(user);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        button = new JButton("Add Password");
        button.setBounds(10, 210, 140, 25);
        panel.add(button);
        panel.repaint();

        this.add(panel);
        this.setSize(800, 500);
        this.setTitle("Password List");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        passwordTableModel = new DefaultTableModel(
                new String[] { "Website URL", "Website Name", "Username", "Password" }, 0);
        passwordTable = new JTable(passwordTableModel);
        passwordTable.setBounds(10, 10, 780, 450);
        panel.add(passwordTable);
        panel.repaint();

        user.updatePasswords();
        ArrayList<password> passwords = user.getPasswords();
        String webURL = new String();
        String webName = new String();
        String webUName = new String();
        String webUPassword = new String();
        String Password = null;

        for (password password : passwords) {
            Password = password.getPassword();
            passwordTableModel.addRow(new String[] { webURL, webName, webUName, webUPassword, Password });
        }
        panel.repaint();

        this.setVisible(true);
    }

}
