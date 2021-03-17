package com.example.scanball;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.scanball.utils.ConnectionDec;
//окно ошибки
public class MainActivity extends AppCompatActivity{
    ConnectionDec cd;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        cd = new ConnectionDec(this);
        if (cd.isConnected()) {
           // setContentView(R.layout.activity_main2);
            openActivity2();
        } else {
            setContentView(R.layout.activity_main);
            button = (Button) findViewById(R.id.button2);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cd.isConnected()) {
                        openActivity2();
                    }
                }
            });
        }

    }
    //открытие главной страницы
    public void openActivity2(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}