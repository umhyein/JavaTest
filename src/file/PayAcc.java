package file;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PayAcc {
    public static Long Acount = 0L;
    public static Long BCcount = 0L;
    public static Long Dcount = 0L;

    public static Long AcountPick = 0L;
    public static Long BCcountPick = 0L;
    public static Long DcountPick = 0L;

    public static void main(String[] args) {

        String line;
        String group;
        String accountId;
        String score;
        String[] values;
        Long count = 0L;
        List<Map<String, String>> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/umhyein/Downloads/updatePayAcc.csv"))) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("/Users/umhyein/Downloads/updatePayAcc_result.csv")));

            bw.write(String.format("user_type,account_id,score"));
            while ((line = br.readLine()) != null) {

                accountId = line.split(",")[1].replace("\"", "").trim();
                bw.write(String.format(" (%s, now()),", accountId));
                bw.newLine();

            }



            bw.flush();
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double getRandom(String group, int weight) {
        //int weightRandom = (int) ((!group.substring(0, 1).equalsIgnoreCase("A")) ? Math.random() * (weight * 1000000) : Math.random());
        //int random = (int) ((Math.random() + weightRandom));
        int random = (int) ((Math.random() + Math.random() * (weight * 0.009)) * 100);
        return random;
    }

    public static int getWeight(String s) {
        if (s.equalsIgnoreCase("B") || s.equalsIgnoreCase("C")) {
            BCcount++;
            return 2;
        }

        if (s.equalsIgnoreCase("D")) {
            Dcount++;
            return 4;
        }

        Acount++;
        return 1;
    }
}
