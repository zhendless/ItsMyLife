package com.endless.itsmylife.receiver;

import java.util.Iterator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.gsm.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {
    public static final String TAG = "ImiChatSMSReceiver";
    public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVED_ACTION)) {
            SmsMessage[] messages = getMessageFromIntent(intent);
            for (SmsMessage message : messages) {
                Log.i(TAG, message.getOriginatingAddress() + " : " + message.getDisplayOriginatingAddress() + " : " + message.getDisplayMessageBody() + " : " + message.getTimestampMillis());
            }
        }
    }

    public final SmsMessage[] getMessageFromIntent(Intent intent) {
        Object[] messages = (Object[]) intent.getSerializableExtra("pdus");
        
        byte[][] pdusObjs = new byte[messages.length][];
        for (int i = 0; i < messages.length; i++) {
            pdusObjs[i] = (byte[]) messages[i];
        }
        
        byte[][] pdus = new byte[pdusObjs.length][];
        int pdusCount = pdus.length;
        SmsMessage[] msgs = new SmsMessage[pdusCount];
        for (int i = 0; i < pdusCount; i++) {
            pdus[i] = pdusObjs[i];
            msgs[i] = SmsMessage.createFromPdu(pdus[i]);
        }
        return msgs;
    }
}
