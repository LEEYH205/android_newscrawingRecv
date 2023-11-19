package com.example.jsontestapp;

public class MyRequestModel {
    private String username;
    private int sendcnt;

    public MyRequestModel() {
        this.username = username;
        this.sendcnt = sendcnt;
    }

    public String getUsername() {
        return username;
    }

    public int getSendCnt() {
        return sendcnt;
    }

    public void SetUsername(String username){
        this.username = username;
    }

    public void SetSendCnt(int SendCnt){
        this.sendcnt = SendCnt;
    }

    @Override
    public String toString() {
        return "MyRequestModel{"
                + "field1='" + getUsername() + '\''
                + ", field2='" + getSendCnt() + '\''
                // 필드 추가 및 포맷 변경
                + '}';
    }
    // 게터와 세터 메서드도 정의해야 합니다.
}