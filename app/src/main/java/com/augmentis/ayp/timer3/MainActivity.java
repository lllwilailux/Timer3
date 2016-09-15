package com.augmentis.ayp.timer3;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TimerHandlerThread.CallBack {

    EditText editText;
    Button button;
    TextView textView;
    Handler handler;
    TimerHandlerThread timerHandlerThread;
    String str;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        timerHandlerThread = new TimerHandlerThread("Thread",this,handler);
        timerHandlerThread.start();
        timerHandlerThread.onLooperPrepared();

        editText = (EditText) findViewById(R.id.edt_text);
        textView = (TextView) findViewById(R.id.txt_view);
        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                str = editText.getText().toString();
                num = Integer.parseInt(str);

                timerHandlerThread.addMessage(num);

            }
        });
    }

    @Override
    public void sentback(String s) {
        textView.setText(s);
    }
}
