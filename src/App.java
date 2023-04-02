import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class App {
    public static void main(String[] args) {
        new App().start();
    }

    public void start() {
        Login log = new Login();
        log.setBounds(400, 200, 500, 300);
        log.setVisible(true);
        log.butt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                boolean matched = false;
                String text3 = log.text1.getText().toString();
                String text6 = log.text2.getText().toString();

                try {
                    connectionUA con = new connectionUA();
                    if (con.search(text3, text6))
                        matched = true;
                } catch (Exception e) {
                }
                if (matched) {
                    log.label2.setText("Welcome to the Password Manager");
                    PMFrame pmFrame = new PMFrame();
                } else
                    log.label2.setText("Username or password is incorrect");
            }
        });
    }
}
