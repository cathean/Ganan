package com.unikom.ganan;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.json.JSONArray;

public class DiscountActivity extends AppCompatActivity {
    String gameTitle, gameImage, storeID;
    float priceOri, priceSales;
    int discount;
    ImageLoader imageLoader;
    JSONArray arrStores = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);
        imageLoader = ImageLoader.getInstance();
        StoreAPI api = new StoreAPI(this);
        api.execute();

        storeID = getIntent().getStringExtra("STORE_ID");
        gameTitle = getIntent().getStringExtra("GAME_TITLE");
        gameImage = getIntent().getStringExtra("GAME_IMAGE");
        priceOri = getIntent().getFloatExtra("GAME_PRICEORI", 0);
        priceSales = getIntent().getFloatExtra("GAME_PRICESALES", 0);
        discount = getIntent().getIntExtra("GAME_DISCOUNT", 0);

        // Fungsi buat pasang gambar lewat URL
        imageLoader.loadImage(gameImage, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                //Set Resolusi
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(loadedImage, 140, 72, false);

                //Set ke gambar
                coverView.setImageBitmap(resizedBitmap);
            }
        });
    }

    public void StoreAdapter(JSONArray a) {
        arrStores = a;
    }
}
