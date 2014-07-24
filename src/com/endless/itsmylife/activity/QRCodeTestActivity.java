package com.endless.itsmylife.activity;

import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.util.Log;
import android.view.View;

public class QRCodeTestActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = QRCodeTestActivity.class.getSimpleName();
    private static final int QR_WIDTH = 400;
    private static final int QR_HEIGHT = 400;
    private ImageView mImgQRCode;
    private EditText mEtUrl;
    private Button mBtnGenerate;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        
        mImgQRCode = (ImageView) findViewById(R.id.imageView_qr_code);
        mEtUrl = (EditText) findViewById(R.id.editText_qr_code_content);
        mBtnGenerate = (Button) findViewById(R.id.button_generate);
        mBtnGenerate.setOnClickListener(this);
        
    }
    
    private boolean generateQRCodeImg(String url) {
        if (url == null || url.equals("")) {
            return false;
        }
        Log.d(TAG, "generateQRCodeImg--Text：" + url);
        
        try {
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix martix = writer.encode(url, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT);
            Log.d(TAG, "w:" + martix.getWidth() + "\nh:" + martix.getHeight());
            
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
            bitMatrix.setRegion(10, 20, 100, 100);
            int[] pixels = new int[QR_HEIGHT * QR_WIDTH];
            for (int i = 0; i < QR_HEIGHT; i++) {
                for (int j = 0; j < QR_WIDTH; j++) {
                    if (bitMatrix.get(j, i)) {
                        pixels[i * QR_WIDTH + j] = 0xff000000;
                    } else {
                        pixels[i * QR_WIDTH + j] = 0xffffffff;
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
            mImgQRCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.button_generate:
            Log.d(TAG, "button generate clicked!");
            generateQRCodeImg(mEtUrl.getText().toString());
            break;

        default:
            break;
        }
    }
}
