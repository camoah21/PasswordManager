import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class PasswordListFrame extends JFrame {

    public PasswordListFrame(String webUrl, String webName, String webUName, String webPassword) {
        setTitle("Password Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        add(panel);

        panel.add(new JLabel("Website URL:"));
        panel.add(new JLabel(webUrl));

        panel.add(new JLabel("Website Name:"));
        panel.add(new JLabel(webName));

        panel.add(new JLabel("Website Username:"));
        panel.add(new JLabel(webUName));

        panel.add(new JLabel("Website Password:"));
        panel.add(new JLabel(webPassword));

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
