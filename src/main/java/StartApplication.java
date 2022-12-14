import data.GetFineDustData;
import message.Message;
import sender.Telegram;

public class StartApplication {
    public static void main(String[] args) {
        GetFineDustData gfd = new GetFineDustData();
        Message message = Message.builder()
                .dateTime(gfd.getDateTime())
                .pm10(gfd.getPM10())
                .pm10Grade(gfd.getPM10Grade())
                .pm25(gfd.getPM25())
                .pm25Grade(gfd.getPM25Grade())
                .build();

        Telegram telegram = new Telegram();
        telegram.send(message.toString());
    }
}
