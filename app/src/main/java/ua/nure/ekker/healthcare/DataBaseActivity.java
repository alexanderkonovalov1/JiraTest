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

public class DataBaseActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAdd, btnRead, btnClear;
    EditText Name, Calories, Fat, Protein, Carbohydrates;
    TextView textView;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        Name = (EditText) findViewById(R.id.Name);
        Calories = (EditText) findViewById(R.id.Calories);
        Fat = (EditText) findViewById(R.id.Fat);
        Protein = (EditText) findViewById(R.id.Protein);
        Carbohydrates = (EditText) findViewById(R.id.Carbohydrates);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        String name = Name.getText().toString();
        String calories = Calories.getText().toString();
        String fat = Fat.getText().toString();
        String protein = Protein.getText().toString();
        String carbohydrates = Carbohydrates.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        switch (v.getId()) {

            case R.id.btnAdd:
                contentValues.put(DBHelper.FOOD_NAME, name);
                contentValues.put(DBHelper.FOOD_CALORIES, calories);
                contentValues.put(DBHelper.FOOD_FAT, fat);
                contentValues.put(DBHelper.FOOD_PROTEIN, protein);
                contentValues.put(DBHelper.FOOD_CARBOHYDRATES, carbohydrates);

                database.insert(DBHelper.TABLE_FOOD, null, contentValues);
                break;

            case R.id.btnRead:
                Cursor cursor = database.query(DBHelper.TABLE_FOOD, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                        StringBuilder stringBuilder = new StringBuilder();
                        int idIndex = cursor.getColumnIndex(DBHelper.FOOD_ID);
                        int nameIndex = cursor.getColumnIndex(DBHelper.FOOD_NAME);
                        int calorieslIndex = cursor.getColumnIndex(DBHelper.FOOD_CALORIES);
                        int fatlIndex = cursor.getColumnIndex(DBHelper.FOOD_FAT);
                        int proteinlIndex = cursor.getColumnIndex(DBHelper.FOOD_PROTEIN);
                        int carbohydrateslIndex = cursor.getColumnIndex(DBHelper.FOOD_CARBOHYDRATES);
                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", name = " + cursor.getString(nameIndex) +
                                ", calories = " + cursor.getString(calorieslIndex) +
                                ", fat = " + cursor.getString(fatlIndex) +
                                ", protein = " + cursor.getString(proteinlIndex) +
                                ", carbohydrates = " + cursor.getString(carbohydrateslIndex));
                        stringBuilder.append('\n')
                                .append(cursor.getString(nameIndex))
                                .append('\n')
                                .append(cursor.getString(calorieslIndex))
                                .append('\n')
                                .append(cursor.getString(fatlIndex))
                                .append('\n')
                                .append(cursor.getString(proteinlIndex))
                                .append('\n')
                                .append(cursor.getString(carbohydrateslIndex))
                                .append('\n');
                    } while (cursor.moveToNext());
                    textView.setText(stringBuilder.toString());
                } else{
                    Log.d("mLog","0 rows");
                textView.setText("Ничего небыло найдено :'с");}
                cursor.close();
                break;

            case R.id.btnClear:
                database.delete(DBHelper.TABLE_FOOD, null, null);
                break;
        }
        dbHelper.close();
    }
}