package ua.nure.ekker.healthcare;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StatiscticsActivity extends AppCompatActivity implements View.OnClickListener {

    Button dailyStat, monthlyStat, yearlyStat;
    TextView textViewStat;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statisctics);

        textViewStat = (TextView) findViewById(R.id.textViewStat);
        textViewStat.setMovementMethod(new ScrollingMovementMethod());

        dailyStat = (Button) findViewById(R.id.btnDailyStat);
        dailyStat.setOnClickListener(this);

        monthlyStat = (Button) findViewById(R.id.btnMonthlyStat);
        monthlyStat.setOnClickListener(this);

        yearlyStat = (Button) findViewById(R.id.btnyearlyStat);
        yearlyStat.setOnClickListener(this);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        switch (v.getId()) {
            case R.id.btnDailyStat:

                Date date = new Date();                      // timestamp now
                Calendar cal = Calendar.getInstance();       // get calendar instance
                cal.setTime(date);                           // set cal to date
                cal.set(Calendar.HOUR_OF_DAY, 23);            // set hour to midnight
                cal.set(Calendar.MINUTE, 59);                 // set minute in hour
                cal.set(Calendar.SECOND, 59);                 // set second in minute
                cal.set(Calendar.MILLISECOND, 999);            // set millis in second

                Date zeroedDate = cal.getTime();
                System.out.println(zeroedDate);
                long currentDay = cal.getTimeInMillis();

                cal.add(Calendar.DATE,-1);
                zeroedDate = cal.getTime();
                long previousDay = cal.getTimeInMillis();
                System.out.println(zeroedDate);

                String whereClause = "date BETWEEN ? AND ?";
                String[] whereArgs = new String[]{
                        String.valueOf(previousDay), String.valueOf(currentDay)
                };

                Cursor cursor2 = database.query(DBHelper.TABLE_ATE, null, whereClause, whereArgs, null, null, null);

                if (cursor2.moveToFirst()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    int calorieslIndex = cursor2.getColumnIndex(DBHelper.ATE_CALORIES);
                    int fatlIndex = cursor2.getColumnIndex(DBHelper.ATE_FAT);
                    int proteinlIndex = cursor2.getColumnIndex(DBHelper.ATE_PROTEIN);
                    int carbohydrateslIndex = cursor2.getColumnIndex(DBHelper.ATE_CARBOHYDRATES);

                    double sumCalDay = 0;
                    double sumFatDay = 0;
                    double sumProtDay = 0;
                    double sumCarbDay = 0;
                    do {
                        double caloriesDay = Double.parseDouble(cursor2.getString(calorieslIndex));
                        double fatDay = Double.parseDouble(cursor2.getString(fatlIndex));
                        double proteinDay = Double.parseDouble(cursor2.getString(proteinlIndex));
                        double carbohydratesDay = Double.parseDouble(cursor2.getString(carbohydrateslIndex));

                        sumCalDay += caloriesDay;
                        sumFatDay += fatDay;
                        sumProtDay += proteinDay;
                        sumCarbDay += carbohydratesDay;

                    } while (cursor2.moveToNext());

                    stringBuilder.append('\n')
                            .append("Вы получили : ")
                            .append('\n')
                            .append(sumCalDay)
                            .append(" калорий,")
                            .append('\n')
                            .append(sumFatDay)
                            .append("жиров, ")
                            .append('\n')
                            .append(sumProtDay)
                            .append("белка, ")
                            .append('\n')
                            .append(sumCarbDay)
                            .append("углеводов. ")
                            .append('\n');

                    textViewStat.setText(stringBuilder.toString());
                } else {
                    Log.d("mLog", "0 rows");
                    textViewStat.setText("Ничего небыло найдено :'с");
                }
                cursor2.close();
                break;
            case R.id.btnMonthlyStat:
                break;
            case R.id.btnyearlyStat:
            default:
                break;
        }
    }
}