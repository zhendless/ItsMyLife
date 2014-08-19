package com.endless.itsmylife.utils;

import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import android.graphics.Bitmap;

/**
 * 二维码的工具类，依赖zxing-core-3.1.0.jar，对其进行封装。
 */
public class QRCodeUtils {

    public static final String TAG = QRCodeUtils.class.getSimpleName();

    /**
     * 生成二维码图片
     *
     * @param url      content that will be generated in QR code
     * @param qrWidth, qrHeight width and height for QR code image.
     * @return Bitmap for success, null for Exception.
     * @
     */
    public static Bitmap generateQRCode(String url, int qrWidth, int qrHeight) {

        try {
            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.MARGIN, 0);
            BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, qrWidth, qrHeight, hints);
            int[] pixels = new int[qrHeight * qrWidth];
            for (int i = 0; i < qrHeight; i++) {
                for (int j = 0; j < qrWidth; j++) {
                    if (bitMatrix.get(j, i)) {
                        pixels[i * qrWidth + j] = 0xff000000;
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(qrWidth, qrHeight, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, qrWidth, 0, 0, qrWidth, qrHeight);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }
}
