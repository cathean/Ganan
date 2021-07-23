package com.unikom.ganan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class GameSalesAdapter extends ArrayAdapter<GameSales> {
    private Context mContext;
    private int mResource;
    ImageLoader imageLoader;

    public GameSalesAdapter(@NonNull Context context, int resource, @NonNull ArrayList<GameSales> objects) {
        super(context, resource, objects);

        this.mContext = context;
        this.mResource = resource;
        imageLoader = ImageLoader.getInstance();
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
        TextView txtDiscount = convertView.findViewById(R.id.txtOff);

        imageLoader.loadImage(getItem(position).getImage(), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(loadedImage, 140, 72, false);
                coverView.setImageBitmap(resizedBitmap);
            }
        });

        txtTitle.setText(getItem(position).getTitle());
        txtPriceSales.setText("$" + getItem(position).getPriceSales());
        txtPriceOri.setText("$" + getItem(position).getPriceOri());
        txtDiscount.setText(getItem(position).getDiscount() + "% OFF");
        return convertView;
    }
}
