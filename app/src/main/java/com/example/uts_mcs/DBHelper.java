package com.example.uts_mcs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "ProductsDB";
    private static final int DB_VERSION = 1;

    public static final String TABLE_PRODUCTS = "products";
    public static final String FIELD_PRODUCT_ID = "id";
    public static final String FIELD_PRODUCT_NAME = "name";
    public static final String FIELD_PRODUCT_DESC = "des";
    public static final String FIELD_PRODUCT_QUANTITY = "quantity";

    private static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCTS + "(" +
            FIELD_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FIELD_PRODUCT_NAME + " TEXT, " +
            FIELD_PRODUCT_DESC + " TEXT, " +
            FIELD_PRODUCT_QUANTITY + " INTEGER);";
//
    private static final String DROP_TABLE_PRODUCTS = "DROP TABLE IF EXISTS " + TABLE_PRODUCTS + ";";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String CREATE_TABLE_PRODUCTS = "CREATE TABLE " + TABLE_PRODUCTS + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAMA Text, DES Text, QTY INTEGER)";
        db.execSQL(CREATE_TABLE_PRODUCTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DROP THE EXISTING TABLES AND RECREATE AGAIN
        db.execSQL(DROP_TABLE_PRODUCTS);
        onCreate(db);
    }

    public boolean add(String nama, String des, Integer Qty){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FIELD_PRODUCT_NAME, nama);
        cv.put(FIELD_PRODUCT_DESC, des);
        cv.put(FIELD_PRODUCT_QUANTITY, Qty);
        long result = db.insert(TABLE_PRODUCTS, null, cv);
        if(result == -1) return false;
        else return true;
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS, null);
        return data;
    }

    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + FIELD_PRODUCT_ID + " FROM " + TABLE_PRODUCTS +
                " WHERE " + FIELD_PRODUCT_NAME + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param newName
     * @param newDes
     * @param newQty
     * @param id
     * @param oldName
     */
    public void updateName(String newName, String newDes, String newQty, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_PRODUCTS + " SET " + FIELD_PRODUCT_NAME +
                " = '" + newName + "', " + FIELD_PRODUCT_DESC + " = '" + newDes + "', " +
                FIELD_PRODUCT_QUANTITY + " = '" + newQty + "' WHERE "
                + FIELD_PRODUCT_ID + " = '" + id + "'" +
                " AND " + FIELD_PRODUCT_NAME + " = '" + oldName + "'";
        db.execSQL(query);
    }

}