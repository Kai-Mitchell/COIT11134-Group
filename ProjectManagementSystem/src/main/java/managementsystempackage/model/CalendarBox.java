/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managementsystempackage.model;

import java.time.LocalDate;


public class CalendarBox {
    private int boxNumber;
    private LocalDate date;
    private boolean isSunday;
    private boolean isCurrentDay;

    public CalendarBox() {
    }
    public CalendarBox(int boxNumber, LocalDate date) {
        this.boxNumber = boxNumber;
        this.date = date;
    }
      public int getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(int boxNumber) {
        this.boxNumber = boxNumber;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
   public boolean isSunday() {
        return isSunday;
    }

    public void setIsSunday(boolean isSunday) {
        this.isSunday = isSunday;
    }
  
    public boolean isIsCurrentDay() {
        return isCurrentDay;
    }

    public void setIsCurrentDay(boolean isCurrentDay) {
        this.isCurrentDay = isCurrentDay;
    }
    
    
    
    
}
