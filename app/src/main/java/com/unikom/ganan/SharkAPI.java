package com.unikom.ganan;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SharkAPI extends AsyncTask<String, String, String> {
    private ArrayList<GameSales> listSales = new ArrayList<GameSales>();
    private String KEY = "8c48c14b5dmsh18f9df73b7bd045p151b67jsn00d403ecbbc3";
    private String HOST = "cheapshark-game-deals.p.rapidapi.com";
    MainActivity m;

    public SharkAPI(MainActivity ma) {
        this.m = ma;
    }

    @Override
    protected String doInBackground(String... urls) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://cheapshark-game-deals.p.rapidapi.com/deals?lowerPrice=0&steamRating=0&desc=0&output=json&steamworks=0&sortBy=Deal%20Rating&AAA=0&pageSize=60&exact=0&upperPrice=50&pageNumber=0&onSale=0&metacritic=0&storeID=1%2C2%2C3")
                .get()
                .addHeader("x-rapidapi-key", KEY)
                .addHeader("x-rapidapi-host", HOST)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(final String s) {
        JSONArray arrSales = null;
        try {
            arrSales = new JSONArray(s);

            for(int i = 0; i < arrSales.length(); i++) {
                if(arrSales.getJSONObject(i).getString("isOnSale").equals("1")) {
                    listSales.add(new GameSales(
                            arrSales.getJSONObject(i).getString("thumb"),
                            arrSales.getJSONObject(i).getString("title"),
                            Float.parseFloat(arrSales.getJSONObject(i).getString("salePrice")),
                            Float.parseFloat(arrSales.getJSONObject(i).getString("normalPrice")),
                            arrSales.getJSONObject(i).getString("gameID"))
                    );
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println("LENGTH: " + listSales.size());
        m.setItemAdapter(listSales);
    }
}
