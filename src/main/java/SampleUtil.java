import java.time.ZonedDateTime;

public class SampleUtil {

    /**
    * Generate Time as epoch time to generate unique prefix for first name, last name and user name
    */
    public String generateTime() {
        String timeMilli = String.valueOf(ZonedDateTime.now().toInstant().toEpochMilli());
        return timeMilli;
    }
}
