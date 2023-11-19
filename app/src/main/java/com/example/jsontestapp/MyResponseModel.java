package com.example.jsontestapp;

import android.text.Html;
import android.text.Spanned;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyResponseModel {
    private int num;
    private String title;

    // 기본 생성자
    public MyResponseModel() {
    }

    // JSON 배열을 MyResponseModel 객체 배열로 매핑하는 메서드
    public static MyResponseModel[] fromJsonArrayString(String jsonArrayString) {
        try {
            JSONArray jsonArray = new JSONArray(jsonArrayString);
            MyResponseModel[] responseModels = new MyResponseModel[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);

                MyResponseModel responseModel = new MyResponseModel();
                responseModel.num = json.getInt("num");
                responseModel.title = json.getString("title");

                responseModels[i] = responseModel;
            }

            return responseModels;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Spanned getTitleSpanned() {
        // HTML 태그를 처리하여 Spanned로 반환
        return Html.fromHtml(title);
    }

    public int getNumId() {
        return num;
    }

    public String getTitle() {
        return title;
    }



    @Override
    public String toString() {
        return "MyResponseModel{"
                + "num=" + num
                + ", title='" + title + '\''
                + '}';
    }
}