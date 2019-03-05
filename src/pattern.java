import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class pattern {
    public static void main(String[] args) {
        List<Pattern> whiteListPattern = Arrays.asList(
            Pattern.compile("api/v1/user/bank/account"),
            Pattern.compile("api/v1/qr-send-marketing"),
            Pattern.compile("api/v1/qr-send-marketing/([^/]+)"),
            Pattern.compile("v1/user/auth")
        );

        String path1 = "api/v1/user/bank/account";
        if (whiteListPattern.stream().anyMatch(p -> p.matcher(path1).matches())) {
            System.out.println("패턴1 있다!!");
        }

        String path2 = "api/v1/qr-send-marketing";
        if (whiteListPattern.stream().anyMatch(p -> p.matcher(path2).matches())) {
            System.out.println("패턴2 있다!!");
        }

        String path3 = "api/v1/qr-send-marketing/8888/";
        if (whiteListPattern.stream().anyMatch(p -> p.matcher(path3).matches())) {
            System.out.println("패턴3 있다!!");
        }

        String path4 = "v1/user/auth";
        if (whiteListPattern.stream().anyMatch(p -> p.matcher(path4).matches())) {
            System.out.println("패턴4 있다!!");
        }
    }
}
