package com.example.currencylist;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spin;
    private EditText ruble;
    private TextView convert;
    private CurrencyAdapter currencyAdapter;

    private static final String URL = "https://www.cbr-xml-daily.ru/daily_json.js";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spin = (Spinner) findViewById(R.id.spinner);
        ruble = (EditText) findViewById(R.id.inputRuble);
        convert = (TextView) findViewById(R.id.convertRuble);
        currencyAdapter = new CurrencyAdapter(this);

        new downloadJSON().execute(URL);




        ruble.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                try {

                    if (Double.parseDouble(ruble.getText().toString()) > 0 && ruble.getText().toString() != "") {
                        double result = Double.parseDouble(currencyAdapter.getCurrency(spin.getFirstVisiblePosition()).getValue()) *
                                Double.parseDouble(ruble.getText().toString());
                        convert.setText(String.valueOf(result));

                    }

                }catch (Exception e){
                    Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }



    private class downloadJSON extends AsyncTask<String, Void, List<com.example.currencylist.Currency>> {
        @Override
        protected List<com.example.currencylist.Currency> doInBackground(String... urls) {

            List<com.example.currencylist.Currency> currencyList = null;
            CurrencyParser cp = new CurrencyParser();

            try {
                currencyList = cp.readCurrencyList(URL);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return currencyList;
        }

        @Override
        protected void onPostExecute(List<com.example.currencylist.Currency> currencyList) {
            currencyAdapter = new CurrencyAdapter(MainActivity.this, currencyList);
            spin.setAdapter(currencyAdapter);
        }
    }
}

