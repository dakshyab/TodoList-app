package com.example.todolist;


import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class NoteRead extends AppCompatActivity {
    private TextToSpeech mTTs;
    private TextView head;
    private TextView desc;
    private Button read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_item);


        read = findViewById(R.id.button_read);
        mTTs = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTs.setLanguage(Locale.US);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language nor supported");
                    } else {
                        read.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed ");
                }
            }
        });

        head = findViewById(R.id.text_view_title);
        desc = findViewById(R.id.text_view_description);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
    }


    private void speak(){
        //String text = head.getText().toString()+"  "+desc.getText().toString();
        String text = desc.getText().toString();
        //mTTs.speak(text,TextToSpeech.QUEUE_FLUSH,null);
        mTTs.speak(text, TextToSpeech.QUEUE_FLUSH,null,null);
    }

    @Override
    protected void onDestroy() {
        if (mTTs != null){
            mTTs.stop();
            mTTs.shutdown();
        }
        super.onDestroy();
    }
}
