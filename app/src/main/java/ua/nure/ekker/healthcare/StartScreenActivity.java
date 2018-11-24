package ua.nure.ekker.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StartScreenActivity extends AppCompatActivity implements View.OnClickListener {

    Button toDataBaseActivity, toAteActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        toDataBaseActivity = (Button) findViewById(R.id.btnToDBActivity);
        toDataBaseActivity.setOnClickListener(this);

        toAteActivity = (Button) findViewById(R.id.btnToAteActivity);
        toAteActivity.setOnClickListener(this);

        toAteActivity = (Button) findViewById(R.id.btnToStatActivity);
        toAteActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnToDBActivity:
                Intent dbActivity = new Intent(this, DataBaseActivity.class);
                startActivity(dbActivity);
                break;
            case R.id.btnToAteActivity:
                Intent ateActivity = new Intent(this, AteActivity.class);
                startActivity(ateActivity);
            case R.id.btnToStatActivity:
                Intent statActivity = new Intent(this, StatiscticsActivity.class);
                startActivity(statActivity);
            default:
                    break;
        }
    }
}
