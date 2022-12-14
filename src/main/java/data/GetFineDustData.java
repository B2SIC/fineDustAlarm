package data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GetFineDustData {
    private static final String serviceKey = "SERVICE_KEY"; // Required
    private static final String stationName = URLEncoder.encode("STATION_NAME", StandardCharsets.UTF_8); // Required
    private final StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty");

    public JSONObject todayData;

    public GetFineDustData() {
        urlBuilder.append("?serviceKey=" + serviceKey);
        urlBuilder.append("&returnType=json");
        urlBuilder.append("&stationName=" + stationName);
        urlBuilder.append("&dataTerm=DAILY");
        urlBuilder.append("&ver=1.0");

        this.getStationData();
    }

    private void getStationData() {
        try {
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");

            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();

            JSONObject totalData = stringToJson(sb.toString());
            JSONObject data = (JSONObject) ((JSONObject) totalData.get("response")).get("body");
            JSONArray items = (JSONArray) data.get("items");
            todayData = (JSONObject) items.get(0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getPM10() {
        return (String) todayData.get("pm10Value");
    }

    public String getPM10Grade() {
        return (String) todayData.get("pm10Grade");
    }

    public String getPM25() {
        return (String) todayData.get("pm25Value");
    }

    public String getPM25Grade() {
        return (String) todayData.get("pm25Grade");
    }

    public String getDateTime() {
        return (String) todayData.get("dataTime");
    }

    public JSONObject stringToJson(String getString) {
        JSONObject obj = null;
        try {
            JSONParser parser = new JSONParser();
            obj = (JSONObject) parser.parse(getString);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }
}
