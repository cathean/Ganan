package com.unikom.ganan;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.json.JSONArray;
import org.json.JSONException;

public class DiscountActivity extends AppCompatActivity {
    String gameTitle, gameImage, storeID;
    float priceOri, priceSales;
    int discount;
    ImageLoader imageLoader;
    JSONArray arrStores = new JSONArray();
    TextView harga_diskon;
    TextView harga_asli;
    TextView diskon;
    TextView judul;
    ImageView gambar;
    TextView toko;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);
        imageLoader = ImageLoader.getInstance();

        harga_diskon = (TextView) findViewById(R.id.harga_diskon);
        harga_asli = (TextView) findViewById(R.id.harga_asli);
        diskon = (TextView) findViewById(R.id.diskon);
        judul = (TextView) findViewById(R.id.judul);
        gambar = (ImageView) findViewById(R.id.gambar);
        toko = (TextView) findViewById(R.id.toko);


        StoreAPI api = new StoreAPI(this);
        api.execute();

        storeID = getIntent().getStringExtra("STORE_ID");
        gameTitle = getIntent().getStringExtra("GAME_TITLE");
        gameImage = getIntent().getStringExtra("GAME_IMAGE");
        priceOri = getIntent().getFloatExtra("GAME_PRICEORI", 0);
        priceSales = getIntent().getFloatExtra("GAME_PRICESALES", 0);
        discount = getIntent().getIntExtra("GAME_DISCOUNT", 0);


        harga_diskon.setText("$"+String.valueOf(priceSales));
        harga_asli.setText("$"+String.valueOf(priceOri));
        diskon.setText(String.valueOf(discount)+"%");
        judul.setText(gameTitle);


        // Fungsi buat pasang gambar lewat URL
        imageLoader.loadImage(gameImage, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                //Set Resolusi
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(loadedImage, 200, 90, false);


                //Set ke gambar
                gambar.setImageBitmap(resizedBitmap);
            }
        });
    }

    public void StoreAdapter(JSONArray a) {
        Log.d("cekcek:", String.valueOf(a.length()));
        arrStores = a;
        try {
            Log.d("error:", String.valueOf(a.length()));
            toko.setText(a.getJSONObject(Integer.parseInt(storeID)).getString("storeName"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
