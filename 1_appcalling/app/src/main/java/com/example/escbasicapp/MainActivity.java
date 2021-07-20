package com.example.escbasicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private ImageButton addContact;
    private ImageButton contact;
    private TextView phoneNum;
    private TextView[] dials = new TextView[10];
    private TextView star;
    private TextView sharp;
    private ImageButton message;
    private ImageButton call;
    private ImageButton backspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();
    }

    private void  setUpUI()  {
        addContact = findViewById(R.id.main_ibtn_add);
        contact = findViewById(R.id.main_ibtn_contact);
        phoneNum = findViewById(R.id.main_tv_phone);

        for(int i=0; i<dials.length; i++) {
            dials[i] = findViewById(getResourceID("main_tv_" + i,"id", this ));
        }

        star = findViewById(R.id.main_tv_star);
        sharp = findViewById(R.id.main_tv_sharp);
        message = findViewById(R.id.main_ibtn_message);
        call = findViewById(R.id.main_ibtn_call);
        backspace = findViewById(R.id.main_ibtn_back);

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 연락처 추가
                Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();

            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 연락처
            }
        });
        setOnClickDial(star,"*");
        setOnClickDial(sharp,"#");

        for(int i = 0; i < 10; i++){
            setOnClickDial(dials[i], String.valueOf(i));
        }

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:메시지
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:전화
            }
        });
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phoneNum.getText().length() > 0){
                    //String formatPhoneNum = PhoneNumberUtils.formatNumber(phoneNum.getText().subSequence(0,phoneNum.getText().length()-1).toString(), Locale.getDefault().getCountry());
                    //phoneNum.setText(formatPhoneNum);

                    String change = changeToDial(phoneNum.getText().subSequence(0,phoneNum.getText().length()-1).toString());
                    phoneNum.setText(change);
                }

            }
        });


    }

    private void setOnClickDial(View view, final String input){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String formatPhoneNum = PhoneNumberUtils.formatNumber(phoneNum.getText() + input, Locale.getDefault().getCountry());
//                phoneNum.setText(formatPhoneNum); //번호 입력

                String change = changeToDial(phoneNum.getText() + input);
                phoneNum.setText(change);
            }
        });
    }

    private int getResourceID(final String resName, final String resType, final Context ctx) {
        final int ResourceID = ctx.getResources().getIdentifier(resName, resType, ctx.getApplicationInfo().packageName);
        if(ResourceID == 0){
            //throw new IllegalAccessException("No resource string found with name " + resName);
        }
        else{
            return ResourceID;
        }
        return ResourceID;//
    }

    private String changeToDial(String phoneNum_){
        //전화번호 기준 01033218119
        //4글자 이상일 때 3번째 숫자 다음에
        //8글자이상이면 3번째 다음이랑 7번째 다음에 둘다 하이픈을
        //12글자 이상이면 전부 제거
        //특수문자 있으면 하이픈 전부 제거
        phoneNum_ = phoneNum_.replaceAll("-","");
        int length = phoneNum_.length();

        if(phoneNum_.charAt(length-1) == '*' || phoneNum_.charAt(length-1) == '#'){
            return phoneNum_;
        }
        else if(length>=(12)){
            return phoneNum_;
        }
        else if(length >= 8){
            phoneNum_ = phoneNum_.substring(0,3) + "-" + phoneNum_.substring(3,7) + "-" + phoneNum_.substring(7);
            return phoneNum_;
        }
        else if(length >= 4){
            phoneNum_ = phoneNum_.substring(0,3) + "-" + phoneNum_.substring((3));
            return phoneNum_;
        }

        return phoneNum_;
    }
}
