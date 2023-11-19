package com.example.jsontestapp;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class SendGetRequestActivity extends AsyncTask<String, Void, MyResponseModel[]> {

    private OnDataReceivedListener onDataReceivedListener;


    // 생성자에서 TextView와 데이터 수신 리스너를 받아와 초기화합니다.
    public SendGetRequestActivity(OnDataReceivedListener onDataReceivedListener) {
        this.onDataReceivedListener = onDataReceivedListener;
    }

    public interface OnDataReceivedListener {
        void onDataReceived(MyResponseModel[] data);
        void onError(String errorMessage);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // UI 초기화 등의 작업을 수행할 수 있습니다.
    }

    @Override
    protected MyResponseModel[] doInBackground(String... params) {
        String username = params[0];
        String sendcnt = params[1];

        try {
            // GET 요청 코드를 호출하고 결과 반환
            String jsonArrayString = sendGetRequest(username, sendcnt);

            // JSON 배열을 MyResponseModel 배열로 매핑
            return MyResponseModel.fromJsonArrayString(jsonArrayString);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected void onPostExecute(MyResponseModel[] result) {
        // AsyncTask가 완료된 후 UI 업데이트
        if (result != null) {
            onDataReceivedListener.onDataReceived(result);
        } else {
            // 에러가 발생한 경우 사용자에게 알림
            if (onDataReceivedListener != null) {
                onDataReceivedListener.onError("Error occurred while parsing the response.");
            }
        }
    }

    private String sendGetRequest(String username, String sendcnt) {
        try {
            // 서버의 PHP 파일 주소
            String urlString = "http://118.67.129.167/project1_1.php" +
                    "?username=" + URLEncoder.encode(username, "UTF-8") +
                    "&sendcnt=" + URLEncoder.encode(sendcnt, "UTF-8");

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // GET 요청 설정
            connection.setRequestMethod("GET");

            // 응답 받기
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // 연결 해제
            connection.disconnect();

            return response.toString();

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}

