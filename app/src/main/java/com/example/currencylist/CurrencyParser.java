package com.example.currencylist;

import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CurrencyParser {



    public static List<Currency> readCurrencyList(String URL) throws IOException, JSONException {
        List<Currency> currencyList = new ArrayList();
        Currency currency = null;
        JSONObject json = null;
        json = new JSONObject(IOUtils.toString(new URL(URL), Charset.forName("UTF-8")));

        JSONObject jsonMain = new JSONObject(json.toString());
        JSONObject charCode =  new JSONObject(json.toString());

        jsonMain = jsonMain.getJSONObject("Valute");
        charCode = charCode.getJSONObject("Valute");
        JSONObject temp = null;


        while (charCode.keys().hasNext()) {


          temp =  jsonMain.getJSONObject(charCode.keys().next());
          currency = new Currency();

          currency.setCharCode(temp.getString("CharCode"));
          currency.setName(temp.getString("Name"));
          currency.setValue(temp.getString("Value"));

          currencyList.add(currency);

          charCode.remove(charCode.keys().next());



        }
        return currencyList;
    }

}

