package ua.nure.ekker.healthcare;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.FOOD_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.FOOD_NAME);
                    int calorieslIndex = cursor.getColumnIndex(DBHelper.FOOD_CALORIES);
                    int fatlIndex = cursor.getColumnIndex(DBHelper.FOOD_FAT);
                    int proteinlIndex = cursor.getColumnIndex(DBHelper.FOOD_PROTEIN);
                    int carbohydrateslIndex = cursor.getColumnIndex(DBHelper.FOOD_CARBOHYDRATES);
                    contentValues.put(DBHelper.ATE_NAME, cursor.getString(nameIndex));
                    contentValues.put(DBHelper.ATE_CALORIES, cursor.getInt(calorieslIndex) * amount);
                    contentValues.put(DBHelper.ATE_FAT, cursor.getInt(fatlIndex) * amount);
                    contentValues.put(DBHelper.ATE_PROTEIN, cursor.getInt(proteinlIndex) * amount);
                    contentValues.put(DBHelper.ATE_CARBOHYDRATES, cursor.getInt(carbohydrateslIndex) * amount);
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
                    do {
                        Log.d("mLog", "ID = " + cursor2.getInt(idIndex) +
                                ", name = " + cursor2.getString(nameIndex) +
                                ", calories = " + cursor2.getString(calorieslIndex) +
                                ", fat = " + cursor2.getString(fatlIndex) +
                                ", protein = " + cursor2.getString(proteinlIndex) +
                                ", carbohydrates = " + cursor2.getString(carbohydrateslIndex));
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
                                .append('\n');
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
