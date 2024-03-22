package examenii_parcialii;

import examenii_parcialii.HashTable.Trophy;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author telip
 */
public class PSNUsers {
    HashTable users;
    RandomAccessFile RAF;
    RandomAccessFile psn;
    long size=0;
    
  public PSNUsers(){ 
       try{
           RAF= new RandomAccessFile("users.psn","rw");
           psn=new RandomAccessFile("psn","rw");
           reloadHashTable();
       }catch(IOException e){
           e.printStackTrace();
       }
    }
    
    private void reloadHashTable(){
        size=0;
        
        try{
            users= new HashTable();
            RAF.seek(0);
            
            while(RAF.getFilePointer()<RAF.length()){
                String username=RAF.readUTF();
                int points= RAF.readInt();
                int trophies= RAF.readInt();
                boolean active=RAF.readBoolean();
                
                if(active){
                   users.add(username, size);
                size++;
                }
                
            }
            
            }catch(IOException e){
            e.printStackTrace();
        }
            }
        
    
    
    public boolean addUser(String username){
        
        if(users.search(username)!=-1){
            return false;
        }
        
        try{
            
            RAF.seek(RAF.length());
            RAF.writeUTF(username);
            RAF.writeInt(0);
            RAF.writeInt(0);
            RAF.writeBoolean(true);
            users.add(username, size);
            size++;
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }  
    }
    
    public void deactivateUser(String username){
        
       if(users.search(username)!=-1){
           try{
               RAF.seek(0);
               while(RAF.getFilePointer()<RAF.length()){
                   String user= RAF.readUTF();
                   RAF.skipBytes(8);
                   long pos=RAF.getFilePointer();
                   boolean active=RAF.readBoolean();
                   if(user.equals(username)){
                       RAF.seek(pos);
                        RAF.writeBoolean(false);
                        users.remove(username);
                }
               }
           }catch(IOException e){
               e.printStackTrace();
           }
       }
       else JOptionPane.showInternalMessageDialog(null, "No existe ese usuario");
       
    }
    public void addTrophieTo(String username,String trophyGame,String trophyName,Trophy type){
        
        if(users.search(username)!=-1){
            try{
                psn.seek(psn.length());
                psn.writeUTF(username);
                psn.writeUTF(type.name());
                psn.writeUTF(trophyGame);
                psn.writeUTF(trophyName);
                Date date= new Date();
                psn.writeUTF(date.toString());
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        
    }
    public String playerInfo(String username){
        
        if(users.search(username)!=-1){
            long posUser=users.search(username);
            Entry tmp=users.inicio;
            while(tmp!=null){
                if(posUser==tmp.pos){
                    String info="Username: "+username+" "+getInfoinRAF(username);
                    return info;
                    
                }
            }
        }
        return "";
        
    }
    public String getInfoinRAF(String username){
        String info="";
        try{RAF.seek(0);
        psn.seek(0);
        while(RAF.getFilePointer()<RAF.length()){
            String user=RAF.readUTF();
            int points=RAF.readInt();
            int trophies=RAF.readInt();
            boolean active=RAF.readBoolean();
            if(user.equals(username)){
            info+="Puntos: "+points+" Trofeos: "+trophies+" Estado: "+active;
            RAF.close();
            }
        }
        while(psn.getFilePointer()<psn.length()){
        String user=psn.readUTF();
        String type=psn.readUTF();
        String gameName=psn.readUTF();
        String trophyName=psn.readUTF();
        String fecha=psn.readUTF();
        if(user.equals(username)){
        info+="\nTROFEOS:\n"+"Fecha: "+fecha+" Tipo: "+type+" Juego: "+gameName+" Descripcion: "+trophyName;
        psn.close();
        }
        }
        
        }catch(IOException e){
                e.printStackTrace();
            }
        
    return info;}
    public String show(){
        Entry tmp=users.inicio;
        String users="";
        if(this.users.inicio==null){
            return "";
        }
        while(tmp!=null){
            users+=tmp.username+"\n";
        }
        return users;
    }
}
    

