package ua.nure.ekker.healthcare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "HealdtDB";

    public static final String TABLE_ATE = "ate";
    public static final String ATE_ID = "_id";
    public static final String ATE_NAME = "foodName";
    public static final String ATE_CALORIES = "calories";
    public static final String ATE_FAT = "fat";
    public static final String ATE_PROTEIN = "protein";
    public static final String ATE_CARBOHYDRATES = "carbohydrates";

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
                " integer," + ATE_FAT + " integer," + ATE_PROTEIN + " integer," + ATE_CARBOHYDRATES + " integer" + ")");
        db.execSQL("create table " + TABLE_FOOD + "(" + FOOD_ID
                + " integer primary key," + FOOD_NAME + " text," + FOOD_CALORIES +
                " integer," + FOOD_FAT + " integer," + FOOD_PROTEIN + " integer," + FOOD_CARBOHYDRATES + " integer" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_ATE);

        onCreate(db);

    }
}
