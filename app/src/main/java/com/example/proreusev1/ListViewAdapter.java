package com.example.proreusev1;

import static com.google.api.ResourceProto.resource;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Info> {
    private int resource;

    public ListViewAdapter(@NonNull Context context, int resource, @NonNull List<Info> objects) {
        super(context, resource, objects);
        this.resource = resource;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        if( convertView == null )
            convertView = inflater.inflate(resource,parent,false);
        TextView userNameTV = convertView.findViewById(R.id.userNameS);
        TextView productNameTV = convertView.findViewById(R.id.product_nameS);
        TextView dateTV = convertView.findViewById(R.id.dateS);
        TextView priceTV = convertView.findViewById(R.id.priceS);
        TextView descriptionTV = convertView.findViewById(R.id.descriptionS);

        Info info = getItem(position);
        userNameTV.setText(info.getUserName());
        productNameTV.setText("Product Name : " + info.getProductName());
        dateTV.setText("Date : " + info.getDate());
        priceTV.setText("Price : "+info.getPrice());
        descriptionTV.setText("Description :\n"+info.getDescription());

        return convertView;

    }
}
