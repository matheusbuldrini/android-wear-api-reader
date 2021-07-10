package com.example.android.wearable.wear.wearverifyremoteapp;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ItemRequest extends AsyncTask<Void, Void, List<Item>> {
    private WeakReference<ListActivity> activity;

    public ItemRequest( ListActivity activity ){
        this.activity = new WeakReference<>( activity );
    }

    @Override
    protected List<Item> doInBackground(Void... voids) {

        //Document json = null;
        List<Item> items = new ArrayList<>();
        Gson gson = new Gson();
        Type itemListType = new TypeToken<ArrayList<Item>>(){}.getType();
        try {
            //json = Jsoup.connect("https://jsonplaceholder.typicode.com/users").get();

            //String webPage = "https://jsonplaceholder.typicode.com/users";
            String webPage = "https://jsonplaceholder.typicode.com/posts";

            String json = Jsoup.connect(webPage).ignoreContentType(true).execute().body();

            //Item item = gson.fromJson(json, Item.class);
            items = gson.fromJson(json, itemListType);
            //items.add(item);
            //System.out.println(item);
            Log.d("JSON",json);
            Log.d("JSON","items: " + items.size());

            } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return items;
    }

    @Override
    protected void onPostExecute(List<Item> items) {
        super.onPostExecute( items );
        if( activity.get() != null ){
            activity.get().updateList( items );
        }
    }
}
