package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatesUtils {
    public static String getDateString() {
        Date currentDate = new Date();
        //for windows
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        return dateFormat.format(currentDate);
    }
}
