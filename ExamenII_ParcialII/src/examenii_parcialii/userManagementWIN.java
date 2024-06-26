package examenii_parcialii;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author telip
 */
public class userManagementWIN extends JFrame{
    private JPanel panel;
    private JList<String> userList;
    public PSNUsers psn = new PSNUsers();

    public userManagementWIN() {
        panel = new JPanel();
        add(panel);
        panel.setLayout(new BorderLayout());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);

        String[] users = getUsersFromFile("users.psn");

        userList = new JList<>(users);
        JScrollPane scrollPane = new JScrollPane(userList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton addBTN = new JButton("Add");
        JButton deactivateBTN = new JButton("Deactivate");
        JButton backBTN = new JButton("Back");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addBTN);
        buttonPanel.add(deactivateBTN);
        buttonPanel.add(backBTN);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        deactivateBTN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deactivateBTNActionPerformed(e);
            }
        });

        backBTN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                backBTNActionPerformed(e);
            }
        });

        addBTN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBTNActionPerformed(e);
            }
        });

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new userManagementWIN());
    }

    private void backBTNActionPerformed(java.awt.event.ActionEvent evt) {
        new mainWIN().setVisible(true);
        this.dispose();
    }

    private void addBTNActionPerformed(java.awt.event.ActionEvent evt) {
        String username = JOptionPane.showInputDialog(null, "Username: ");
        if (username != null && !username.isEmpty()) {
            if (psn.addUser(username)) {
                JOptionPane.showMessageDialog(null, "User added successfully.");
                updateUsersList();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add user. User already exists.");
            }
        }
    }

    private void deactivateBTNActionPerformed(java.awt.event.ActionEvent evt) {
        String selectedUser = userList.getSelectedValue();
        if (selectedUser != null) {
            psn.deactivateUser(selectedUser);
            JOptionPane.showMessageDialog(null, "User deactivated successfully.");
            updateUsersList();
        } else {
            JOptionPane.showMessageDialog(null, "Please select a user to deactivate.");
        }
    }

    private void updateUsersList() {
        String[] users = getUsersFromFile("users.psn");
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String user : users) {
            model.addElement(user);
        }
        userList.setModel(model);
    }

    private String[] getUsersFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            StringBuilder users = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                users.append(line).append("\n");
            }
            return users.toString().split("\n");
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }
    
}
