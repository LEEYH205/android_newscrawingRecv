package com.example.jsontestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SendGetRequestActivity.OnDataReceivedListener {
    private static final String TAG = "Main";


    private TextView[] textViews;
    private List<MyResponseModel> data;
    private int currentIndex = 0;

    static int sendCnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViews = new TextView[]{
                findViewById(R.id.TextView_index1),
                findViewById(R.id.TextView_index2),
                findViewById(R.id.TextView_index3),
                findViewById(R.id.TextView_index4),
                findViewById(R.id.TextView_index5),
                findViewById(R.id.TextView_index6),
                findViewById(R.id.TextView_index7),
                findViewById(R.id.TextView_index8),
                findViewById(R.id.TextView_index9),
        };

        // 버튼 클릭 시 SendGetRequestActivity 시작
        Button mButton = findViewById(R.id.button);

        // 버튼 클릭 또는 이벤트 핸들러 등을 통해 요청을 보낼 수 있습니다.
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼이 클릭되었을 때 수행할 동작 작성
                // 예를 들어, 토스트 메시지를 표시하는 경우:
                Toast.makeText(MainActivity.this, "버튼이 클릭되었습니다!", Toast.LENGTH_SHORT).show();
                //sendRequest(++sendCnt);
                new SendGetRequestActivity(MainActivity.this).execute("lyh", String.valueOf(++sendCnt));

            }
        });

    }

    @Override
    public void onDataReceived(MyResponseModel[] newData) {
        // 데이터를 받았을 때 처리
        if (newData != null && newData.length > 0) {
            Log.d(TAG, "newData.length : "+ newData.length);
            Log.d(TAG, "textViews.length : "+ textViews.length);

            for (int i = 0; i < Math.min(newData.length, textViews.length); i++) {
                MyResponseModel model = newData[i];
                textViews[i].setText(String.format("Num: %d\nTitle: %s",
                        model.getNumId(), model.getTitle()));
            }
        } else {
            // 데이터가 없을 때 처리
            for (TextView textView : textViews) {
                textView.setText("");
            }
        }
    }

    @Override
    public void onError(String errorMessage) {
        // 에러 메시지를 사용자에게 표시
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}