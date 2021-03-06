package com.unikom.ganan;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    EditText searchtext;
    protected ImageLoader imageLoader;
    ArrayList<GameSales> arrayList = new ArrayList<>();
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.nav_view);
        Toolbar toolbar= findViewById(R.id.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.navigation_draw_open, R.string.navigation_draw_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));

        listView = findViewById(R.id.listView);
        searchtext = findViewById(R.id.editSearch);
        SharkAPI api = new SharkAPI(this);
        api.execute();

        searchtext.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    Toast.makeText(MainActivity.this, searchtext.getText(), Toast.LENGTH_SHORT).show();
                    System.out.println("KE ENTER: " + searchtext.getText());
                    SearchAPI sapi = new SearchAPI(MainActivity.this, searchtext.getText().toString());
                    sapi.execute();
                    return true;
                }
                return false;
            }
        });
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
                intent.putExtra("GAME_PRICESALES", arr.get(position).getPriceSales());
                intent.putExtra("GAME_DISCOUNT", arr.get(position).getDiscount());
                startActivity(intent);
            }
        });
    }
}