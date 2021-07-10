package com.example.android.wearable.wear.wearverifyremoteapp;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.wear.ambient.AmbientModeSupport;
import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends FragmentActivity implements AmbientModeSupport.AmbientCallbackProvider {

    private ArrayList<Item> items;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        if( savedInstanceState != null ){
            items = savedInstanceState.getParcelableArrayList(Item.ITEM_KEY);
            initViews();
        }
        else{
            items = new ArrayList<>();
            initViews();
            retrieveItems();
        }
    }

    private void initViews() {
        WearableRecyclerView wearableRecyclerView = (WearableRecyclerView)findViewById(R.id.recycler_launcher_view);
        adapter = new ItemAdapter(items, this);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {
                Toast.makeText(ListActivity.this, "Carregando", Toast.LENGTH_SHORT).show();
                item.pickOption(ListActivity.this);
            }
        });
        //WearableRecyclerView.LayoutManager layout = new GridLayoutManager(this, 3);
        wearableRecyclerView.setEdgeItemsCenteringEnabled(true);
        wearableRecyclerView.setLayoutManager(new WearableLinearLayoutManager(this));

        //wearableRecyclerView.setLayoutManager(layout);
        wearableRecyclerView.setAdapter(adapter);
    }

    private void retrieveItems(){
        new ItemRequest(this).execute();
    }


    public void updateList( List<Item> j ){
        items.clear();
        items.addAll( j );
        //adapter.notifyDataSetChanged();
        ((WearableRecyclerView) findViewById(R.id.recycler_launcher_view)).getAdapter().notifyDataSetChanged();
    }

    @Override
    public AmbientModeSupport.AmbientCallback getAmbientCallback() {
        return null;
    }
}