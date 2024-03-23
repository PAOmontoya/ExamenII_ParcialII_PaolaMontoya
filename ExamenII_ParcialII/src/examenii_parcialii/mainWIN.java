package examenii_parcialii;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author telip
 */
public class mainWIN extends javax.swing.JFrame{
    private JPanel panel;
    
    public mainWIN(){
        panel = new JPanel();
        add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 75);
        
        JButton usersBTN = new JButton("Users");
        JButton trophyBTN = new JButton("Add Trophy");
        JButton infoBTN = new JButton("Player Info");
        
        panel.add(usersBTN);
        panel.add(trophyBTN);
        panel.add(infoBTN);
        
        infoBTN.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                infoBTNActionPerformed(e);
            }
        });
        
        trophyBTN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTrophyBTNActionPerformed(e);
            }
        });
        
        usersBTN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                usersBTNActionPerformed(e);
            }
        });
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    private void addTrophyBTNActionPerformed(java.awt.event.ActionEvent evt) {
        new addTrophyWIN().setVisible(true);
        this.dispose();
    }
    
    private void infoBTNActionPerformed(java.awt.event.ActionEvent evt){
        new infoWIN().setVisible(true);
        this.dispose();
    }
    
    private void usersBTNActionPerformed(java.awt.event.ActionEvent evt){
        new userManagementWIN().setVisible(true);
        this.dispose();
    }
}
