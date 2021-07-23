package com.unikom.ganan;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StoreAPI extends AsyncTask<String, String, String> {
    private String KEY = "8c48c14b5dmsh18f9df73b7bd045p151b67jsn00d403ecbbc3";
    private String HOST = "cheapshark-game-deals.p.rapidapi.com";

    DiscountActivity m;

    public StoreAPI(DiscountActivity ma) {
        this.m = ma;
    }

    @Override
    protected String doInBackground(String... urls) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://cheapshark-game-deals.p.rapidapi.com/stores")
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
        JSONArray arrStores = null;
        try {
            arrStores = new JSONArray(s);
            m.StoreAdapter(arrStores);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
