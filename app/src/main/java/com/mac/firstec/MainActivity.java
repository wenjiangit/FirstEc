package com.mac.firstec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.mac.latte.core.app.Latte;

import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(Latte.getApplication(), "注入ApplicationContext",
                0).show();


    }
}
