package ua.nure.ekker.healthcare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StatiscticsActivity extends AppCompatActivity implements View.OnClickListener {

    Button dailyStat, monthlyStat, yearlyStat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statisctics);

        dailyStat = (Button) findViewById(R.id.btnDailyStat);
        dailyStat.setOnClickListener(this);

        monthlyStat = (Button) findViewById(R.id.btnMonthlyStat);
        monthlyStat.setOnClickListener(this);

        yearlyStat = (Button) findViewById(R.id.btnyearlyStat);
        yearlyStat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDailyStat:
                break;
            case R.id.btnMonthlyStat:
                break;
            case R.id.btnyearlyStat:
            default:
                break;
        }
    }
}