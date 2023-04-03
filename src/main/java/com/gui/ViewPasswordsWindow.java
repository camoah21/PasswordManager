package main.java.com.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;


import javax.swing.JLabel;

import main.java.com.example.Password;
import main.java.com.example.PasswordDAO;

public class ViewPasswordsWindow extends JFrame {
    private PasswordDAO passwordDAO;
    private String username;
    private JTable table;
    private DefaultTableModel model;

    public ViewPasswordsWindow(PasswordDAO passwordDAO, String username){
        this.passwordDAO = passwordDAO;
        this.username = username;
        initComponents();
        try {
            populateTable();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());

        model = new DefaultTableModel(new String[]{"Website URL", "Website Name", "Website Username", "Website Password", "Edit", "Delete"}, 0);
        table = new JTable(model);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserOptionsWindow userOptionsWindow = new UserOptionsWindow(passwordDAO, username);
                userOptionsWindow.setVisible(true);
                dispose();
            }
        }); 
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(new JLabel(), BorderLayout.NORTH);
        bottomPanel.add(new JLabel(), BorderLayout.EAST);
        bottomPanel.add(backButton, BorderLayout.SOUTH);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        
        add(panel);
        setTitle("View Passwords");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }

    private void populateTable() throws SQLException{
        try {
            List<Password> passwords = passwordDAO.getUserPasswords(username);
            for (Password password : passwords) {
    
                JButton editButton = new JButton("Edit");
                editButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        EditPasswordWindow editPasswordWindow = new EditPasswordWindow(passwordDAO, password, username);
                        editPasswordWindow.setVisible(true);
                        dispose();
                    }
                });
    
                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int row = table.getSelectedRow();
                        if (row >= 0) {
                            Password selectedPassword = passwords.get(row);
                            try {
                                passwordDAO.deleteUserPassword(selectedPassword.getId());
                                model.removeRow(row);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                // Handle the exception appropriately
                            }
                        }
                    }
                });
                
    
                // Create a new array with the buttons as the last two elements
                Object[] row = new Object[]{password.getWebsiteUrl(), password.getWebsiteName(),
                        password.getWebsiteUsername(), password.getWebsitePassword(), editButton, deleteButton};
    
                // Add the row to the table model
                model.addRow(row);
            }
    
            // Set the button renderer and editor for the "Edit" and "Delete" columns
            TableColumn editColumn = table.getColumnModel().getColumn(4);
            editColumn.setCellRenderer(new JButtonRenderer());
            editColumn.setCellEditor(new JButtonEditor(new JCheckBox()));
    
            TableColumn deleteColumn = table.getColumnModel().getColumn(5);
            deleteColumn.setCellRenderer(new JButtonRenderer());
            deleteColumn.setCellEditor(new JButtonEditor(new JCheckBox()));
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    class JButtonRenderer implements TableCellRenderer {
        private JButton button;
    
        public JButtonRenderer() {
            button = new JButton();
        }
    
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof JButton)
                button = (JButton)value;
            return button;
        }
    }
    
    class JButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean isPushed;
    
        public JButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }
    
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            if (value instanceof JButton) {
                button = (JButton) value;
                label = button.getText();
            } else {
                label = (value == null) ? "" : value.toString();
                button.setText(label);
            }
            isPushed = true;
            return button;
        }
    
        public Object getCellEditorValue() {
            if (isPushed) {
                // Perform action when button is pushed
            }
            isPushed = false;
            return label;
        }
    
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
    
}