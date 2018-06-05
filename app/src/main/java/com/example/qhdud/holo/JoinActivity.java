package com.example.qhdud.holo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
    }

    public void onOkayClicked(View v)//확인->로그인화면
    {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("Loading","Loading");
        startActivity(intent);
        //startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
    public void onLateClicked(View v)//나중에하기->메인화면
    {
        startActivity(new Intent(getApplicationContext(),finishActivity.class));
        finish();
    }
}
