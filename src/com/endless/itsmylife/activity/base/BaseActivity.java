package com.endless.itsmylife.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.endless.common.log.ZLog;

public class BaseActivity extends Activity {

    private static final String TAG = "BaseActivity";

    /**
     * ***************************** 【Activity LifeCycle For Debug】 ******************************************
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ZLog.d(TAG, this.getClass().getSimpleName() + " onCreate() invoked!!");
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        MobclickAgent.onError(this);

    }

    @Override
    protected void onStart() {
        ZLog.d(TAG, this.getClass().getSimpleName() + " onStart() invoked!!");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        ZLog.d(TAG, this.getClass().getSimpleName() + " onRestart() invoked!!");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        ZLog.d(TAG, this.getClass().getSimpleName() + " onResume() invoked!!");
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        ZLog.d(TAG, this.getClass().getSimpleName() + " onPause() invoked!!");
        super.onPause();
        try {
//            MobclickAgent.onPause(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        ZLog.d(TAG, this.getClass().getSimpleName() + " onStop() invoked!!");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        ZLog.d(TAG, this.getClass().getSimpleName() + " onDestroy() invoked!!");
        super.onDestroy();

        /*if (mRunningTask != null && mRunningTask.isCancelled() == false) {
            mRunningTask.cancel(false);
            mRunningTask = null;
        }
        if (mAlertDialog != null) {
            mAlertDialog.dismiss();
            mAlertDialog = null;
        } */
    }

    /**
     * ***************************** 【Activity LifeCycle For Debug】 ******************************************
     */
    public void recommandToYourFriend(String url, String shareTitle) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, shareTitle + "   " + url);
        Intent itn = Intent.createChooser(intent, "分享");
        startActivity(itn);
    }
}
