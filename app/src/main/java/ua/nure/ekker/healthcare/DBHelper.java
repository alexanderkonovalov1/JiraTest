package ua.nure.ekker.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;

public class DBHelper  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "HealdtDB";

    public static final String TABLE_ATE = "ate";
    public static final String ATE_ID = "_id";
    public static final String ATE_NAME = "foodName";
    public static final String ATE_CALORIES = "calories";
    public static final String ATE_FAT = "fat";
    public static final String ATE_PROTEIN = "protein";
    public static final String ATE_CARBOHYDRATES = "carbohydrates";
    public static final String ATE_DATE = "date";

    public static final String TABLE_FOOD = "food";
    public static final String FOOD_ID = "_id";
    public static final String FOOD_NAME = "foodName";
    public static final String FOOD_CALORIES = "calories";
    public static final String FOOD_FAT = "fat";
    public static final String FOOD_PROTEIN = "protein";
    public static final String FOOD_CARBOHYDRATES = "carbohydrates";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("create table " + TABLE_ATE + "(" + ATE_ID
                + " integer primary key," + ATE_NAME + " text," + ATE_CALORIES +
                " integer," + ATE_FAT + " integer," + ATE_PROTEIN + " integer," + ATE_CARBOHYDRATES + " integer," + ATE_DATE + " date" + ")");
        db.execSQL("create table " + TABLE_FOOD + "(" + FOOD_ID
                + " integer primary key," + FOOD_NAME + " text," + FOOD_CALORIES +
                " integer," + FOOD_FAT + " integer," + FOOD_PROTEIN + " integer," + FOOD_CARBOHYDRATES + " integer" + ")");

        dbFill(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_ATE);
        db.execSQL("drop table if exists " + TABLE_FOOD);
        onCreate(db);
    }

    private void dbFill(SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.FOOD_NAME,"Молоко 0.1%");
        contentValues.put(DBHelper.FOOD_CALORIES,0.31);
        contentValues.put(DBHelper.FOOD_FAT,0.001);
        contentValues.put(DBHelper.FOOD_PROTEIN,0.02);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,0.048);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Молоко 1%");
        contentValues.put(DBHelper.FOOD_CALORIES,0.41);
        contentValues.put(DBHelper.FOOD_FAT,0.01);
        contentValues.put(DBHelper.FOOD_PROTEIN,0.033);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,0.048);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Молоко 4.5%");
        contentValues.put(DBHelper.FOOD_CALORIES,0.72);
        contentValues.put(DBHelper.FOOD_FAT,0.045);
        contentValues.put(DBHelper.FOOD_PROTEIN,0.031);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,0.047);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Баранина");
        contentValues.put(DBHelper.FOOD_CALORIES,2.09);
        contentValues.put(DBHelper.FOOD_FAT,0.163);
        contentValues.put(DBHelper.FOOD_PROTEIN,0.156);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,0.0);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Бекон");
        contentValues.put(DBHelper.FOOD_CALORIES,5.0);
        contentValues.put(DBHelper.FOOD_FAT,0.45);
        contentValues.put(DBHelper.FOOD_PROTEIN,0.23);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,0.0);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Говядина");
        contentValues.put(DBHelper.FOOD_CALORIES,1.87);
        contentValues.put(DBHelper.FOOD_FAT,0.124);
        contentValues.put(DBHelper.FOOD_PROTEIN,0.189);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,0.0);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Гусь");
        contentValues.put(DBHelper.FOOD_CALORIES,4.12);
        contentValues.put(DBHelper.FOOD_FAT,0.39);
        contentValues.put(DBHelper.FOOD_PROTEIN,0.152);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,0.0);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Кролик");
        contentValues.put(DBHelper.FOOD_CALORIES,1.56);
        contentValues.put(DBHelper.FOOD_FAT,0.08);
        contentValues.put(DBHelper.FOOD_PROTEIN,0.21);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,0.0);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Сало");
        contentValues.put(DBHelper.FOOD_CALORIES,7.97);
        contentValues.put(DBHelper.FOOD_FAT,0.89);
        contentValues.put(DBHelper.FOOD_PROTEIN,0.024);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,0.0);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Свинина");
        contentValues.put(DBHelper.FOOD_CALORIES,2.59);
        contentValues.put(DBHelper.FOOD_FAT,0.216);
        contentValues.put(DBHelper.FOOD_PROTEIN,0.16);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,0.0);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Гречневая крупа");
        contentValues.put(DBHelper.FOOD_CALORIES,3.13);
        contentValues.put(DBHelper.FOOD_FAT,0.033);
        contentValues.put(DBHelper.FOOD_PROTEIN,0.126);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,0.62);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Кукурузная крупа");
        contentValues.put(DBHelper.FOOD_CALORIES,3.37);
        contentValues.put(DBHelper.FOOD_FAT,0.012);
        contentValues.put(DBHelper.FOOD_PROTEIN,0.083);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,0.75);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Манная крупа");
        contentValues.put(DBHelper.FOOD_CALORIES,3.28);
        contentValues.put(DBHelper.FOOD_FAT,0.01);
        contentValues.put(DBHelper.FOOD_PROTEIN,0.103);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,0.673);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Картофель");
        contentValues.put(DBHelper.FOOD_CALORIES,0.76);
        contentValues.put(DBHelper.FOOD_FAT,0.004);
        contentValues.put(DBHelper.FOOD_PROTEIN,0.02);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,0.161);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Макароны");
        contentValues.put(DBHelper.FOOD_CALORIES,3.37);
        contentValues.put(DBHelper.FOOD_FAT,0.011);
        contentValues.put(DBHelper.FOOD_PROTEIN,0.104);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,0.697);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);
    }

}
