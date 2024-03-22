package examenii_parcialii;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author telip
 */
public class addTrophyWIN extends JFrame {
    private JPanel panel;
    public PSNUsers psn= new PSNUsers();
    
     JLabel username= new JLabel("Username:");        
        JLabel gameName= new JLabel("Juego:");
        JLabel trophyName= new JLabel("Nombre del Trofeo:");
        JLabel trophyType= new JLabel("Trofeo:");
        
        JTextField usernameFIELD= new JTextField(25);
        JTextField gameNameFIELD= new JTextField(25);
        JTextField trophyNameFIELD= new JTextField(25);
        JComboBox<String> trophyTypeCB= new JComboBox<>();
        
        JButton addBTN = new JButton("Add");
        JButton backBTN = new JButton("Back");
    
    
    public addTrophyWIN(){
        panel= new JPanel();
        add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
       
        trophyTypeCB.addItem("PLATINO");
        trophyTypeCB.addItem("ORO");
        trophyTypeCB.addItem("PLATA");
        trophyTypeCB.addItem("BRONCE");

        panel.add(username);
        panel.add(usernameFIELD);
        
        panel.add(gameName);
        panel.add(gameNameFIELD);
        
        panel.add(trophyName);
        panel.add(trophyNameFIELD);
        
        panel.add(trophyType);
        panel.add(trophyTypeCB);
        
        panel.add(addBTN);
        panel.add(backBTN);
        
        addBTN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBTNActionPerformed(e);
            }
        });
        
        backBTN.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                backBTNActionPerformed(e);
            }
        });
        
    }
    
    private void addBTNActionPerformed(java.awt.event.ActionEvent evt){
        String username= usernameFIELD.getText();
        String trophyGame= gameNameFIELD.getText();
        String trophyName= trophyNameFIELD.getText();
        String type= trophyTypeCB.getSelectedItem().toString();
        HashTable.Trophy trophy= HashTable.Trophy.valueOf(type);
        if(username.isBlank()||trophyGame.isBlank()||trophyName.isBlank()){
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos");
        }
        else{psn.addTrophieTo(username, trophyGame, trophyName, trophy);
        new mainWIN().setVisible(true);
        this.dispose();
        }
    }
    
    private void backBTNActionPerformed(java.awt.event.ActionEvent evt){
        new mainWIN().setVisible(true);
        this.dispose();
    }
}
