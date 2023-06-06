/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managementsystempackage.model;

import java.time.LocalDate;

/**
 *
 * @author renza
 */
public class CalendarBox {
    private int boxNumber;
    private LocalDate date;

    public CalendarBox(int boxNumber, LocalDate date) {
        this.boxNumber = boxNumber;
        this.date = date;
    }
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    private boolean isCurrentDay;


    
    public int getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(int boxNumber) {
        this.boxNumber = boxNumber;
    }

    public boolean isIsCurrentDay() {
        return isCurrentDay;
    }

    public void setIsCurrentDay(boolean isCurrentDay) {
        this.isCurrentDay = isCurrentDay;
    }
    
    
    
}
