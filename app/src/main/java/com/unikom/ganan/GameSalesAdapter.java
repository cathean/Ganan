package com.unikom.ganan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GameSalesAdapter extends ArrayAdapter<GameSales> {
    private Context mContext;
    private int mResource;

    public GameSalesAdapter(@NonNull Context context, int resource, @NonNull ArrayList<GameSales> objects) {
        super(context, resource, objects);

        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);
        ImageView coverView = convertView.findViewById(R.id.cover);
        TextView txtTitle = convertView.findViewById(R.id.txtTitle);
        TextView txtPriceSales = convertView.findViewById(R.id.txtPriceSales);
        TextView txtPriceOri = convertView.findViewById(R.id.txtPriceOri);

        coverView.setImageResource(getItem(position).getImage());
        txtTitle.setText(getItem(position).getTitle());
        txtPriceSales.setText("$" + getItem(position).getPriceSales());
        txtPriceOri.setText("$" + getItem(position).getPriceOri());

        return convertView;
    }
}
