package com.example.android.wearable.wear.wearverifyremoteapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.wear.widget.WearableRecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends WearableRecyclerView.Adapter{

    private List<Item> items;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ItemAdapter(List<Item> items, Context context){
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public WearableRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WearableRecyclerView.ViewHolder viewHolder, int position) {
        ItemViewHolder holder = (ItemViewHolder) viewHolder;
        Item channel = items.get(position) ;
        holder.setData(channel);

    }



    @Override
    public int getItemCount() {
        try {
            return items.size();
        } catch (Exception e){
            Log.d("JSOUP", "erro items.size");
            return 0;
        }
    }


    class ItemViewHolder extends WearableRecyclerView.ViewHolder {

        private final TextView name;
        //private final TextView url;
        private final ImageView image;
        private Item item;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id. item_name);
            //url = (TextView) itemView.findViewById(R.id.item_channel_url);
            image = (ImageView) itemView.findViewById(R.id.item_image);

            itemView.setOnClickListener((view) ->  {
                onItemClickListener.onItemClick(item);
                Toast.makeText(context, "ViewHolder clicado", Toast.LENGTH_SHORT).show();
            });
        }

        private void setData(Item item){
            name.setText(item.getTitle());
            //url.setText(channel.getUrl());
            try {

                Picasso.with( context )
                        //.load( item.getImage() )
                        .load("https://via.placeholder.com/50" )
                        .placeholder(R.mipmap.ic_launcher) // can also be a drawable
                        .error(R.mipmap.ic_launcher)
                        .into( image );
            }catch (Exception e){
                Log.d("JSOUP", "No picture");
            }
            this.item = item;
        }
    }
}
