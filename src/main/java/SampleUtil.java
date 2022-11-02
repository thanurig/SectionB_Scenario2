import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.UUID;

public class SampleUtil {

    /**
    * Method Generate Time as epoch time to generate unique prefix for username
    */
    public String generateTime() {
        String timeMilli = String.valueOf(ZonedDateTime.now().toInstant().toEpochMilli());
        return timeMilli;
    }

    /**
     * Method for Generate unique prefix for password
     */
    public String generateString() {
        String uuid = UUID.randomUUID().toString();
        String [] splitPw = uuid.split("-");
        String  firstEle = splitPw[0];
        String password = firstEle.toUpperCase(Locale.ROOT);
        return password;
    }

}
