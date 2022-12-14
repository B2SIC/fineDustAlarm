package sender;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Telegram {
    private static final String token = "TOKEN"; // Required
    private static final String chat_id = "CHAT_ID"; // Required

    public void send(String text) {
        BufferedReader in = null;

        try {
            URL url = new URL("https://api.telegram.org/bot" + token + "/sendmessage?chat_id=" + chat_id + "&text=" + URLEncoder.encode(text, StandardCharsets.UTF_8));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));

            String line;
            StringBuilder result = new StringBuilder();
            while((line = in.readLine()) != null) {
                result.append(line);
            }
            String resultString = result.toString();
            System.out.println("Send Request: " + resultString);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if(in != null)
                try {
                    in.close();
                } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
