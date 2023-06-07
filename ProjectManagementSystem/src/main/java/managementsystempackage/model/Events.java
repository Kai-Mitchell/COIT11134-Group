package managementsystempackage.model;
/**
 *
 * @author gomez
 */
import java.io.Serializable;
import java.util.Date;


public class Events implements Serializable{

    private int eventID;
    private String eventName;
    private Date start;
    private Date end;
    private static int nextEventID = 1001;

    public Events(String eventName,Date start,Date end)
    {
        setEventID();
        this.eventName = eventName;
        this.start = start;
        this.end = end;

    }


    public int getEventID() {
        return eventID;
    }
    
    public int getNextEventID(){
        return nextEventID;
    }

    public String getEventName() {
        return eventName;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEventID() {
        eventID = nextEventID;
        nextEventID++;
    }
    
    public static void setNextEventID(int nextEventID) {
        Events.nextEventID = nextEventID;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
    
      
}
