package managementsystempackage.model;
/**
 *
 * @author gomez
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Calendar {
    
    private LocalDate todaysDate;
    private LocalDate currentCalendarDate;
    private CalendarBox[] boxArray;
    private ReentrantLock lock;
    private DateTimeFormatter formatter;

    public Calendar() {
        formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        
        this.todaysDate = LocalDate.now();
        this.currentCalendarDate = LocalDate.now();
        this.boxArray = new CalendarBox[35];
        lock = new ReentrantLock();
    }

    public void displayEvent()
    {

    }
    
    public CalendarBox[] getCurrentMonthInfo(){
        this.currentCalendarDate = todaysDate;
        LocalDate currentMonthFirstDay = currentCalendarDate.with(TemporalAdjusters.firstDayOfMonth());
        
        
        return generateCalendarBoxData(currentMonthFirstDay);

    }
    
    public CalendarBox[] getPreviousMonthInfo(){
        // Get previous month
        LocalDate previousMonthFirstDay = currentCalendarDate.with(TemporalAdjusters.firstDayOfMonth())
                .minusMonths(1);
        this.currentCalendarDate = previousMonthFirstDay;
        
        generateCalendarBoxData(previousMonthFirstDay);
        return boxArray;

    }
    
    public CalendarBox[] getNextMonthInfo(){
        // Get next month
        LocalDate nextMonthFirstDay = this.currentCalendarDate.with(TemporalAdjusters.firstDayOfMonth())
                .plusMonths(1);
        this.currentCalendarDate = nextMonthFirstDay;
        
        generateCalendarBoxData(nextMonthFirstDay);
        return this.boxArray;
    }
    
    public String getMonthAndYear(){
        currentCalendarDate.format(formatter);
        String month = currentCalendarDate.getMonth().toString();
        int year = currentCalendarDate.getYear();
        return month+" "+year;
    }
    
    private CalendarBox[] generateCalendarBoxData(LocalDate date){
        //executing logic inside a separate thread
        date.format(formatter);
        CompletableFuture<CalendarBox[]> future = CompletableFuture.supplyAsync(() -> {

            
            lock.lock();//lock to prevent anyother thread from editing boxArray
            try {
                LocalDate currentDate = date.minusMonths(1);
                
                
                int weekday = date.getDayOfWeek().getValue()-1;
                
                int previousMonthLastDay = date.minusDays(1).getDayOfMonth();
                
                int lastDayOfCurrentMonth = date.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
                
                int dayCounter = weekday;
                
                if(weekday >= 1){
                    //fill boxes with date from last month
                    while(dayCounter >= 1){
                        int day = previousMonthLastDay - (dayCounter-1);
                        this.boxArray[weekday - dayCounter] = new CalendarBox(day, currentDate.withDayOfMonth(day));
                        dayCounter--;
                    }
                    currentDate = date;
                    dayCounter = 1;
                    //fill boxes with date from current month
                    for(int i = weekday; i < boxArray.length; i++ ){
                        //if all boxes from current month have date 
                        //change currentMonth to next month
                        if(dayCounter == lastDayOfCurrentMonth+1){
                            dayCounter = 1;
                            currentDate = date.with(TemporalAdjusters.firstDayOfMonth())
                                    .plusMonths(1);
                        }
                        this.boxArray[i] = new CalendarBox(dayCounter, currentDate.withDayOfMonth(dayCounter));
                        dayCounter++;
                        
                    }
                    
                    
                    
                }else{
                    //if boxes starting from one
                    currentDate = date;
                    dayCounter = 1;
                    for(int i = 0; i < boxArray.length; i++ ){
                        //if all boxes from current month have date 
                        //change currentMonth to next month
                        if(dayCounter == lastDayOfCurrentMonth+1){
                            dayCounter = 1;
                            currentDate = date.with(TemporalAdjusters.firstDayOfMonth())
                                    .plusMonths(1);
                        }
                        this.boxArray[i] = new CalendarBox(dayCounter, currentDate.withDayOfMonth(dayCounter));
                        dayCounter++;
                        
                    }
                }
                
                return boxArray;
            } finally {
                lock.unlock();//enable editing
            }
        });
        
        try {
            return future.get();// Wait for the thread to complete and return the result
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    
    
    
}
