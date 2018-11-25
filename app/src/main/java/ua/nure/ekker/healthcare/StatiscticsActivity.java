package ua.nure.ekker.healthcare;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

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

                cal.add(Calendar.DATE, -1);
                zeroedDate = cal.getTime();
                long previousDay = cal.getTimeInMillis();
                System.out.println(zeroedDate);

                String whereClause = "date BETWEEN ? AND ?";
                String[] whereArgs = new String[]{
                        String.valueOf(previousDay), String.valueOf(currentDay)
                };

                Cursor cursor = database.query(DBHelper.TABLE_ATE, null, whereClause, whereArgs, null, null, null);

                if (cursor.moveToFirst()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    int calorieslIndex = cursor.getColumnIndex(DBHelper.ATE_CALORIES);
                    int fatlIndex = cursor.getColumnIndex(DBHelper.ATE_FAT);
                    int proteinlIndex = cursor.getColumnIndex(DBHelper.ATE_PROTEIN);
                    int carbohydrateslIndex = cursor.getColumnIndex(DBHelper.ATE_CARBOHYDRATES);
                    int dateIndex = cursor.getColumnIndex(DBHelper.ATE_DATE);

                    double sumCalDay0 = 0, sumCalDay1 = 0, sumCalDay2 = 0, sumCalDay3 = 0, sumCalDay4 = 0, sumCalDay5 = 0, sumCalDay6 = 0, sumCalDay7 = 0, sumCalDay8 = 0, sumCalDay9 = 0, sumCalDay10 = 0, sumCalDay11 = 0, sumCalDay12 = 0, sumCalDay13 = 0, sumCalDay14 = 0, sumCalDay15 = 0, sumCalDay16 = 0, sumCalDay17 = 0, sumCalDay18 = 0, sumCalDay19 = 0, sumCalDay20 = 0, sumCalDay21 = 0, sumCalDay22 = 0, sumCalDay23 = 0;
                    double sumFatDay0 = 0, sumFatDay1 = 0, sumFatDay2 = 0, sumFatDay3 = 0, sumFatDay4 = 0, sumFatDay5 = 0, sumFatDay6 = 0, sumFatDay7 = 0, sumFatDay8 = 0, sumFatDay9 = 0, sumFatDay10 = 0, sumFatDay11 = 0, sumFatDay12 = 0, sumFatDay13 = 0, sumFatDay14 = 0, sumFatDay15 = 0, sumFatDay16 = 0, sumFatDay17 = 0, sumFatDay18 = 0, sumFatDay19 = 0, sumFatDay20 = 0, sumFatDay21 = 0, sumFatDay22 = 0, sumFatDay23 = 0;
                    double sumProtDay0 = 0, sumProtDay1 = 0, sumProtDay2 = 0, sumProtDay3 = 0, sumProtDay4 = 0, sumProtDay5 = 0, sumProtDay6 = 0, sumProtDay7 = 0, sumProtDay8 = 0, sumProtDay9 = 0, sumProtDay10 = 0, sumProtDay11 = 0, sumProtDay12 = 0, sumProtDay13 = 0, sumProtDay14 = 0, sumProtDay15 = 0, sumProtDay16 = 0, sumProtDay17 = 0, sumProtDay18 = 0, sumProtDay19 = 0, sumProtDay20 = 0, sumProtDay21 = 0, sumProtDay22 = 0, sumProtDay23 = 0;
                    double sumCarbDay0 = 0, sumCarbDay1 = 0, sumCarbDay2 = 0, sumCarbDay3 = 0, sumCarbDay4 = 0, sumCarbDay5 = 0, sumCarbDay6 = 0, sumCarbDay7 = 0, sumCarbDay8 = 0, sumCarbDay9 = 0, sumCarbDay10 = 0, sumCarbDay11 = 0, sumCarbDay12 = 0, sumCarbDay13 = 0, sumCarbDay14 = 0, sumCarbDay15 = 0, sumCarbDay16 = 0, sumCarbDay17 = 0, sumCarbDay18 = 0, sumCarbDay19 = 0, sumCarbDay20 = 0, sumCarbDay21 = 0, sumCarbDay22 = 0, sumCarbDay23 = 0;
                    do {
                        long mills = cursor.getLong(dateIndex);
                        Date curDate = new Date(mills);
                        SimpleDateFormat formatForDateNow = new SimpleDateFormat("HH");
                        int hour = Integer.parseInt(formatForDateNow.format(curDate));

                        double caloriesDay = Double.parseDouble(cursor.getString(calorieslIndex));
                        double fatDay = Double.parseDouble(cursor.getString(fatlIndex));
                        double proteinDay = Double.parseDouble(cursor.getString(proteinlIndex));
                        double carbohydratesDay = Double.parseDouble(cursor.getString(carbohydrateslIndex));

                        if (hour == 0) {
                            sumCalDay0 += caloriesDay;
                            sumFatDay0 += fatDay;
                            sumProtDay0 += proteinDay;
                            sumCarbDay0 += carbohydratesDay;
                        } else if (hour == 1) {
                            sumCalDay1 += caloriesDay;
                            sumFatDay1 += fatDay;
                            sumProtDay1 += proteinDay;
                            sumCarbDay1 += carbohydratesDay;
                        } else if (hour == 2) {
                            sumCalDay2 += caloriesDay;
                            sumFatDay2 += fatDay;
                            sumProtDay2 += proteinDay;
                            sumCarbDay2 += carbohydratesDay;
                        } else if (hour == 3) {
                            sumCalDay3 += caloriesDay;
                            sumFatDay3 += fatDay;
                            sumProtDay3 += proteinDay;
                            sumCarbDay3 += carbohydratesDay;
                        } else if (hour == 4) {
                            sumCalDay4 += caloriesDay;
                            sumFatDay4 += fatDay;
                            sumProtDay4 += proteinDay;
                            sumCarbDay4 += carbohydratesDay;
                        } else if (hour == 5) {
                            sumCalDay5 += caloriesDay;
                            sumFatDay5 += fatDay;
                            sumProtDay5 += proteinDay;
                            sumCarbDay5 += carbohydratesDay;
                        } else if (hour == 6) {
                            sumCalDay6 += caloriesDay;
                            sumFatDay6 += fatDay;
                            sumProtDay6 += proteinDay;
                            sumCarbDay6 += carbohydratesDay;
                        } else if (hour == 7) {
                            sumCalDay7 += caloriesDay;
                            sumFatDay7 += fatDay;
                            sumProtDay7 += proteinDay;
                            sumCarbDay7 += carbohydratesDay;
                        } else if (hour == 8) {
                            sumCalDay8 += caloriesDay;
                            sumFatDay8 += fatDay;
                            sumProtDay8 += proteinDay;
                            sumCarbDay8 += carbohydratesDay;
                        } else if (hour == 9) {
                            sumCalDay9 += caloriesDay;
                            sumFatDay9 += fatDay;
                            sumProtDay9 += proteinDay;
                            sumCarbDay9 += carbohydratesDay;
                        } else if (hour == 10) {
                            sumCalDay10 += caloriesDay;
                            sumFatDay10 += fatDay;
                            sumProtDay10 += proteinDay;
                            sumCarbDay10 += carbohydratesDay;
                        } else if (hour == 11) {
                            sumCalDay11 += caloriesDay;
                            sumFatDay11 += fatDay;
                            sumProtDay11 += proteinDay;
                            sumCarbDay11 += carbohydratesDay;
                        } else if (hour == 12) {
                            sumCalDay12 += caloriesDay;
                            sumFatDay12 += fatDay;
                            sumProtDay12 += proteinDay;
                            sumCarbDay12 += carbohydratesDay;
                        } else if (hour == 13) {
                            sumCalDay13 += caloriesDay;
                            sumFatDay13 += fatDay;
                            sumProtDay13 += proteinDay;
                            sumCarbDay13 += carbohydratesDay;
                        } else if (hour == 14) {
                            sumCalDay14 += caloriesDay;
                            sumFatDay14 += fatDay;
                            sumProtDay14 += proteinDay;
                            sumCarbDay14 += carbohydratesDay;
                        } else if (hour == 15) {
                            sumCalDay15 += caloriesDay;
                            sumFatDay15 += fatDay;
                            sumProtDay15 += proteinDay;
                            sumCarbDay15 += carbohydratesDay;
                        } else if (hour == 16) {
                            sumCalDay16 += caloriesDay;
                            sumFatDay16 += fatDay;
                            sumProtDay16 += proteinDay;
                            sumCarbDay16 += carbohydratesDay;
                        } else if (hour == 17) {
                            sumCalDay17 += caloriesDay;
                            sumFatDay17 += fatDay;
                            sumProtDay17 += proteinDay;
                            sumCarbDay17 += carbohydratesDay;
                        } else if (hour == 18) {
                            sumCalDay18 += caloriesDay;
                            sumFatDay18 += fatDay;
                            sumProtDay18 += proteinDay;
                            sumCarbDay18 += carbohydratesDay;
                        } else if (hour == 19) {
                            sumCalDay19 += caloriesDay;
                            sumFatDay19 += fatDay;
                            sumProtDay19 += proteinDay;
                            sumCarbDay19 += carbohydratesDay;
                        } else if (hour == 20) {
                            sumCalDay20 += caloriesDay;
                            sumFatDay20 += fatDay;
                            sumProtDay20 += proteinDay;
                            sumCarbDay20 += carbohydratesDay;
                        } else if (hour == 21) {
                            sumCalDay21 += caloriesDay;
                            sumFatDay21 += fatDay;
                            sumProtDay21 += proteinDay;
                            sumCarbDay21 += carbohydratesDay;
                        } else if (hour == 22) {
                            sumCalDay22 += caloriesDay;
                            sumFatDay22 += fatDay;
                            sumProtDay22 += proteinDay;
                            sumCarbDay22 += carbohydratesDay;
                        } else if (hour == 23) {
                            sumCalDay23 += caloriesDay;
                            sumFatDay23 += fatDay;
                            sumProtDay23 += proteinDay;
                            sumCarbDay23 += carbohydratesDay;
                        }
                    } while (cursor.moveToNext());

                    stringBuilder.append('\n')
                            .append("Вы получили : ")
                            .append('\n')
                            .append(Math.round(sumCalDay0 + sumCalDay1 + sumCalDay2 + sumCalDay3 + sumCalDay4 + sumCalDay5 + sumCalDay6 + sumCalDay7 + sumCalDay8 + sumCalDay9 + sumCalDay10 + sumCalDay11 + sumCalDay12 + sumCalDay13 + sumCalDay14 + sumCalDay15 + sumCalDay16 + sumCalDay17 + sumCalDay18 + sumCalDay19 + sumCalDay20 + sumCalDay21 + sumCalDay22 + sumCalDay23))
                            .append(" калорий,")
                            .append('\n')
                            .append(Math.round(sumFatDay0 + sumFatDay1 + sumFatDay2 + sumFatDay3 + sumFatDay4 + sumFatDay5 + sumFatDay6 + sumFatDay7 + sumFatDay8 + sumFatDay9 + sumFatDay10 + sumFatDay11 + sumFatDay12 + sumFatDay13 + sumFatDay14 + sumFatDay15 + sumFatDay16 + sumFatDay17 + sumFatDay18 + sumFatDay19 + sumFatDay20 + sumFatDay21 + sumFatDay22 + sumFatDay23))
                            .append("жиров, ")
                            .append('\n')
                            .append(Math.round(sumProtDay0 + sumProtDay1 + sumProtDay2 + sumProtDay3 + sumProtDay4 + sumProtDay5 + sumProtDay6 + sumProtDay7 + sumProtDay8 + sumProtDay9 + sumProtDay10 + sumProtDay11 + sumProtDay12 + sumProtDay13 + sumProtDay14 + sumProtDay15 + sumProtDay16 + sumProtDay17 + sumProtDay18 + sumProtDay19 + sumProtDay20 + sumProtDay21 + sumProtDay22 + sumProtDay23))
                            .append("белка, ")
                            .append('\n')
                            .append(Math.round(sumCarbDay0 + sumCarbDay1 + sumCarbDay2 + sumCarbDay3 + sumCarbDay4 + sumCarbDay5 + sumCarbDay6 + sumCarbDay7 + sumCarbDay8 + sumCarbDay9 + sumCarbDay10 + sumCarbDay11 + sumCarbDay12 + sumCarbDay13 + sumCarbDay14 + sumCarbDay15 + sumCarbDay16 + sumCarbDay17 + sumCarbDay18 + sumCarbDay19 + sumCarbDay20 + sumCarbDay21 + sumCarbDay22 + sumCarbDay23))
                            .append("углеводов. ")
                            .append('\n');

                    GraphView graphCal = (GraphView) findViewById(R.id.graphCal);
                    graphCal.removeAllSeries();
                    graphCal.setTitle("График калорий");
                    graphCal.getViewport().setXAxisBoundsManual(true);
                    graphCal.getViewport().setMinX(10);
                    graphCal.getViewport().setMaxX(24);
                    graphCal.getViewport().setYAxisBoundsManual(true);
                    graphCal.getViewport().setMinY(0);
                    graphCal.getViewport().setMaxY(2000);
                    graphCal.getViewport().setScalable(true);
                    graphCal.getViewport().setScalableY(true);

                    GraphView graphFat = (GraphView) findViewById(R.id.graphFat);
                    graphFat.removeAllSeries();
                    graphFat.setTitle("График жиров");
                    graphFat.getViewport().setXAxisBoundsManual(true);
                    graphFat.getViewport().setMinX(10);
                    graphFat.getViewport().setMaxX(24);
                    graphFat.getViewport().setYAxisBoundsManual(true);
                    graphFat.getViewport().setMinY(0);
                    graphFat.getViewport().setMaxY(150);
                    graphFat.getViewport().setScalable(true);
                    graphFat.getViewport().setScalableY(true);

                    GraphView graphProt = (GraphView) findViewById(R.id.graphProt);
                    graphProt.removeAllSeries();
                    graphProt.setTitle("График белка");
                    graphProt.getViewport().setXAxisBoundsManual(true);
                    graphProt.getViewport().setMinX(10);
                    graphProt.getViewport().setMaxX(24);
                    graphProt.getViewport().setYAxisBoundsManual(true);
                    graphProt.getViewport().setMinY(0);
                    graphProt.getViewport().setMaxY(100);
                    graphProt.getViewport().setScalable(true);
                    graphProt.getViewport().setScalableY(true);

                    GraphView graphCarb = (GraphView) findViewById(R.id.graphCarb);
                    graphCarb.removeAllSeries();
                    graphCarb.setTitle("График углеводов");
                    graphCarb.getViewport().setXAxisBoundsManual(true);
                    graphCarb.getViewport().setMinX(10);
                    graphCarb.getViewport().setMaxX(24);
                    graphCarb.getViewport().setYAxisBoundsManual(true);
                    graphCarb.getViewport().setMinY(0);
                    graphCarb.getViewport().setMaxY(150);
                    graphCarb.getViewport().setScalable(true);
                    graphCarb.getViewport().setScalableY(true);

                    PointsGraphSeries<DataPoint> caloriesGraph = new PointsGraphSeries<>(new DataPoint[]{
                            new DataPoint(0, sumCalDay0),
                            new DataPoint(1, sumCalDay1),
                            new DataPoint(2, sumCalDay2),
                            new DataPoint(3, sumCalDay3),
                            new DataPoint(4, sumCalDay4),
                            new DataPoint(5, sumCalDay5),
                            new DataPoint(6, sumCalDay6),
                            new DataPoint(7, sumCalDay7),
                            new DataPoint(8, sumCalDay8),
                            new DataPoint(9, sumCalDay9),
                            new DataPoint(10, sumCalDay10),
                            new DataPoint(11, sumCalDay11),
                            new DataPoint(12, sumCalDay12),
                            new DataPoint(13, sumCalDay13),
                            new DataPoint(14, sumCalDay14),
                            new DataPoint(15, sumCalDay15),
                            new DataPoint(16, sumCalDay16),
                            new DataPoint(17, sumCalDay17),
                            new DataPoint(18, sumCalDay18),
                            new DataPoint(19, sumCalDay19),
                            new DataPoint(20, sumCalDay20),
                            new DataPoint(21, sumCalDay21),
                            new DataPoint(22, sumCalDay22),
                            new DataPoint(23, sumCalDay23),
                    });
                    caloriesGraph.setColor(Color.GREEN);

                    PointsGraphSeries<DataPoint> fatGraph = new PointsGraphSeries<>(new DataPoint[]{
                            new DataPoint(0, sumFatDay0),
                            new DataPoint(1, sumFatDay1),
                            new DataPoint(2, sumFatDay2),
                            new DataPoint(3, sumFatDay3),
                            new DataPoint(4, sumFatDay4),
                            new DataPoint(5, sumFatDay5),
                            new DataPoint(6, sumFatDay6),
                            new DataPoint(7, sumFatDay7),
                            new DataPoint(8, sumFatDay8),
                            new DataPoint(9, sumFatDay9),
                            new DataPoint(10, sumFatDay10),
                            new DataPoint(11, sumFatDay11),
                            new DataPoint(12, sumFatDay12),
                            new DataPoint(13, sumFatDay13),
                            new DataPoint(14, sumFatDay14),
                            new DataPoint(15, sumFatDay15),
                            new DataPoint(16, sumFatDay16),
                            new DataPoint(17, sumFatDay17),
                            new DataPoint(18, sumFatDay18),
                            new DataPoint(19, sumFatDay19),
                            new DataPoint(20, sumFatDay20),
                            new DataPoint(21, sumFatDay21),
                            new DataPoint(22, sumFatDay22),
                            new DataPoint(23, sumFatDay23),
                    });
                    fatGraph.setColor(Color.MAGENTA);

                    PointsGraphSeries<DataPoint> proteinGraph = new PointsGraphSeries<>(new DataPoint[]{
                            new DataPoint(0, sumProtDay0),
                            new DataPoint(1, sumProtDay1),
                            new DataPoint(2, sumProtDay2),
                            new DataPoint(3, sumProtDay3),
                            new DataPoint(4, sumProtDay4),
                            new DataPoint(5, sumProtDay5),
                            new DataPoint(6, sumProtDay6),
                            new DataPoint(7, sumProtDay7),
                            new DataPoint(8, sumProtDay8),
                            new DataPoint(9, sumProtDay9),
                            new DataPoint(10, sumProtDay10),
                            new DataPoint(11, sumProtDay11),
                            new DataPoint(12, sumProtDay12),
                            new DataPoint(13, sumProtDay13),
                            new DataPoint(14, sumProtDay14),
                            new DataPoint(15, sumProtDay15),
                            new DataPoint(16, sumProtDay16),
                            new DataPoint(17, sumProtDay17),
                            new DataPoint(18, sumProtDay18),
                            new DataPoint(19, sumProtDay19),
                            new DataPoint(20, sumProtDay20),
                            new DataPoint(21, sumProtDay21),
                            new DataPoint(22, sumProtDay22),
                            new DataPoint(23, sumProtDay23),
                    });
                    fatGraph.setColor(Color.YELLOW);

                    PointsGraphSeries<DataPoint> carbohydratesGraph = new PointsGraphSeries<>(new DataPoint[]{
                            new DataPoint(0, sumCarbDay0),
                            new DataPoint(1, sumCarbDay1),
                            new DataPoint(2, sumCarbDay2),
                            new DataPoint(3, sumCarbDay3),
                            new DataPoint(4, sumCarbDay4),
                            new DataPoint(5, sumCarbDay5),
                            new DataPoint(6, sumCarbDay6),
                            new DataPoint(7, sumCarbDay7),
                            new DataPoint(8, sumCarbDay8),
                            new DataPoint(9, sumCarbDay9),
                            new DataPoint(10, sumCarbDay10),
                            new DataPoint(11, sumCarbDay11),
                            new DataPoint(12, sumCarbDay12),
                            new DataPoint(13, sumCarbDay13),
                            new DataPoint(14, sumCarbDay14),
                            new DataPoint(15, sumCarbDay15),
                            new DataPoint(16, sumCarbDay16),
                            new DataPoint(17, sumCarbDay17),
                            new DataPoint(18, sumCarbDay18),
                            new DataPoint(19, sumCarbDay19),
                            new DataPoint(20, sumCarbDay20),
                            new DataPoint(21, sumCarbDay21),
                            new DataPoint(22, sumCarbDay22),
                            new DataPoint(23, sumCarbDay23),
                    });
                    fatGraph.setColor(Color.RED);

                    graphCal.addSeries(caloriesGraph);
                    graphFat.addSeries(fatGraph);
                    graphProt.addSeries(proteinGraph);
                    graphCarb.addSeries(carbohydratesGraph);

                    textViewStat.setText(stringBuilder.toString());
                } else {
                    Log.d("mLog", "0 rows");
                    textViewStat.setText("Ничего небыло найдено :'с");
                }
                cursor.close();
                break;
            case R.id.btnMonthlyStat:


                Date dateMonthly = new Date();                      // timestamp now
                Calendar calMonthly = Calendar.getInstance();       // get calendar instance
                calMonthly.setTime(dateMonthly);                           // set cal to date
                calMonthly.set(Calendar.HOUR_OF_DAY, 23);            // set hour to midnight
                calMonthly.set(Calendar.MINUTE, 59);                 // set minute in hour
                calMonthly.set(Calendar.SECOND, 59);                 // set second in minute
                calMonthly.set(Calendar.MILLISECOND, 999);            // set millis in second

                Date zeroedDateMonthly = calMonthly.getTime();
                System.out.println(zeroedDateMonthly);
                long currentDayMonthly = calMonthly.getTimeInMillis();

                calMonthly.add(Calendar.MONTH, -1);
                zeroedDate = calMonthly.getTime();
                long previousDayMonthly = calMonthly.getTimeInMillis();
                System.out.println(zeroedDate);

                String whereClauseMonthly = "date BETWEEN ? AND ?";
                String[] whereArgsMonthly = new String[]{
                        String.valueOf(previousDayMonthly), String.valueOf(currentDayMonthly)
                };

                Cursor cursorMonthly = database.query(DBHelper.TABLE_ATE, null, whereClauseMonthly, whereArgsMonthly, null, null, null);

                if (cursorMonthly.moveToFirst()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    int calorieslIndex = cursorMonthly.getColumnIndex(DBHelper.ATE_CALORIES);
                    int fatlIndex = cursorMonthly.getColumnIndex(DBHelper.ATE_FAT);
                    int proteinlIndex = cursorMonthly.getColumnIndex(DBHelper.ATE_PROTEIN);
                    int carbohydrateslIndex = cursorMonthly.getColumnIndex(DBHelper.ATE_CARBOHYDRATES);
                    int dateIndex = cursorMonthly.getColumnIndex(DBHelper.ATE_DATE);

                    double sumCalDay0 = 0, sumCalDay1 = 0, sumCalDay2 = 0, sumCalDay3 = 0, sumCalDay4 = 0, sumCalDay5 = 0, sumCalDay6 = 0, sumCalDay7 = 0, sumCalDay8 = 0, sumCalDay9 = 0, sumCalDay10 = 0, sumCalDay11 = 0, sumCalDay12 = 0, sumCalDay13 = 0, sumCalDay14 = 0, sumCalDay15 = 0, sumCalDay16 = 0, sumCalDay17 = 0, sumCalDay18 = 0, sumCalDay19 = 0, sumCalDay20 = 0, sumCalDay21 = 0, sumCalDay22 = 0, sumCalDay23 = 0, sumCalDay24 = 0, sumCalDay25 = 0, sumCalDay26 = 0, sumCalDay31 = 0, sumCalDay27 = 0, sumCalDay28 = 0, sumCalDay29 = 0, sumCalDay30 = 0;
                    double sumFatDay0 = 0, sumFatDay1 = 0, sumFatDay2 = 0, sumFatDay3 = 0, sumFatDay4 = 0, sumFatDay5 = 0, sumFatDay6 = 0, sumFatDay7 = 0, sumFatDay8 = 0, sumFatDay9 = 0, sumFatDay10 = 0, sumFatDay11 = 0, sumFatDay12 = 0, sumFatDay13 = 0, sumFatDay14 = 0, sumFatDay15 = 0, sumFatDay16 = 0, sumFatDay17 = 0, sumFatDay18 = 0, sumFatDay19 = 0, sumFatDay20 = 0, sumFatDay21 = 0, sumFatDay22 = 0, sumFatDay23 = 0, sumFatDay24 = 0, sumFatDay25 = 0, sumFatDay26 = 0, sumFatDay27 = 0, sumFatDay28 = 0, sumFatDay29 = 0, sumFatDay30 = 0, sumFatDay31 = 0;
                    double sumProtDay0 = 0, sumProtDay1 = 0, sumProtDay2 = 0, sumProtDay3 = 0, sumProtDay4 = 0, sumProtDay5 = 0, sumProtDay6 = 0, sumProtDay7 = 0, sumProtDay8 = 0, sumProtDay9 = 0, sumProtDay10 = 0, sumProtDay11 = 0, sumProtDay12 = 0, sumProtDay13 = 0, sumProtDay14 = 0, sumProtDay15 = 0, sumProtDay16 = 0, sumProtDay17 = 0, sumProtDay18 = 0, sumProtDay19 = 0, sumProtDay20 = 0, sumProtDay21 = 0, sumProtDay22 = 0, sumProtDay23 = 0, sumProtDay24 = 0, sumProtDay25 = 0, sumProtDay26 = 0, sumProtDay27 = 0, sumProtDay28 = 0, sumProtDay29 = 0, sumProtDay30 = 0, sumProtDay31 = 0;
                    double sumCarbDay0 = 0, sumCarbDay1 = 0, sumCarbDay2 = 0, sumCarbDay3 = 0, sumCarbDay4 = 0, sumCarbDay5 = 0, sumCarbDay6 = 0, sumCarbDay7 = 0, sumCarbDay8 = 0, sumCarbDay9 = 0, sumCarbDay10 = 0, sumCarbDay11 = 0, sumCarbDay12 = 0, sumCarbDay13 = 0, sumCarbDay14 = 0, sumCarbDay15 = 0, sumCarbDay16 = 0, sumCarbDay17 = 0, sumCarbDay18 = 0, sumCarbDay19 = 0, sumCarbDay20 = 0, sumCarbDay21 = 0, sumCarbDay22 = 0, sumCarbDay23 = 0, sumCarbDay24 = 0, sumCarbDay25 = 0, sumCarbDay26 = 0, sumCarbDay27 = 0, sumCarbDay28 = 0, sumCarbDay29 = 0, sumCarbDay30 = 0, sumCarbDay31 = 0;
                    do {
                        long mills = cursorMonthly.getLong(dateIndex);
                        Date curDate = new Date(mills);
                        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd");
                        int day = Integer.parseInt(formatForDateNow.format(curDate));

                        double caloriesDay = Double.parseDouble(cursorMonthly.getString(calorieslIndex));
                        double fatDay = Double.parseDouble(cursorMonthly.getString(fatlIndex));
                        double proteinDay = Double.parseDouble(cursorMonthly.getString(proteinlIndex));
                        double carbohydratesDay = Double.parseDouble(cursorMonthly.getString(carbohydrateslIndex));

                        if (day == 0) {
                            sumCalDay0 += caloriesDay;
                            sumFatDay0 += fatDay;
                            sumProtDay0 += proteinDay;
                            sumCarbDay0 += carbohydratesDay;
                        } else if (day == 1) {
                            sumCalDay1 += caloriesDay;
                            sumFatDay1 += fatDay;
                            sumProtDay1 += proteinDay;
                            sumCarbDay1 += carbohydratesDay;
                        } else if (day == 2) {
                            sumCalDay2 += caloriesDay;
                            sumFatDay2 += fatDay;
                            sumProtDay2 += proteinDay;
                            sumCarbDay2 += carbohydratesDay;
                        } else if (day == 3) {
                            sumCalDay3 += caloriesDay;
                            sumFatDay3 += fatDay;
                            sumProtDay3 += proteinDay;
                            sumCarbDay3 += carbohydratesDay;
                        } else if (day == 4) {
                            sumCalDay4 += caloriesDay;
                            sumFatDay4 += fatDay;
                            sumProtDay4 += proteinDay;
                            sumCarbDay4 += carbohydratesDay;
                        } else if (day == 5) {
                            sumCalDay5 += caloriesDay;
                            sumFatDay5 += fatDay;
                            sumProtDay5 += proteinDay;
                            sumCarbDay5 += carbohydratesDay;
                        } else if (day == 6) {
                            sumCalDay6 += caloriesDay;
                            sumFatDay6 += fatDay;
                            sumProtDay6 += proteinDay;
                            sumCarbDay6 += carbohydratesDay;
                        } else if (day == 7) {
                            sumCalDay7 += caloriesDay;
                            sumFatDay7 += fatDay;
                            sumProtDay7 += proteinDay;
                            sumCarbDay7 += carbohydratesDay;
                        } else if (day == 8) {
                            sumCalDay8 += caloriesDay;
                            sumFatDay8 += fatDay;
                            sumProtDay8 += proteinDay;
                            sumCarbDay8 += carbohydratesDay;
                        } else if (day == 9) {
                            sumCalDay9 += caloriesDay;
                            sumFatDay9 += fatDay;
                            sumProtDay9 += proteinDay;
                            sumCarbDay9 += carbohydratesDay;
                        } else if (day == 10) {
                            sumCalDay10 += caloriesDay;
                            sumFatDay10 += fatDay;
                            sumProtDay10 += proteinDay;
                            sumCarbDay10 += carbohydratesDay;
                        } else if (day == 11) {
                            sumCalDay11 += caloriesDay;
                            sumFatDay11 += fatDay;
                            sumProtDay11 += proteinDay;
                            sumCarbDay11 += carbohydratesDay;
                        } else if (day == 12) {
                            sumCalDay12 += caloriesDay;
                            sumFatDay12 += fatDay;
                            sumProtDay12 += proteinDay;
                            sumCarbDay12 += carbohydratesDay;
                        } else if (day == 13) {
                            sumCalDay13 += caloriesDay;
                            sumFatDay13 += fatDay;
                            sumProtDay13 += proteinDay;
                            sumCarbDay13 += carbohydratesDay;
                        } else if (day == 14) {
                            sumCalDay14 += caloriesDay;
                            sumFatDay14 += fatDay;
                            sumProtDay14 += proteinDay;
                            sumCarbDay14 += carbohydratesDay;
                        } else if (day == 15) {
                            sumCalDay15 += caloriesDay;
                            sumFatDay15 += fatDay;
                            sumProtDay15 += proteinDay;
                            sumCarbDay15 += carbohydratesDay;
                        } else if (day == 16) {
                            sumCalDay16 += caloriesDay;
                            sumFatDay16 += fatDay;
                            sumProtDay16 += proteinDay;
                            sumCarbDay16 += carbohydratesDay;
                        } else if (day == 17) {
                            sumCalDay17 += caloriesDay;
                            sumFatDay17 += fatDay;
                            sumProtDay17 += proteinDay;
                            sumCarbDay17 += carbohydratesDay;
                        } else if (day == 18) {
                            sumCalDay18 += caloriesDay;
                            sumFatDay18 += fatDay;
                            sumProtDay18 += proteinDay;
                            sumCarbDay18 += carbohydratesDay;
                        } else if (day == 19) {
                            sumCalDay19 += caloriesDay;
                            sumFatDay19 += fatDay;
                            sumProtDay19 += proteinDay;
                            sumCarbDay19 += carbohydratesDay;
                        } else if (day == 20) {
                            sumCalDay20 += caloriesDay;
                            sumFatDay20 += fatDay;
                            sumProtDay20 += proteinDay;
                            sumCarbDay20 += carbohydratesDay;
                        } else if (day == 21) {
                            sumCalDay21 += caloriesDay;
                            sumFatDay21 += fatDay;
                            sumProtDay21 += proteinDay;
                            sumCarbDay21 += carbohydratesDay;
                        } else if (day == 22) {
                            sumCalDay22 += caloriesDay;
                            sumFatDay22 += fatDay;
                            sumProtDay22 += proteinDay;
                            sumCarbDay22 += carbohydratesDay;
                        } else if (day == 23) {
                            sumCalDay23 += caloriesDay;
                            sumFatDay23 += fatDay;
                            sumProtDay23 += proteinDay;
                            sumCarbDay23 += carbohydratesDay;
                        } else if (day == 24) {
                            sumCalDay24 += caloriesDay;
                            sumFatDay24 += fatDay;
                            sumProtDay24 += proteinDay;
                            sumCarbDay24 += carbohydratesDay;
                        } else if (day == 25) {
                            sumCalDay25 += caloriesDay;
                            sumFatDay25 += fatDay;
                            sumProtDay25 += proteinDay;
                            sumCarbDay25 += carbohydratesDay;
                        } else if (day == 26) {
                            sumCalDay26 += caloriesDay;
                            sumFatDay26 += fatDay;
                            sumProtDay26 += proteinDay;
                            sumCarbDay26 += carbohydratesDay;
                        } else if (day == 27) {
                            sumCalDay27 += caloriesDay;
                            sumFatDay27 += fatDay;
                            sumProtDay27 += proteinDay;
                            sumCarbDay27 += carbohydratesDay;
                        } else if (day == 28) {
                            sumCalDay28 += caloriesDay;
                            sumFatDay28 += fatDay;
                            sumProtDay28 += proteinDay;
                            sumCarbDay28 += carbohydratesDay;
                        } else if (day == 29) {
                            sumCalDay29 += caloriesDay;
                            sumFatDay29 += fatDay;
                            sumProtDay29 += proteinDay;
                            sumCarbDay29 += carbohydratesDay;
                        } else if (day == 30) {
                            sumCalDay30 += caloriesDay;
                            sumFatDay30 += fatDay;
                            sumProtDay30 += proteinDay;
                            sumCarbDay30 += carbohydratesDay;
                        } else if (day == 31) {
                            sumCalDay31 += caloriesDay;
                            sumFatDay31 += fatDay;
                            sumProtDay31 += proteinDay;
                            sumCarbDay31 += carbohydratesDay;
                        }
                    } while (cursorMonthly.moveToNext());

                    stringBuilder.append('\n')
                            .append("Вы получили : ")
                            .append('\n')
                            .append(Math.round(sumCalDay0 + sumCalDay1 + sumCalDay2 + sumCalDay3 + sumCalDay4 + sumCalDay5 + sumCalDay6 + sumCalDay7 + sumCalDay8 + sumCalDay9 + sumCalDay10 + sumCalDay11 + sumCalDay12 + sumCalDay13 + sumCalDay14 + sumCalDay15 + sumCalDay16 + sumCalDay17 + sumCalDay18 + sumCalDay19 + sumCalDay20 + sumCalDay21 + sumCalDay22 + sumCalDay23 + sumCalDay24 + sumCalDay25 + sumCalDay26 + sumCalDay27 + sumCalDay28 + sumCalDay29 + sumCalDay30 + sumCalDay31))
                            .append(" калорий,")
                            .append('\n')
                            .append(Math.round(sumFatDay0 + sumFatDay1 + sumFatDay2 + sumFatDay3 + sumFatDay4 + sumFatDay5 + sumFatDay6 + sumFatDay7 + sumFatDay8 + sumFatDay9 + sumFatDay10 + sumFatDay11 + sumFatDay12 + sumFatDay13 + sumFatDay14 + sumFatDay15 + sumFatDay16 + sumFatDay17 + sumFatDay18 + sumFatDay19 + sumFatDay20 + sumFatDay21 + sumFatDay22 + sumFatDay23 + sumFatDay24 + sumFatDay25 + sumFatDay26 + sumFatDay27 + sumFatDay28 + sumFatDay29 + sumFatDay30 + sumFatDay31))
                            .append("жиров, ")
                            .append('\n')
                            .append(Math.round(sumProtDay0 + sumProtDay1 + sumProtDay2 + sumProtDay3 + sumProtDay4 + sumProtDay5 + sumProtDay6 + sumProtDay7 + sumProtDay8 + sumProtDay9 + sumProtDay10 + sumProtDay11 + sumProtDay12 + sumProtDay13 + sumProtDay14 + sumProtDay15 + sumProtDay16 + sumProtDay17 + sumProtDay18 + sumProtDay19 + sumProtDay20 + sumProtDay21 + sumProtDay22 + sumProtDay23 + sumProtDay24 + sumProtDay25 + sumProtDay26 + sumProtDay27 + sumProtDay28 + sumProtDay29 + sumProtDay30 + sumProtDay31))
                            .append("белка, ")
                            .append('\n')
                            .append(Math.round(sumCarbDay0 + sumCarbDay1 + sumCarbDay2 + sumCarbDay3 + sumCarbDay4 + sumCarbDay5 + sumCarbDay6 + sumCarbDay7 + sumCarbDay8 + sumCarbDay9 + sumCarbDay10 + sumCarbDay11 + sumCarbDay12 + sumCarbDay13 + sumCarbDay14 + sumCarbDay15 + sumCarbDay16 + sumCarbDay17 + sumCarbDay18 + sumCarbDay19 + sumCarbDay20 + sumCarbDay21 + sumCarbDay22 + sumCarbDay23 + sumCarbDay24 + sumCarbDay25 + sumCarbDay26 + sumCarbDay27 + sumCarbDay28 + sumCarbDay29 + sumCarbDay30 + sumCarbDay31))
                            .append("углеводов. ")
                            .append('\n');

                    GraphView graphCal = (GraphView) findViewById(R.id.graphCal);
                    graphCal.removeAllSeries();
                    graphCal.setTitle("График калорий");
                    graphCal.getViewport().setXAxisBoundsManual(true);
                    graphCal.getViewport().setMinX(10);
                    graphCal.getViewport().setMaxX(31);
                    graphCal.getViewport().setYAxisBoundsManual(true);
                    graphCal.getViewport().setMinY(0);
                    graphCal.getViewport().setMaxY(4000);
                    graphCal.getViewport().setScalable(true);
                    graphCal.getViewport().setScalableY(true);

                    GraphView graphFat = (GraphView) findViewById(R.id.graphFat);
                    graphFat.removeAllSeries();
                    graphFat.setTitle("График жиров");
                    graphFat.getViewport().setXAxisBoundsManual(true);
                    graphFat.getViewport().setMinX(10);
                    graphFat.getViewport().setMaxX(31);
                    graphFat.getViewport().setYAxisBoundsManual(true);
                    graphFat.getViewport().setMinY(0);
                    graphFat.getViewport().setMaxY(400);
                    graphFat.getViewport().setScalable(true);
                    graphFat.getViewport().setScalableY(true);

                    GraphView graphProt = (GraphView) findViewById(R.id.graphProt);
                    graphProt.removeAllSeries();
                    graphProt.setTitle("График белка");
                    graphProt.getViewport().setXAxisBoundsManual(true);
                    graphProt.getViewport().setMinX(10);
                    graphProt.getViewport().setMaxX(31);
                    graphProt.getViewport().setYAxisBoundsManual(true);
                    graphProt.getViewport().setMinY(0);
                    graphProt.getViewport().setMaxY(200);
                    graphProt.getViewport().setScalable(true);
                    graphProt.getViewport().setScalableY(true);

                    GraphView graphCarb = (GraphView) findViewById(R.id.graphCarb);
                    graphCarb.removeAllSeries();
                    graphCarb.setTitle("График углеводов");
                    graphCarb.getViewport().setXAxisBoundsManual(true);
                    graphCarb.getViewport().setMinX(10);
                    graphCarb.getViewport().setMaxX(31);
                    graphCarb.getViewport().setYAxisBoundsManual(true);
                    graphCarb.getViewport().setMinY(0);
                    graphCarb.getViewport().setMaxY(400);
                    graphCarb.getViewport().setScalable(true);
                    graphCarb.getViewport().setScalableY(true);


                    PointsGraphSeries<DataPoint> caloriesGraph = new PointsGraphSeries<>(new DataPoint[]{
                            new DataPoint(0, sumCalDay0),
                            new DataPoint(1, sumCalDay1),
                            new DataPoint(2, sumCalDay2),
                            new DataPoint(3, sumCalDay3),
                            new DataPoint(4, sumCalDay4),
                            new DataPoint(5, sumCalDay5),
                            new DataPoint(6, sumCalDay6),
                            new DataPoint(7, sumCalDay7),
                            new DataPoint(8, sumCalDay8),
                            new DataPoint(9, sumCalDay9),
                            new DataPoint(10, sumCalDay10),
                            new DataPoint(11, sumCalDay11),
                            new DataPoint(12, sumCalDay12),
                            new DataPoint(13, sumCalDay13),
                            new DataPoint(14, sumCalDay14),
                            new DataPoint(15, sumCalDay15),
                            new DataPoint(16, sumCalDay16),
                            new DataPoint(17, sumCalDay17),
                            new DataPoint(18, sumCalDay18),
                            new DataPoint(19, sumCalDay19),
                            new DataPoint(20, sumCalDay20),
                            new DataPoint(21, sumCalDay21),
                            new DataPoint(22, sumCalDay22),
                            new DataPoint(23, sumCalDay23),
                            new DataPoint(24, sumCalDay24),
                            new DataPoint(25, sumCalDay25),
                            new DataPoint(26, sumCalDay26),
                            new DataPoint(27, sumCalDay27),
                            new DataPoint(28, sumCalDay28),
                            new DataPoint(29, sumCalDay29),
                            new DataPoint(30, sumCalDay30),
                            new DataPoint(31, sumCalDay31),
                    });
                    caloriesGraph.setColor(Color.GREEN);

                    PointsGraphSeries<DataPoint> fatGraph = new PointsGraphSeries<>(new DataPoint[]{
                            new DataPoint(0, sumFatDay0),
                            new DataPoint(1, sumFatDay1),
                            new DataPoint(2, sumFatDay2),
                            new DataPoint(3, sumFatDay3),
                            new DataPoint(4, sumFatDay4),
                            new DataPoint(5, sumFatDay5),
                            new DataPoint(6, sumFatDay6),
                            new DataPoint(7, sumFatDay7),
                            new DataPoint(8, sumFatDay8),
                            new DataPoint(9, sumFatDay9),
                            new DataPoint(10, sumFatDay10),
                            new DataPoint(11, sumFatDay11),
                            new DataPoint(12, sumFatDay12),
                            new DataPoint(13, sumFatDay13),
                            new DataPoint(14, sumFatDay14),
                            new DataPoint(15, sumFatDay15),
                            new DataPoint(16, sumFatDay16),
                            new DataPoint(17, sumFatDay17),
                            new DataPoint(18, sumFatDay18),
                            new DataPoint(19, sumFatDay19),
                            new DataPoint(20, sumFatDay20),
                            new DataPoint(21, sumFatDay21),
                            new DataPoint(22, sumFatDay22),
                            new DataPoint(23, sumFatDay23),
                            new DataPoint(24, sumFatDay24),
                            new DataPoint(25, sumFatDay25),
                            new DataPoint(26, sumFatDay26),
                            new DataPoint(27, sumFatDay27),
                            new DataPoint(28, sumFatDay28),
                            new DataPoint(29, sumFatDay29),
                            new DataPoint(30, sumFatDay30),
                            new DataPoint(31, sumFatDay31),
                    });
                    fatGraph.setColor(Color.MAGENTA);

                    PointsGraphSeries<DataPoint> proteinGraph = new PointsGraphSeries<>(new DataPoint[]{
                            new DataPoint(0, sumProtDay0),
                            new DataPoint(1, sumProtDay1),
                            new DataPoint(2, sumProtDay2),
                            new DataPoint(3, sumProtDay3),
                            new DataPoint(4, sumProtDay4),
                            new DataPoint(5, sumProtDay5),
                            new DataPoint(6, sumProtDay6),
                            new DataPoint(7, sumProtDay7),
                            new DataPoint(8, sumProtDay8),
                            new DataPoint(9, sumProtDay9),
                            new DataPoint(10, sumProtDay10),
                            new DataPoint(11, sumProtDay11),
                            new DataPoint(12, sumProtDay12),
                            new DataPoint(13, sumProtDay13),
                            new DataPoint(14, sumProtDay14),
                            new DataPoint(15, sumProtDay15),
                            new DataPoint(16, sumProtDay16),
                            new DataPoint(17, sumProtDay17),
                            new DataPoint(18, sumProtDay18),
                            new DataPoint(19, sumProtDay19),
                            new DataPoint(20, sumProtDay20),
                            new DataPoint(21, sumProtDay21),
                            new DataPoint(22, sumProtDay22),
                            new DataPoint(23, sumProtDay23),
                            new DataPoint(24, sumProtDay24),
                            new DataPoint(25, sumProtDay25),
                            new DataPoint(26, sumProtDay26),
                            new DataPoint(27, sumProtDay27),
                            new DataPoint(28, sumProtDay28),
                            new DataPoint(29, sumProtDay29),
                            new DataPoint(30, sumProtDay30),
                            new DataPoint(31, sumProtDay31),
                    });
                    fatGraph.setColor(Color.YELLOW);

                    PointsGraphSeries<DataPoint> carbohydratesGraph = new PointsGraphSeries<>(new DataPoint[]{
                            new DataPoint(0, sumCarbDay0),
                            new DataPoint(1, sumCarbDay1),
                            new DataPoint(2, sumCarbDay2),
                            new DataPoint(3, sumCarbDay3),
                            new DataPoint(4, sumCarbDay4),
                            new DataPoint(5, sumCarbDay5),
                            new DataPoint(6, sumCarbDay6),
                            new DataPoint(7, sumCarbDay7),
                            new DataPoint(8, sumCarbDay8),
                            new DataPoint(9, sumCarbDay9),
                            new DataPoint(10, sumCarbDay10),
                            new DataPoint(11, sumCarbDay11),
                            new DataPoint(12, sumCarbDay12),
                            new DataPoint(13, sumCarbDay13),
                            new DataPoint(14, sumCarbDay14),
                            new DataPoint(15, sumCarbDay15),
                            new DataPoint(16, sumCarbDay16),
                            new DataPoint(17, sumCarbDay17),
                            new DataPoint(18, sumCarbDay18),
                            new DataPoint(19, sumCarbDay19),
                            new DataPoint(20, sumCarbDay20),
                            new DataPoint(21, sumCarbDay21),
                            new DataPoint(22, sumCarbDay22),
                            new DataPoint(23, sumCarbDay23),
                            new DataPoint(24, sumCarbDay24),
                            new DataPoint(25, sumCarbDay25),
                            new DataPoint(26, sumCarbDay26),
                            new DataPoint(27, sumCarbDay27),
                            new DataPoint(28, sumCarbDay28),
                            new DataPoint(29, sumCarbDay29),
                            new DataPoint(30, sumCarbDay30),
                            new DataPoint(31, sumCarbDay31),
                    });
                    fatGraph.setColor(Color.RED);

                    graphCal.addSeries(caloriesGraph);
                    graphFat.addSeries(fatGraph);
                    graphProt.addSeries(proteinGraph);
                    graphCarb.addSeries(carbohydratesGraph);

                    textViewStat.setText(stringBuilder.toString());
                } else {
                    Log.d("mLog", "0 rows");
                    textViewStat.setText("Ничего небыло найдено :'с");
                }
                cursorMonthly.close();

                break;
            case R.id.btnyearlyStat:

                Date dateYear = new Date();                      // timestamp now
                Calendar calYear = Calendar.getInstance();       // get calendar instance
                calYear.setTime(dateYear);                           // set cal to date
                calYear.set(Calendar.MONTH, 12);
                calYear.set(Calendar.HOUR_OF_DAY, 23);            // set hour to midnight
                calYear.set(Calendar.MINUTE, 59);                 // set minute in hour
                calYear.set(Calendar.SECOND, 59);                 // set second in minute
                calYear.set(Calendar.MILLISECOND, 999);            // set millis in second

                Date zeroedYear = calYear.getTime();
                System.out.println(zeroedYear);
                long currentYear = calYear.getTimeInMillis();

                calYear.add(Calendar.YEAR, -1);
                zeroedYear = calYear.getTime();
                long previousYear = calYear.getTimeInMillis();
                System.out.println(zeroedYear);

                String whereClauseYear = "date BETWEEN ? AND ?";
                String[] whereArgsYear = new String[]{
                        String.valueOf(previousYear), String.valueOf(currentYear)
                };

                Cursor cursorYear = database.query(DBHelper.TABLE_ATE, null, whereClauseYear, whereArgsYear, null, null, null);

                if (cursorYear.moveToFirst()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    int calorieslIndex = cursorYear.getColumnIndex(DBHelper.ATE_CALORIES);
                    int fatlIndex = cursorYear.getColumnIndex(DBHelper.ATE_FAT);
                    int proteinlIndex = cursorYear.getColumnIndex(DBHelper.ATE_PROTEIN);
                    int carbohydrateslIndex = cursorYear.getColumnIndex(DBHelper.ATE_CARBOHYDRATES);
                    int dateIndex = cursorYear.getColumnIndex(DBHelper.ATE_DATE);

                    double sumCalDay0 = 0, sumCalDay1 = 0, sumCalDay2 = 0, sumCalDay3 = 0, sumCalDay4 = 0, sumCalDay5 = 0, sumCalDay6 = 0, sumCalDay7 = 0, sumCalDay8 = 0, sumCalDay9 = 0, sumCalDay10 = 0, sumCalDay11 = 0, sumCalDay12 = 0;
                    double sumFatDay0 = 0, sumFatDay1 = 0, sumFatDay2 = 0, sumFatDay3 = 0, sumFatDay4 = 0, sumFatDay5 = 0, sumFatDay6 = 0, sumFatDay7 = 0, sumFatDay8 = 0, sumFatDay9 = 0, sumFatDay10 = 0, sumFatDay11 = 0, sumFatDay12 = 0;
                    double sumProtDay0 = 0, sumProtDay1 = 0, sumProtDay2 = 0, sumProtDay3 = 0, sumProtDay4 = 0, sumProtDay5 = 0, sumProtDay6 = 0, sumProtDay7 = 0, sumProtDay8 = 0, sumProtDay9 = 0, sumProtDay10 = 0, sumProtDay11 = 0, sumProtDay12 = 0;
                    double sumCarbDay0 = 0, sumCarbDay1 = 0, sumCarbDay2 = 0, sumCarbDay3 = 0, sumCarbDay4 = 0, sumCarbDay5 = 0, sumCarbDay6 = 0, sumCarbDay7 = 0, sumCarbDay8 = 0, sumCarbDay9 = 0, sumCarbDay10 = 0, sumCarbDay11 = 0, sumCarbDay12 = 0;
                    do {
                        long mills = cursorYear.getLong(dateIndex);
                        Date curDate = new Date(mills);
                        SimpleDateFormat formatForDateNow = new SimpleDateFormat("MM");
                        int month = Integer.parseInt(formatForDateNow.format(curDate));

                        double caloriesDay = Double.parseDouble(cursorYear.getString(calorieslIndex));
                        double fatDay = Double.parseDouble(cursorYear.getString(fatlIndex));
                        double proteinDay = Double.parseDouble(cursorYear.getString(proteinlIndex));
                        double carbohydratesDay = Double.parseDouble(cursorYear.getString(carbohydrateslIndex));

                        if (month == 0) {
                            sumCalDay0 += caloriesDay;
                            sumFatDay0 += fatDay;
                            sumProtDay0 += proteinDay;
                            sumCarbDay0 += carbohydratesDay;
                        } else if (month == 1) {
                            sumCalDay1 += caloriesDay;
                            sumFatDay1 += fatDay;
                            sumProtDay1 += proteinDay;
                            sumCarbDay1 += carbohydratesDay;
                        } else if (month == 2) {
                            sumCalDay2 += caloriesDay;
                            sumFatDay2 += fatDay;
                            sumProtDay2 += proteinDay;
                            sumCarbDay2 += carbohydratesDay;
                        } else if (month == 3) {
                            sumCalDay3 += caloriesDay;
                            sumFatDay3 += fatDay;
                            sumProtDay3 += proteinDay;
                            sumCarbDay3 += carbohydratesDay;
                        } else if (month == 4) {
                            sumCalDay4 += caloriesDay;
                            sumFatDay4 += fatDay;
                            sumProtDay4 += proteinDay;
                            sumCarbDay4 += carbohydratesDay;
                        } else if (month == 5) {
                            sumCalDay5 += caloriesDay;
                            sumFatDay5 += fatDay;
                            sumProtDay5 += proteinDay;
                            sumCarbDay5 += carbohydratesDay;
                        } else if (month == 6) {
                            sumCalDay6 += caloriesDay;
                            sumFatDay6 += fatDay;
                            sumProtDay6 += proteinDay;
                            sumCarbDay6 += carbohydratesDay;
                        } else if (month == 7) {
                            sumCalDay7 += caloriesDay;
                            sumFatDay7 += fatDay;
                            sumProtDay7 += proteinDay;
                            sumCarbDay7 += carbohydratesDay;
                        } else if (month == 8) {
                            sumCalDay8 += caloriesDay;
                            sumFatDay8 += fatDay;
                            sumProtDay8 += proteinDay;
                            sumCarbDay8 += carbohydratesDay;
                        } else if (month == 9) {
                            sumCalDay9 += caloriesDay;
                            sumFatDay9 += fatDay;
                            sumProtDay9 += proteinDay;
                            sumCarbDay9 += carbohydratesDay;
                        } else if (month == 10) {
                            sumCalDay10 += caloriesDay;
                            sumFatDay10 += fatDay;
                            sumProtDay10 += proteinDay;
                            sumCarbDay10 += carbohydratesDay;
                        } else if (month == 11) {
                            sumCalDay11 += caloriesDay;
                            sumFatDay11 += fatDay;
                            sumProtDay11 += proteinDay;
                            sumCarbDay11 += carbohydratesDay;
                        } else if (month == 12) {
                            sumCalDay12 += caloriesDay;
                            sumFatDay12 += fatDay;
                            sumProtDay12 += proteinDay;
                            sumCarbDay12 += carbohydratesDay;
                        }
                    } while (cursorYear.moveToNext());

                    stringBuilder.append('\n')
                            .append("Вы получили : ")
                            .append('\n')
                            .append(Math.round(sumCalDay0 + sumCalDay1 + sumCalDay2 + sumCalDay3 + sumCalDay4 + sumCalDay5 + sumCalDay6 + sumCalDay7 + sumCalDay8 + sumCalDay9 + sumCalDay10 + sumCalDay11 + sumCalDay12))
                            .append(" калорий,")
                            .append('\n')
                            .append(Math.round(sumFatDay0 + sumFatDay1 + sumFatDay2 + sumFatDay3 + sumFatDay4 + sumFatDay5 + sumFatDay6 + sumFatDay7 + sumFatDay8 + sumFatDay9 + sumFatDay10 + sumFatDay11 + sumFatDay12))
                            .append("жиров, ")
                            .append('\n')
                            .append(Math.round(sumProtDay0 + sumProtDay1 + sumProtDay2 + sumProtDay3 + sumProtDay4 + sumProtDay5 + sumProtDay6 + sumProtDay7 + sumProtDay8 + sumProtDay9 + sumProtDay10 + sumProtDay11 + sumProtDay12))
                            .append("белка, ")
                            .append('\n')
                            .append(Math.round(sumCarbDay0 + sumCarbDay1 + sumCarbDay2 + sumCarbDay3 + sumCarbDay4 + sumCarbDay5 + sumCarbDay6 + sumCarbDay7 + sumCarbDay8 + sumCarbDay9 + sumCarbDay10 + sumCarbDay11 + sumCarbDay12))
                            .append("углеводов. ")
                            .append('\n');

                    GraphView graphCal = (GraphView) findViewById(R.id.graphCal);
                    graphCal.removeAllSeries();
                    graphCal.setTitle("График калорий");
                    graphCal.getViewport().setXAxisBoundsManual(true);
                    graphCal.getViewport().setMinX(10);
                    graphCal.getViewport().setMaxX(12);
                    graphCal.getViewport().setYAxisBoundsManual(true);
                    graphCal.getViewport().setMinY(0);
                    graphCal.getViewport().setMaxY(6000);
                    graphCal.getViewport().setScalable(true);
                    graphCal.getViewport().setScalableY(true);

                    GraphView graphFat = (GraphView) findViewById(R.id.graphFat);
                    graphFat.removeAllSeries();
                    graphFat.setTitle("График жиров");
                    graphFat.getViewport().setXAxisBoundsManual(true);
                    graphFat.getViewport().setMinX(10);
                    graphFat.getViewport().setMaxX(12);
                    graphFat.getViewport().setYAxisBoundsManual(true);
                    graphFat.getViewport().setMinY(0);
                    graphFat.getViewport().setMaxY(700);
                    graphFat.getViewport().setScalable(true);
                    graphFat.getViewport().setScalableY(true);

                    GraphView graphProt = (GraphView) findViewById(R.id.graphProt);
                    graphProt.removeAllSeries();
                    graphProt.setTitle("График белка");
                    graphProt.getViewport().setXAxisBoundsManual(true);
                    graphProt.getViewport().setMinX(10);
                    graphProt.getViewport().setMaxX(12);
                    graphProt.getViewport().setYAxisBoundsManual(true);
                    graphProt.getViewport().setMinY(0);
                    graphProt.getViewport().setMaxY(300);
                    graphProt.getViewport().setScalable(true);
                    graphProt.getViewport().setScalableY(true);

                    GraphView graphCarb = (GraphView) findViewById(R.id.graphCarb);
                    graphCarb.removeAllSeries();
                    graphCarb.setTitle("График углеводов");
                    graphCarb.getViewport().setXAxisBoundsManual(true);
                    graphCarb.getViewport().setMinX(10);
                    graphCarb.getViewport().setMaxX(12);
                    graphCarb.getViewport().setYAxisBoundsManual(true);
                    graphCarb.getViewport().setMinY(0);
                    graphCarb.getViewport().setMaxY(300);
                    graphCarb.getViewport().setScalable(true);
                    graphCarb.getViewport().setScalableY(true);

                    PointsGraphSeries<DataPoint> caloriesGraph = new PointsGraphSeries<>(new DataPoint[]{
                            new DataPoint(0, sumCalDay0),
                            new DataPoint(1, sumCalDay1),
                            new DataPoint(2, sumCalDay2),
                            new DataPoint(3, sumCalDay3),
                            new DataPoint(4, sumCalDay4),
                            new DataPoint(5, sumCalDay5),
                            new DataPoint(6, sumCalDay6),
                            new DataPoint(7, sumCalDay7),
                            new DataPoint(8, sumCalDay8),
                            new DataPoint(9, sumCalDay9),
                            new DataPoint(10, sumCalDay10),
                            new DataPoint(11, sumCalDay11),
                            new DataPoint(12, sumCalDay12),
                    });
                    caloriesGraph.setColor(Color.GREEN);

                    PointsGraphSeries<DataPoint> fatGraph = new PointsGraphSeries<>(new DataPoint[]{
                            new DataPoint(0, sumFatDay0),
                            new DataPoint(1, sumFatDay1),
                            new DataPoint(2, sumFatDay2),
                            new DataPoint(3, sumFatDay3),
                            new DataPoint(4, sumFatDay4),
                            new DataPoint(5, sumFatDay5),
                            new DataPoint(6, sumFatDay6),
                            new DataPoint(7, sumFatDay7),
                            new DataPoint(8, sumFatDay8),
                            new DataPoint(9, sumFatDay9),
                            new DataPoint(10, sumFatDay10),
                            new DataPoint(11, sumFatDay11),
                            new DataPoint(12, sumFatDay12),
                    });
                    fatGraph.setColor(Color.MAGENTA);

                    PointsGraphSeries<DataPoint> proteinGraph = new PointsGraphSeries<>(new DataPoint[]{
                            new DataPoint(0, sumProtDay0),
                            new DataPoint(1, sumProtDay1),
                            new DataPoint(2, sumProtDay2),
                            new DataPoint(3, sumProtDay3),
                            new DataPoint(4, sumProtDay4),
                            new DataPoint(5, sumProtDay5),
                            new DataPoint(6, sumProtDay6),
                            new DataPoint(7, sumProtDay7),
                            new DataPoint(8, sumProtDay8),
                            new DataPoint(9, sumProtDay9),
                            new DataPoint(10, sumProtDay10),
                            new DataPoint(11, sumProtDay11),
                            new DataPoint(12, sumProtDay12),
                    });
                    fatGraph.setColor(Color.YELLOW);

                    PointsGraphSeries<DataPoint> carbohydratesGraph = new PointsGraphSeries<>(new DataPoint[]{
                            new DataPoint(0, sumCarbDay0),
                            new DataPoint(1, sumCarbDay1),
                            new DataPoint(2, sumCarbDay2),
                            new DataPoint(3, sumCarbDay3),
                            new DataPoint(4, sumCarbDay4),
                            new DataPoint(5, sumCarbDay5),
                            new DataPoint(6, sumCarbDay6),
                            new DataPoint(7, sumCarbDay7),
                            new DataPoint(8, sumCarbDay8),
                            new DataPoint(9, sumCarbDay9),
                            new DataPoint(10, sumCarbDay10),
                            new DataPoint(11, sumCarbDay11),
                            new DataPoint(12, sumCarbDay12),
                    });
                    fatGraph.setColor(Color.RED);

                    graphCal.addSeries(caloriesGraph);
                    graphFat.addSeries(fatGraph);
                    graphProt.addSeries(proteinGraph);
                    graphCarb.addSeries(carbohydratesGraph);

                    textViewStat.setText(stringBuilder.toString());
                } else {
                    Log.d("mLog", "0 rows");
                    textViewStat.setText("Ничего небыло найдено :'с");
                }
                cursorYear.close();

            default:
                break;
        }
    }
}