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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnToDBActivity:
                Intent intent = new Intent(this, DataBaseActivity.class);
                startActivity(intent);
                break;
            case R.id.btnToAteActivity:
                Intent intent2 = new Intent(this, AteActivity.class);
                startActivity(intent2);
                default:
                    break;
        }
    }
}
