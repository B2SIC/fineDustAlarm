package message;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class Message {
    private String dateTime;
    private String pm10;
    private String pm25;
    private String pm10Grade;
    private String pm25Grade;

    public String gradeConverter(String gradeNum) {
        String gradeStr = "";
        switch(gradeNum){
            case "1":
                gradeStr = "좋음";
                break;
            case "2":
                gradeStr = "보통";
                break;
            case "3":
                gradeStr = "나쁨";
                break;
            case "4":
                gradeStr = "매우나쁨";
                break;
        }
        return gradeStr;
    }

    public String toString() {
        StringBuilder msg = new StringBuilder();
        msg.append("[" + dateTime + "]\n");
        msg.append("미세먼지 농도(㎍/㎥): " + pm10 + "\n");
        msg.append("미세먼지 등급: " + gradeConverter(pm10Grade) + "\n");
        msg.append("초미세먼지 농도(㎍/㎥): " + pm25 + "\n");
        msg.append("초미세먼지 등급: " + gradeConverter(pm25Grade));
        return msg.toString();
    }
}
