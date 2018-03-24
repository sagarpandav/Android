package com.example.sagarpandav.navigationdrawer;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PANDAV on 24-03-2018.
 */

public class FragmentQRView extends Fragment {

    private Bitmap bitmap;

    @BindView(R.id.qr_view)
    ImageView qrView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        bitmap = encodeAsBitmap("Sagar");
        return inflater.inflate(R.layout.qrcodefragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);


        qrView.setImageBitmap(bitmap);
    }

    private Bitmap encodeAsBitmap(String str) {
        BitMatrix result;

        try {
            result = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, 300, 300, null);
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }

        int width = result.getWidth();
        Log.e("Width", String .valueOf(width));
        int height = result.getHeight();

        int pixels [] = new int[width*height];

        for (int y = 0; y < height; y++){
            int offset = y*height;
            for (int x = 0; x<width; x++){
                pixels [offset+x] = result.get(x, y) ? Color.BLACK : Color.WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
