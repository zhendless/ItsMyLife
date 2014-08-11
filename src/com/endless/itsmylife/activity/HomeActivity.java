package com.endless.itsmylife.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.endless.itsmylife.activity.base.BaseActivity;
import com.endless.itsmylife.utils.EncrypAES;

/**
 * Created by zhanglei on 14-8-10.
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEtKey, mEtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViewById(R.id.button_decode).setOnClickListener(this);
        findViewById(R.id.button_encode).setOnClickListener(this);
        findViewById(R.id.button_share).setOnClickListener(this);
        mEtContent = (EditText) findViewById(R.id.editText_content);
        mEtKey = (EditText) findViewById(R.id.editText_key);
    }

    @Override
    public void onClick(View v) {
        if (!checkInputs()) {
            return;
        }
        switch (v.getId()) {
            case R.id.button_decode:
                try {
                    byte[] b64Key = Base64.encode(mEtKey.getText().toString().trim().getBytes(), 1);
                    String b64Content = EncrypAES.decrypt(new String(b64Key, "utf-8"), mEtContent.getText().toString().trim());
                    byte[] deContent = Base64.decode(b64Content, 1);
                    mEtContent.setText(new String(deContent, "utf-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button_encode:
                try {
                    byte[] b64Key = Base64.encode(mEtKey.getText().toString().trim().getBytes(), 1);
                    byte[] b64Content = Base64.encode(mEtContent.getText().toString().trim().getBytes(), 1);
                    String result = EncrypAES.encrypt(new String(b64Key, "utf-8"), new String(b64Content, "utf-8"));
                    mEtContent.setText(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button_share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
//                intent.putExtra(Intent.EXTRA_SUBJECT, getText(R.string.app_name));
                intent.putExtra(Intent.EXTRA_TEXT, mEtContent.getText().toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, getString(R.string.share)));
                break;
            default:
                break;
        }
    }

    private boolean checkInputs() {
        if (TextUtils.isEmpty(mEtKey.getText())) {
            Toast.makeText(this, getString(R.string.key_is_empty), Toast.LENGTH_LONG).show();
            return false;
        } else if (TextUtils.isEmpty(mEtContent.getText())) {
            Toast.makeText(this, getString(R.string.content_is_empty), Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
