package lgh;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Date;
import java.text.SimpleDateFormat;

public class HelloWorld 
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("==start");
        

        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        cal.setMinimalDaysInFirstWeek(4);
        Date date = cal.getTime();

        System.out.println(cal.toString());

        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd'H'HH"); 
        System.out.println(fmt.format(date));

        fmt = new SimpleDateFormat("yyyyMMdd"); 
        System.out.println(fmt.format(date));

        fmt = new SimpleDateFormat("yyyy'W'ww"); 
        System.out.println(fmt.format(date));

        fmt = new SimpleDateFormat("yyyyMM"); 
        System.out.println(fmt.format(date));

        fmt = new SimpleDateFormat("yyyy"); 
        System.out.println(fmt.format(date));

        System.out.println("minute: " + String.valueOf(cal.get(Calendar.MINUTE)));
        System.out.println("hour: " + String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
        System.out.println("day: " + String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
        System.out.println("week: " + String.valueOf(cal.get(Calendar.WEEK_OF_YEAR)));
        System.out.println("month: " + String.valueOf(cal.get(Calendar.MONTH) + 1));
        System.out.println("year: " + String.valueOf(cal.get(Calendar.YEAR)));
        
        System.out.println("==end");
    }
}
