package managementsystempackage.model;

import java.util.Date;


public class Events {

    private int eventID;
    private String eventName;
    private Date start;
    private Date end;

    public Events(int eventID,String eventName,Date start,Date end)
    {
        this.eventID = eventID;
        this.eventName = eventName;
        this.start = start;
        this.end = end;

    }


    public int getEventID() {
        return eventID;
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

    public void setEventID(int eventID) {
        this.eventID = eventID;
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
