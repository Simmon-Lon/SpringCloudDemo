import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Test {
    public static void main(String[] args) {
        ZonedDateTime time=ZonedDateTime.now();
        System.out.println(time);
        LocalDateTime time1=LocalDateTime.now();
        System.out.println(time1);
    }
}
