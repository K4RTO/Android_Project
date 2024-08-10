package com.example.ga_23s2.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import java.io.ByteArrayOutputStream;

/**
 * @author Yifan Xiao
 */
public abstract class ProcessImage {
  public static String encodeImage(Bitmap bitmap) {
    int previewWidth = 150;
    int previewHeight = bitmap.getHeight() * previewWidth / bitmap.getWidth();
    Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
    byte[] bytes = byteArrayOutputStream.toByteArray();
    return android.util.Base64.encodeToString(bytes, android.util.Base64.DEFAULT);
  }

  public static Bitmap decodeImage(String encodedImg) {
    byte[] bytes = Base64.decode(encodedImg, Base64.DEFAULT);
    return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
  }
}
