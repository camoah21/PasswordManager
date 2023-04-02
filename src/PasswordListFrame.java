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

        JButton editPasswordButton = new JButton("Edit Password");
        editPasswordButton.setBounds(160, 210, 140, 25);
        panel.add(editPasswordButton);
        editPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == editPasswordButton) {
                    new PasswordEditFrame(webUrl, webName, webUName, webPassword);
                }
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
