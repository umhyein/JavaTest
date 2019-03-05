package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileRead {
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

        try (BufferedReader br = new BufferedReader(new FileReader("/Users/umhyein/Downloads/vip_entry_user_type_20180802_ver2.csv"))) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("/Users/umhyein/Downloads/vip_entry_user_type_20180802_ver2_result.csv")));

            bw.write(String.format("user_type,account_id,score"));
            while ((line = br.readLine()) != null) {
                Map<String, String> data = new HashMap<>();
                values = line.split(",");

                group = values[0];
                accountId = values[1];
                score = String.valueOf(getRandom(group, getWeight(group.substring(0, 1))));


                /*bw.write(String.format("%s,%s,%s", group, accountId, score));
                bw.newLine();*/

                accountId = line.split(",")[1].replace("\"", "").trim();
                bw.write(String.format("insert into promotion_cbt_account (account_id, created_at) values (%s, now());", accountId));
                bw.newLine();

                data.put("group", group);
                data.put("account_id", accountId);
                data.put("score", score);
                dataList.add(data);

                count++;
            }
            System.out.println("총 계산 row : " + count);

            //정렬
            List<Map<String, String>> sortList = dataList.stream().sorted((o1, o2) -> Double.compare(Double.parseDouble(o2.get("score")), Double.parseDouble(o1.get("score")))).limit(761).collect(Collectors.toList());

            Long count2 = 0L;
            for (Map<String, String> map : sortList) {
                bw.write(String.format("%s,%s,%s", map.get("group"), map.get("account_id"), map.get("score")));
                bw.newLine();
                count2++;


                if(map.get("group").substring(0, 1).equalsIgnoreCase("A")){
                    AcountPick++;
                }

                if(map.get("group").substring(0, 1).equalsIgnoreCase("B") || map.get("group").substring(0, 1).equalsIgnoreCase("C")){
                    BCcountPick++;
                }

                if(map.get("group").substring(0, 1).equalsIgnoreCase("D")){
                    DcountPick++;
                }
            }

            System.out.println("총 출력 row : " + count2);
            System.out.println("------------------");
            System.out.println("응모자 총 A : " + Acount);
            System.out.println("응모자 총 BC : " + BCcount);
            System.out.println("응모자 총 D : " + Dcount);
            System.out.println("------------------");
            System.out.println(String.format("당첨자 총 A : %s / %s%%", AcountPick, Math.round((AcountPick.doubleValue() / 760.d) * 100.0)));
            System.out.println(String.format("당첨자 총 BC : %s / %s%%", BCcountPick, Math.round((BCcountPick.doubleValue() / 760.d) * 100.0)));
            System.out.println(String.format("당첨자 총 D : %s / %s%%", DcountPick, Math.round((DcountPick.doubleValue() / 760.d) * 100.0)));

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
