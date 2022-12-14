# fineDustAlarm
거주지 인근 미세먼지 농도에 따른 Telegram 알람 전송 서비스

* 언어: `JAVA 11`
* 빌드: `Gradle`
* 활용 API: Telegram, 한국환경공단_에어코리아_대기오염정보
* 운영환경: AWS Lambda (예정)

## Required
* Telegram
  * `token`
  * `chat_id` -> Telegram 채팅 ID
* 공공데이터포탈 (한국환경공단_에어코리아_대기오염정보)
  * `serviceKey`
  * `stationName` -> 측정소 이름

## 알람 정보
### 예시
![image](https://user-images.githubusercontent.com/28584171/207598634-456b34e9-1eea-42b1-a193-921c37dccdbb.png)
### 전송 기준
* 오전 1번, 오후 1번 측정 후 나쁨 이상일 경우에 Telegram 알람 전송
