package com.unikom.ganan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    protected ImageLoader imageLoader;
    ArrayList<GameSales> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));

        listView = findViewById(R.id.listView);
        SharkAPI api = new SharkAPI(this);
        api.execute();



    }

    public void setItemAdapter(ArrayList<GameSales> arr) {
        GameSalesAdapter g = new GameSalesAdapter(this, R.layout.list_row, arr);
        listView.setAdapter(g);
        arrayList = arr;
        Intent intent = new Intent(getBaseContext(), DiscountActivity.class);

        // Open activity from listview item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When clicked, show a toast with the TextView text
                intent.putExtra("STORE_ID", arr.get(position).getStoreID());
                intent.putExtra("GAME_TITLE", arr.get(position).getTitle());
                intent.putExtra("GAME_IMAGE", arr.get(position).getImage());
                intent.putExtra("GAME_PRICEORI", arr.get(position).getPriceOri());
                intent.putExtra("GAME_PRICESALES", arr.get(position).getPriceOri());
                intent.putExtra("GAME_DISCOUNT", arr.get(position).getDiscount());
                startActivity(intent);
            }
        });
    }
}