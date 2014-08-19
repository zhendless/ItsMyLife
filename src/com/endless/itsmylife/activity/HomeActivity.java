package com.endless.itsmylife.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.endless.itsmylife.activity.base.BaseActivity;
import com.endless.itsmylife.activity.fragments.SecretChatFragment;
import com.endless.itsmylife.utils.EncrypAES;

/**
 * Created by zhanglei on 14-8-10.
 */
public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SecretChatFragment secretChatFragment = new SecretChatFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_content, secretChatFragment);
        transaction.commit();
    }


}
