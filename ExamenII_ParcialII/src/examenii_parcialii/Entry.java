package examenii_parcialii;

/**
 *
 * @author telip
 */
public class Entry {
    String username;
    long pos;
    Entry next;

    public Entry(String username,long pos) {
        this.username=username;
        this.pos = pos;
        next=null;
    }
    
}
