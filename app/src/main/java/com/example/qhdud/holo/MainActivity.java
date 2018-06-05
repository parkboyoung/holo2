package com.example.qhdud.holo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //여기서 부터 로딩화면 holo가 들어가는 부분
        if(getIntent().getExtras()==null)
        {
            startActivity(new Intent(this, LoadActivity.class));
        }
        SharedPreferences preference = getSharedPreferences("a",MODE_PRIVATE); //처음 실행 여부를 결정
        int firstviewshow = preference.getInt("First",0);
        final EditText emailText = (EditText) findViewById(R.id.email);
        final EditText passwordText = (EditText) findViewById(R.id.password);

        if(firstviewshow != 1) {
            startActivity(new Intent(this, FirstStartActivity.class));
        }
        //로그인 부분
        Button LoginButton = (Button)findViewById(R.id.login);
        LoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();

                if((email.equals("abcd")) && (password.equals("1234")))
                {
                    startActivity(new Intent(getApplicationContext(),finishActivity.class));//메인화면
                    finish();//현재 엑티비티를 없애고 다른 화면을 나타내기 위함
                }
                else // 아이디와 패스워드가 틀립니다
                {
                    (Toast.makeText(getApplicationContext(),"이메일과 비밀번호가 틀립니다.",Toast.LENGTH_LONG)).show();
                }
            }
        });
    }

    //버튼 2 - 회원가입 버튼 눌렀을 경우
    public void onJoinClicked(View v)
    {
        startActivity(new Intent(getApplicationContext(),JoinActivity.class));
        finish();
    }
}
