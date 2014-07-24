package com.endless.itsmylife.activity;

import com.endless.itsmylife.receiver.SMSReceiver;

import android.os.Bundle;
import android.widget.TextView;

public class ShortMessageActivity extends BaseActivity {
    
    private TextView mTvGetShortMessage;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_message);
        
        mTvGetShortMessage =  (TextView) findViewById(R.id.textView_getShortMessage);
        SMSReceiver receiver = new SMSReceiver();
        mTvGetShortMessage.setText(String.valueOf(Integer.MAX_VALUE));
        
    }

}
