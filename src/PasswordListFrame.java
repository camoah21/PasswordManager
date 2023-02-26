import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PasswordListFrame extends JFrame {

    private JTable passwordTable;
    private DefaultTableModel passwordTableModel;
    private SQLIntegration sqlIntegration;

    public PasswordListFrame(List<String> passwords) {

        this.sqlIntegration = sqlIntegration;

        JPanel panel = new JPanel();
        panel.setLayout(null);

        this.add(panel);
        this.setSize(800, 500);
        this.setTitle("Password List");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        passwordTableModel = new DefaultTableModel(new String[] {"Website URL", "Website Name", "Username", "Password", "Delete"}, 0);
        passwordTable = new JTable(passwordTableModel);
        passwordTable.setBounds(10, 10, 780, 450);
        
        //Add delete button to each row
        passwordTable.getColumn("Delete").setCellRenderer(new ButtonRenderer());
        passwordTable.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox()));
        passwordTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = passwordTable.getSelectedRow();
                int col = passwordTable.getSelectedColumn();
                if (col == passwordTable.getColumn("Delete").getModelIndex()) {
                    int passwordID = (int)passwordTableModel.getValueAt(row, 4);
                    sqlIntegration.deletePassword(passwordID);
                    passwordTableModel.removeRow(row);
                }
            }
        });

        panel.add(passwordTable);

        String webURL = new String();
        String webName = new String();
        String webUName = new String();
        String webUPassword = new String();


        for (String password : passwords) {
            passwordTableModel.addRow(new String[] {webURL, webName, webUName, webUPassword, password});
        }

        this.setVisible(true);
    }

}



    





