package com.example.currencylist;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



public class CurrencyAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<Currency> objects;

    CurrencyAdapter(Context context) {
        ctx = context;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    CurrencyAdapter(Context context, List<Currency> currencyList) {
        ctx = context;
        objects = currencyList;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        Currency c = getCurrency(position);

        ((TextView) view.findViewById(android.R.id.text1)).setText(c.getName());
        ((TextView) view.findViewById(android.R.id.text2)).setText(c.getCharCode() + " | " + c.getValue());

        return view;
    }

    Currency getCurrency(int position) {
        return ((Currency) getItem(position));
    }

}
