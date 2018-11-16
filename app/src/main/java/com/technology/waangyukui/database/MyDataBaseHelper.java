package com.technology.waangyukui.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenvo on 2018/5/22.
 */
public class MyDataBaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;
    private static final String TABLE_NAME_NAME="phone";
    private static final String TABLE_NAME_GOODS="phoneGood";
    private static final String DB_NAME="phones.db";
    private static final int VERSION=1;
    private static final String CREATE_TABLE="create table phone(_id Integer primary key autoincrement,name text,sex text,number text,desc text)";
    private static final String CREATE_TABLE_CHOICE="create table phoneGood(_id Integer primary key autoincrement," +
            "shopName text," +
            "image text," +
            "taobaoId text," +
            "couponPromotUrl text,"+
            "sellCount text,"+
            "goodsName text,"+
            "endTime text,"+
            "cateId text,"+
            "price text,"+
            "couponDenomination text)";
    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public MyDataBaseHelper(Context context,String name,int version){
        this(context,name,null,version);
    }
    public MyDataBaseHelper(Context context){
        this(context,DB_NAME,null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase=sqLiteDatabase;
        sqLiteDatabase.execSQL(CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE_CHOICE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * 插入数据的方法
     * @param values
     */
    public void insert(ContentValues values){
        //获取SQLiteDAtabase的实例
        SQLiteDatabase db=getWritableDatabase();
        //向数据库中插入数据
        db.insert(TABLE_NAME_NAME,null,values);
        db.close();
    }


    /**
     * 插入数据的方法
     * @param values
     */
    public void insertGoods(ContentValues values){
        //获取SQLiteDAtabase的实例
        SQLiteDatabase db=getWritableDatabase();
        //向数据库中插入数据
        db.insert(TABLE_NAME_GOODS,null,values);
        db.close();
    }


    /**
     * 更新数据库中的数据
     * @param values
     * @param whereClause
     * @param whereArgs
     */
    public void update(ContentValues values,String whereClause, String[] whereArgs){
        SQLiteDatabase db=getWritableDatabase();
        db.update(TABLE_NAME_NAME,values,whereClause,whereArgs);
        db.close();
    }

    /**
     * 根据唯一标识删除数据库中的数据
     * @param id
     */
    public void delete(int id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_NAME_NAME,"_id",new String[]{String.valueOf(id)});

    }

    public Cursor query(){
        SQLiteDatabase db=getReadableDatabase();
         Cursor cursor=db.query(TABLE_NAME_NAME,null,null,null,null,null,null);
         return cursor;
    }

    public Cursor queryGoods(){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME_GOODS,null,null,null,null,null,null);
        return cursor;
    }


    public void close(){
        if (sqLiteDatabase!=null){
            sqLiteDatabase.close();
        }
    }

}
