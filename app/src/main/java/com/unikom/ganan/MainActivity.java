package com.unikom.ganan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        // Create data
        ArrayList<GameSales> arrayList = new ArrayList<>();

        arrayList.add(new GameSales(R.drawable.cover_example, "Skyrim", 100, 200));
        arrayList.add(new GameSales(R.drawable.cover_example, "Skyrim", 100, 200));
        arrayList.add(new GameSales(R.drawable.cover_example, "Skyrim", 100, 200));
        arrayList.add(new GameSales(R.drawable.cover_example, "Skyrim", 100, 200));
        arrayList.add(new GameSales(R.drawable.cover_example, "Skyrim", 100, 200));
        arrayList.add(new GameSales(R.drawable.cover_example, "Skyrim", 100, 200));
        arrayList.add(new GameSales(R.drawable.cover_example, "Skyrim", 100, 200));
        arrayList.add(new GameSales(R.drawable.cover_example, "Skyrim", 100, 200));
        arrayList.add(new GameSales(R.drawable.cover_example, "Skyrim", 100, 200));

        // Custom adapter
        GameSalesAdapter gameSalesAdapter = new GameSalesAdapter(this, R.layout.list_row, arrayList);

        listView.setAdapter(gameSalesAdapter);
    }
}