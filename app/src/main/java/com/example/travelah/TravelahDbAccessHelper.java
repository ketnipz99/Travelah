package com.example.travelah;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TravelahDbAccessHelper {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase travelahDb;
    private static TravelahDbAccessHelper instance;

    TravelahDbAccessHelper(Context context) {
        this.openHelper = new TravalahDbOpenHelper(context);
    }

    public static TravelahDbAccessHelper getInstance(Context context) {

        if (instance == null) {
            instance = new TravelahDbAccessHelper(context);
        }

        return instance;
    }

    //To open the database
    public void openTravelahDb() {

        this.travelahDb = openHelper.getWritableDatabase();
    }

    //To close the database
    public void closeTravelahDb() {
        if (travelahDb != null) {
            travelahDb.close();
        }
    }

    /*
    public List<TourismInfo> getTourismInfo() {

        List<TourismInfo> tourismInfoList = new ArrayList<>();

        travelahDb = openHelper.getReadableDatabase();

        Cursor cursor = travelahDb.rawQuery("SELECT * FROM travelahDb ORDER BY _id ASC", new String[]{});

        if (cursor.moveToFirst()) {
            do {
                TourismInfo tourismInfo = new TourismInfo();
                tourismInfo.setId(cursor.getInt(0));
                tourismInfo.setTitle(cursor.getString(1));
                tourismInfo.setDesc_1(cursor.getString(2));
                tourismInfo.setDesc_2(cursor.getString(3));
                tourismInfo.setImage_1(cursor.getBlob(4));
                tourismInfo.setImage_2(cursor.getBlob(5));
                tourismInfoList.add(tourismInfo);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return tourismInfoList;
    }
    */

    public List<TourismInfo> getFeaturedTourismInfo(String chosenCategory) {

        List<TourismInfo> tourismInfoList = new ArrayList<>();

        travelahDb = openHelper.getReadableDatabase();

        Cursor cursor = travelahDb.rawQuery("SELECT * FROM travelahDb WHERE category = ? ORDER BY _id ASC", new String[]{chosenCategory});

        if (cursor.moveToFirst()) {
            do {
                TourismInfo tourismInfo = new TourismInfo();
                tourismInfo.setId(cursor.getInt(0));
                tourismInfo.setTitle(cursor.getString(1));
                tourismInfo.setDesc_1(cursor.getString(2));
                tourismInfo.setDesc_2(cursor.getString(3));
                tourismInfo.setImage_1(cursor.getBlob(4));
                tourismInfo.setImage_2(cursor.getBlob(5));
                tourismInfoList.add(tourismInfo);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return tourismInfoList;
    }

    public List<TourismInfo> getStateTourismInfo(String chosenState) {

        List<TourismInfo> tourismInfoList = new ArrayList<>();

        travelahDb = openHelper.getReadableDatabase();

        Cursor cursor = travelahDb.rawQuery("SELECT * FROM travelahDb WHERE state = ? ORDER BY _id ASC", new String[]{chosenState});

        if (cursor.moveToFirst()) {
            do {
                TourismInfo tourismInfo = new TourismInfo();
                tourismInfo.setId(cursor.getInt(0));
                tourismInfo.setTitle(cursor.getString(1));
                tourismInfo.setDesc_1(cursor.getString(2));
                tourismInfo.setDesc_2(cursor.getString(3));
                tourismInfo.setImage_1(cursor.getBlob(4));
                tourismInfo.setImage_2(cursor.getBlob(5));
                tourismInfoList.add(tourismInfo);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return tourismInfoList;
    }


    public TourismInfo getChosenSpotsDetails(int id) {

        travelahDb = openHelper.getReadableDatabase();

        String chosenId = Integer.toString(id);
        TourismInfo tourismInfo = new TourismInfo();

        Cursor cursor = travelahDb.rawQuery("SELECT * FROM travelahDb WHERE _id = " + chosenId, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                tourismInfo.setTitle(cursor.getString(1));
                tourismInfo.setDesc_1(cursor.getString(2));
                tourismInfo.setDesc_2(cursor.getString(3));
                tourismInfo.setImage_1(cursor.getBlob(4));
                tourismInfo.setImage_2(cursor.getBlob(5));
                tourismInfo.setUrl(cursor.getString(6));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return tourismInfo;
    }
}
