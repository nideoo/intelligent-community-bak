package com.enc.service.json;

public interface JsonService {

    void getCommunity();

    void getBuildings();

    void getRoom();

    void getPerson();

    void getRecords();

    void getSingleRecords(String jsonStr);

    String getYxq();
}
