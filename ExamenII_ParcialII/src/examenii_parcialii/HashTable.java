package examenii_parcialii;

/**
 *
 * @author telip
 */
public class HashTable {
    
    Entry inicio;
    long size=0;
    
    public void add(String username,long pos){
        Entry newEntry= new Entry(username,pos);
        if(search(username)!=-1){
            return;
        }
        if(inicio==null){
            inicio = newEntry;
            return;
        }
        Entry tmp=inicio;
        while(tmp.next != null){
            tmp = tmp.next;
        }
        size++;
        tmp.next = newEntry;
        
        
    }
    
    public void remove(String username){
        Entry tmp =inicio;
        if(inicio==null){
            return;
        }
        if(username.equals(inicio.username)){
            inicio= inicio.next;
            size--;
            return;
        }
        while(tmp.next!=null){
            if(username.equals(tmp.next.username)){
                tmp.next =tmp.next.next;
                size--;
                return;
            }
            tmp= tmp.next;
        }
    }
    
    public long search(String username){
        Entry tmp=inicio;
        long pos=0;
        if(inicio==null)
            return -1;
        
        while(tmp!=null){
            if(username.equals(tmp.username)){
                return pos;
            }
            pos++;
            tmp=tmp.next;
            
        }
        return -1; 
    }
    
    public enum Trophy{
        PLATINO(5),ORO(3),PLATA(2),BRONCE(1);
        public final int points;
        
        Trophy(int points){
            this.points=points;
        }
        
        public int getPoints(){
            return points;
        }
    }
    
    public Entry getInicio() {
        return inicio;
    }
    
}
