package com.endless.itsmylife.activity.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.endless.common.log.ZLog;
import com.endless.itsmylife.activity.R;
import com.endless.itsmylife.utils.EncrypAES;
import com.endless.itsmylife.utils.ToastUtil;
import org.w3c.dom.Text;

/**
 * Created by zhanglei on 14-8-18.
 */
public class SecretChatFragment extends BaseFragment implements View.OnClickListener {

    private EditText mEtKey, mEtContent;
    private static String TAG = "SecretChatFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_secret_chat, null);
        rootView.findViewById(R.id.button_decode).setOnClickListener(this);
        rootView.findViewById(R.id.button_encode).setOnClickListener(this);
        rootView.findViewById(R.id.button_share).setOnClickListener(this);
        rootView.findViewById(R.id.button_send_message).setOnClickListener(this);
        mEtContent = (EditText) rootView.findViewById(R.id.editText_content);
        mEtKey = (EditText) rootView.findViewById(R.id.editText_key);
        return rootView;
    }

    private String encodeMessage(String key, String content) {
        String result = "";
        try {
            if (TextUtils.isEmpty(key) && TextUtils.isEmpty(content)) {
                ToastUtil.showMessage(getActivity(), R.string.key_or_content_is_null);
            } else {
                byte[] b64Key = Base64.encode(key.getBytes(), 1);
                byte[] b64Content = Base64.encode(content.getBytes(), 1);
                result = EncrypAES.encrypt(new String(b64Key, "utf-8"), new String(b64Content, "utf-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtil.showMessage(getActivity(), R.string.encode_failed);
        }
        return result;
    }

    private String decodeMessage(String key, String content) {
        String result = "";
        try {
            if (TextUtils.isEmpty(key) && TextUtils.isEmpty(content)) {
                ToastUtil.showMessage(getActivity(), R.string.key_or_content_is_null);
            } else {
                byte[] b64Key = Base64.encode(key.getBytes(), 1);
                String b64Content = EncrypAES.decrypt(new String(b64Key, "utf-8"), content);
                byte[] deContent = Base64.decode(b64Content, 1);
                result = new String(deContent, "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtil.showMessage(getActivity(), R.string.decode_failed);
        }
        return result;
    }

    @Override
    public void onClick(View v) {
        if (!checkInputs()) {
            return;
        }
        String result = "";
        switch (v.getId()) {
            case R.id.button_decode:
                ZLog.d(TAG, "decode button on clicked!");
                result = decodeMessage(mEtKey.getText().toString().trim(), mEtContent.getText().toString().trim());
                if (!TextUtils.isEmpty(result)) {
                    mEtContent.setText(result);
                }
                break;
            case R.id.button_encode:
                ZLog.d(TAG, "encode button on clicked!");
                result = encodeMessage(mEtKey.getText().toString().trim(), mEtContent.getText().toString().trim());
                if (!TextUtils.isEmpty(result)) {
                    mEtContent.setText(result);
                }
                break;
            case R.id.button_share:
                ZLog.d(TAG, "share button on clicked!");
                shareContent(mEtContent.getText().toString());
                break;
            case R.id.button_send_message:
                ZLog.d(TAG, "send message button on clicked!");
                sendAsShortMessage("15810700086", mEtContent.getText().toString().trim());
                break;
            default:
                break;
        }
    }

    private boolean checkInputs() {
        if (TextUtils.isEmpty(mEtKey.getText())) {
            ToastUtil.showMessage(getActivity(), getString(R.string.key_is_empty));
            return false;
        } else if (TextUtils.isEmpty(mEtContent.getText())) {
            ToastUtil.showMessage(getActivity(), getString(R.string.content_is_empty));
            return false;
        }
        return true;
    }

    private void sendAsShortMessage(String targetPhone, String content) {
        ZLog.d(TAG, "sendAsShortMessage() invoked!");
        if (TextUtils.isEmpty(targetPhone)) {
            ToastUtil.showMessage(getActivity(), R.string.target_phone_num_is_null);
        } else {
            if (TextUtils.isEmpty(content)) {
                ToastUtil.showMessage(getActivity(), R.string.message_content_is_null);
            } else {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(targetPhone, null, content, null, null);
                ZLog.d(TAG, "message sent!");
            }
        }
    }

    private void shareContent(String content) {
        ZLog.d(TAG, "shareContent() invoked!");
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
//      intent.putExtra(Intent.EXTRA_SUBJECT, getText(R.string.app_name));
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, getString(R.string.share)));
    }
}
