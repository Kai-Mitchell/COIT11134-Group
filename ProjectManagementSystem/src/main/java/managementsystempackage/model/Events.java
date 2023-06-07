package managementsystempackage.model;
/**
 *
 * @author gomez
 */
import java.io.Serializable;
import java.time.LocalDate;


public class Events implements Serializable{

    private int eventID;
    private String eventName;
    private LocalDate start;
    private LocalDate end;
    private static int nextEventID = 1001;

    public Events(String eventName,LocalDate start,LocalDate end)
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

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
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

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
    
      
}
