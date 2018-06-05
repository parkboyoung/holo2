package com.example.qhdud.holo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class finishActivity extends AppCompatActivity {

    Button btn1, btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        if (findViewById(R.id.container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            Fragment_my MyPage = new Fragment_my();
            MyPage.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(R.id.container, MyPage).commit();
        }
    }
    public void select(View v)
    {
        Fragment fragment = null;
        switch(v.getId())
        {
            case R.id.btn1 : fragment = new Fragment_my(); break;
            case R.id.btn2 : fragment = new Fragment_add(); break;
            case R.id.btn3 : fragment = new Fragment_holo(); break;
        }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}



