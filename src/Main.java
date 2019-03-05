import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    private static final DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();

        System.out.println(now.isAfter(LocalDateTime.parse("2018/07/05 10:00:00", df1)));
        System.out.println(now.isBefore(LocalDateTime.parse("2018/08/12 23:59:59", df1)));

        if(!now.isAfter(LocalDateTime.parse("2018/07/05 10:00:00", df1)) ||
            !now.isBefore(LocalDateTime.parse("2018/08/12 23:59:59", df1))) {
            System.out.println("종료  중~");
        }
    }
}
