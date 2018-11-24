package ua.nure.ekker.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 9;
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
                " long," + ATE_FAT + " long," + ATE_PROTEIN + " long," + ATE_CARBOHYDRATES + " long," + ATE_DATE + " long" + ")");
        db.execSQL("create table " + TABLE_FOOD + "(" + FOOD_ID
                + " integer primary key," + FOOD_NAME + " text," + FOOD_CALORIES +
                " long," + FOOD_FAT + " long," + FOOD_PROTEIN + " long," + FOOD_CARBOHYDRATES + " long" + ")");

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
        contentValues.put(DBHelper.FOOD_CALORIES,310.0);
        contentValues.put(DBHelper.FOOD_FAT,1.0);
        contentValues.put(DBHelper.FOOD_PROTEIN,20.0);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,48.0);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Молоко 1%");
        contentValues.put(DBHelper.FOOD_CALORIES,410.0);
        contentValues.put(DBHelper.FOOD_FAT,10.0);
        contentValues.put(DBHelper.FOOD_PROTEIN,33.0);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,48.0);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Молоко 4.5%");
        contentValues.put(DBHelper.FOOD_CALORIES,720.0);
        contentValues.put(DBHelper.FOOD_FAT,45.0);
        contentValues.put(DBHelper.FOOD_PROTEIN,31.0);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,47.0);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Баранина");
        contentValues.put(DBHelper.FOOD_CALORIES,2090.0);
        contentValues.put(DBHelper.FOOD_FAT,163.0);
        contentValues.put(DBHelper.FOOD_PROTEIN,156.0);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,0.0);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Бекон");
        contentValues.put(DBHelper.FOOD_CALORIES,5000.0);
        contentValues.put(DBHelper.FOOD_FAT,450.0);
        contentValues.put(DBHelper.FOOD_PROTEIN,230.0);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,0.0);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Говядина");
        contentValues.put(DBHelper.FOOD_CALORIES,1870.0);
        contentValues.put(DBHelper.FOOD_FAT,124.0);
        contentValues.put(DBHelper.FOOD_PROTEIN,189.0);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,0.0);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Гусь");
        contentValues.put(DBHelper.FOOD_CALORIES,4120.0);
        contentValues.put(DBHelper.FOOD_FAT,390.0);
        contentValues.put(DBHelper.FOOD_PROTEIN,152.0);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,0.0);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Кролик");
        contentValues.put(DBHelper.FOOD_CALORIES,1560.0);
        contentValues.put(DBHelper.FOOD_FAT,80.0);
        contentValues.put(DBHelper.FOOD_PROTEIN,210.0);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,0.0);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Сало");
        contentValues.put(DBHelper.FOOD_CALORIES,7970.0);
        contentValues.put(DBHelper.FOOD_FAT,890.0);
        contentValues.put(DBHelper.FOOD_PROTEIN,024.0);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,0.0);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Свинина");
        contentValues.put(DBHelper.FOOD_CALORIES,2590.0);
        contentValues.put(DBHelper.FOOD_FAT,216.0);
        contentValues.put(DBHelper.FOOD_PROTEIN,160.0);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,0.0);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Гречневая крупа");
        contentValues.put(DBHelper.FOOD_CALORIES,3130.0);
        contentValues.put(DBHelper.FOOD_FAT,33.0);
        contentValues.put(DBHelper.FOOD_PROTEIN,126.0);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,620.0);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Кукурузная крупа");
        contentValues.put(DBHelper.FOOD_CALORIES,3370.0);
        contentValues.put(DBHelper.FOOD_FAT,12.0);
        contentValues.put(DBHelper.FOOD_PROTEIN,83.0);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,750.0);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Манная крупа");
        contentValues.put(DBHelper.FOOD_CALORIES,3280.0);
        contentValues.put(DBHelper.FOOD_FAT,10.0);
        contentValues.put(DBHelper.FOOD_PROTEIN,103.0);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,673.0);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Картофель");
        contentValues.put(DBHelper.FOOD_CALORIES,760.0);
        contentValues.put(DBHelper.FOOD_FAT,4.0);
        contentValues.put(DBHelper.FOOD_PROTEIN,20.0);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,161.0);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);

        contentValues.put(DBHelper.FOOD_NAME,"Макароны");
        contentValues.put(DBHelper.FOOD_CALORIES,3370.0);
        contentValues.put(DBHelper.FOOD_FAT,11.0);
        contentValues.put(DBHelper.FOOD_PROTEIN,104.0);
        contentValues.put(DBHelper.FOOD_CARBOHYDRATES,697.0);
        db.insert(DBHelper.TABLE_FOOD, null, contentValues);
    }

}
