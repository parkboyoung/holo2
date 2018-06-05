package com.example.qhdud.holo;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by qhdud on 2018-06-02.
 */
public class Fragment_add extends Fragment {

    EditText editText;
    Button saveBtn,voiceBtn,pictureBtn;
    //파일이름을 저장할 변수
    String fileName;
    //음성인식
    Intent i;
    SpeechRecognizer mRecognizer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_add,container,false);
        Context context = inflater.getContext();

        editText = (EditText) view.findViewById(R.id.editText);

        saveBtn = (Button) view.findViewById(R.id.saveBtn);
        voiceBtn=(Button)view.findViewById(R.id.voiceBtn);
        pictureBtn=(Button)view.findViewById(R.id.pictureBtn);

        i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,getContext().getPackageName());
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"ko-KR");

        //데이터베이스 클래스인 MyDBHelper 생성
        final MyDBHelper helper = new MyDBHelper(context);

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minute = Calendar.getInstance().get(Calendar.MINUTE);
        int second = Calendar.getInstance().get(Calendar.SECOND);
        SQLiteDatabase db = helper.getWritableDatabase();
        fileName = Integer.toString(year)+"년 "+Integer.toString(month+1)+"월 "+Integer.toString(day) + "일 "+Integer.toString(hour)+"시 "+Integer.toString(minute)+"분 "+Integer.toString(second)+"초";

        //음성버튼
        voiceBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mRecognizer=SpeechRecognizer.createSpeechRecognizer(getActivity());
                mRecognizer.setRecognitionListener(listener);
                mRecognizer.startListening(i);

            }
        });
        //저장버튼
        saveBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SQLiteDatabase db= helper.getWritableDatabase();
                String sql = "insert into holo values('" + fileName + "','"+editText.getText().toString()+"');";
                System.out.println(sql);
                db.execSQL(sql);
                db.close();
                (Toast.makeText(getActivity(), "저장되었습니다.", Toast.LENGTH_SHORT)).show();
            }
        });

        return view;

    }
    private RecognitionListener listener = new RecognitionListener()
    {
        @Override public void onRmsChanged(float rmsdB)
        {
            // TODO Auto-generated method stub
            //
        }
        @Override
        public void onResults(Bundle results) {
            // TODO Auto-generated method stub
            String key = "";
            key = SpeechRecognizer.RESULTS_RECOGNITION;
            ArrayList<String> mResult = results.getStringArrayList(key);
            String[] rs = new String[mResult.size()];
            mResult.toArray(rs);
            editText.setText("" + rs[0]);

        }
        @Override
        public void onReadyForSpeech(Bundle params)
        {
            // TODO Auto-generated method stub
        }
        @Override public void onPartialResults(Bundle partialResults)
        {
            // TODO Auto-generated method stub
        }
        @Override public void onEvent(int eventType, Bundle params)
        {
            // TODO Auto-generated method stub
        }
        @Override public void onError(int error)
        {
            // TODO Auto-generated method stub
        }
        @Override public void onEndOfSpeech()
        {
            // TODO Auto-generated method stub
        }
        @Override public void onBufferReceived(byte[] buffer)
        {
            // TODO Auto-generated method stub
        }
        @Override public void onBeginningOfSpeech()
        {
            // TODO Auto-generated method stub
        }
    };


}
