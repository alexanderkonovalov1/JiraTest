package ua.nure.ekker.healthcare;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Date;
import java.time.LocalDate;

public class AteActivity extends AppCompatActivity implements View.OnClickListener {

    EditText amount, foodChoice;
    TextView textViewAte;
    Button save, show;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ate);

        textViewAte = (TextView) findViewById(R.id.textViewAte);
        textViewAte.setMovementMethod(new ScrollingMovementMethod());

        save = (Button) findViewById(R.id.btnSaveFood);
        save.setOnClickListener(this);

        show = (Button) findViewById(R.id.btnShowFood);
        show.setOnClickListener(this);

        amount = (EditText) findViewById(R.id.amount);

        foodChoice = (EditText) findViewById(R.id.foodChoice);

        dbHelper = new DBHelper(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        switch (v.getId()) {
            case R.id.btnSaveFood:

                String food = foodChoice.getText().toString();
                String am = amount.getText().toString();
                int amount = Integer.parseInt(String.valueOf(am));

                String whereClause = "foodName = ?";
                String[] whereArgs = new String[] {
                        food
                };

                Cursor cursor = database.query(DBHelper.TABLE_FOOD, null, whereClause,whereArgs, null, null, null);

                double amountFinal = amount/1000.0;

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.FOOD_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.FOOD_NAME);
                    int calorieslIndex = cursor.getColumnIndex(DBHelper.FOOD_CALORIES);
                    int fatlIndex = cursor.getColumnIndex(DBHelper.FOOD_FAT);
                    int proteinlIndex = cursor.getColumnIndex(DBHelper.FOOD_PROTEIN);
                    int carbohydrateslIndex = cursor.getColumnIndex(DBHelper.FOOD_CARBOHYDRATES);
                    double calories = cursor.getInt(calorieslIndex) * amountFinal;
                    double fat = cursor.getInt(fatlIndex) * amountFinal;
                    double protein = cursor.getInt(proteinlIndex) * amountFinal;
                    double carbohydrates = cursor.getInt(carbohydrateslIndex) * amountFinal;
                    contentValues.put(DBHelper.ATE_NAME, cursor.getString(nameIndex));
                    contentValues.put(DBHelper.ATE_CALORIES, calories);
                    contentValues.put(DBHelper.ATE_FAT, fat);
                    contentValues.put(DBHelper.ATE_PROTEIN, protein);
                    contentValues.put(DBHelper.ATE_CARBOHYDRATES, carbohydrates);
                    long timeNow = System.currentTimeMillis();
                    contentValues.put(DBHelper.ATE_DATE, timeNow);
                }

                database.insert(DBHelper.TABLE_ATE, null, contentValues);
                cursor.close();
                break;
            case R.id.btnShowFood:
                Cursor cursor2 = database.query(DBHelper.TABLE_ATE, null, null, null, null, null, null);

                if (cursor2.moveToFirst()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    int idIndex = cursor2.getColumnIndex(DBHelper.ATE_ID);
                    int nameIndex = cursor2.getColumnIndex(DBHelper.ATE_NAME);
                    int calorieslIndex = cursor2.getColumnIndex(DBHelper.ATE_CALORIES);
                    int fatlIndex = cursor2.getColumnIndex(DBHelper.ATE_FAT);
                    int proteinlIndex = cursor2.getColumnIndex(DBHelper.ATE_PROTEIN);
                    int carbohydrateslIndex = cursor2.getColumnIndex(DBHelper.ATE_CARBOHYDRATES);
                    int dateIndex = cursor2.getColumnIndex(DBHelper.ATE_DATE);
                    do {
                        Log.d("mLog", "ID = " + cursor2.getInt(idIndex) +
                                ", name = " + cursor2.getString(nameIndex) +
                                ", calories = " + cursor2.getString(calorieslIndex) +
                                ", fat = " + cursor2.getString(fatlIndex) +
                                ", protein = " + cursor2.getString(proteinlIndex) +
                                ", carbohydrates = " + cursor2.getString(carbohydrateslIndex) +
                                ", date = " + cursor2.getString(dateIndex));
                        stringBuilder.append('\n')
                                .append(cursor2.getString(nameIndex))
                                .append('\n')
                                .append(cursor2.getString(calorieslIndex))
                                .append('\n')
                                .append(cursor2.getString(fatlIndex))
                                .append('\n')
                                .append(cursor2.getString(proteinlIndex))
                                .append('\n')
                                .append(cursor2.getString(carbohydrateslIndex))
                                .append('\n')
                                .append(cursor2.getString(dateIndex));
                    } while (cursor2.moveToNext());
                    textViewAte.setText(stringBuilder.toString());
                } else{
                    Log.d("mLog","0 rows");
                    textViewAte.setText("Ничего небыло найдено :'с");}
                cursor2.close();
                break;
            default:
                break;

        }
    }
}
