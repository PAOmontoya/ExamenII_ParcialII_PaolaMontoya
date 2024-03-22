package examenii_parcialii;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author telip
 */
public class infoWIN extends JFrame{
    private JPanel panel;
    public PSNUsers psn= new PSNUsers();
    private JLabel usernameLABEL = new JLabel("Username:");
    private JTextField usernameFIELD = new JTextField(20);
    private JButton findBTN = new JButton("Find");
    private JLabel infoLABEL= new JLabel("Player Info:                                                                        ");
    private JButton backBTN= new JButton("Back");
    

    public infoWIN() {
        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        panel.add(usernameLABEL);
        panel.add(usernameFIELD);
        panel.add(findBTN);
        panel.add(infoLABEL);
        panel.add(backBTN);

        this.add(panel);

        this.setLocationRelativeTo(null);
        this.setSize(400, 200);
        this.setVisible(true);
        
        backBTN.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                backBTNActionPerformed(e);
            }
        }
        );
        
        findBTN.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                findBTNActoinPerformed(e);
            }
        }
        );
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    private void backBTNActionPerformed(java.awt.event.ActionEvent evt){
        new mainWIN().setVisible(true);
        this.dispose();
    }
    
    private void findBTNActoinPerformed(java.awt.event.ActionEvent evt){
        String user = usernameFIELD.getText();
    String userInfo = psn.playerInfo(user);
    StringBuilder sb = new StringBuilder();
    sb.append(userInfo).append("\n");
    try {
        psn.psn.seek(0);
        int puntos = 0;
        int trofeos = 0;
        while (psn.psn.getFilePointer() < psn.psn.length()) {
            String username = psn.psn.readUTF();
            String type = psn.psn.readUTF();
            switch (type) {
                case "PLATINO":
                    puntos += 5;
                    break;
                case "ORO":
                    puntos += 3;
                    break;
                case "PLATA":
                    puntos += 2;
                    break;
                case "BRONCE":
                    puntos += 1;
                    break;
            }
            String gameName = psn.psn.readUTF();
            String trophyName = psn.psn.readUTF();
            String fecha = psn.psn.readUTF();
            trofeos++;
            sb.append("Fecha: ").append(fecha).append(" - Tipo: ").append(type)
                    .append(" - Juego: ").append(gameName).append(" - Descripción: ").append(trophyName).append("\n");
        }
        sb.append("Total puntos: ").append(puntos).append("\n");
        sb.append("Total trofeos: ").append(trofeos).append("\n");
        infoLABEL.setText(sb.toString());
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
