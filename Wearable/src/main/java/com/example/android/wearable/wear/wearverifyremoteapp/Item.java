package com.example.android.wearable.wear.wearverifyremoteapp;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

//http://www.parcelabler.com/
public class Item implements Parcelable {

    public static final String ITEM_KEY = "item_key";

    private Integer id;
    private String title;
    private String body;
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
    //private List<String> options = new ArrayList<>();

    public Item(String name, String url, String image, Integer userId, Integer id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    protected Item(Parcel in) {
        userId = in.readByte() == 0x00 ? null : in.readInt();
        id = in.readByte() == 0x00 ? null : in.readInt();
        title = in.readString();
        body = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (userId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(userId);
        }
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        dest.writeString(title);
        dest.writeString(body);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public void pickOption(Context context){
        Toast.makeText(context, "Aguarde...", Toast.LENGTH_SHORT).show();
        /*AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Escolha uma opção para " + getName());
        String[] options = getOptions().toArray(new String[0]);
        builder.setItems(options, (dialog, which) -> {
            // the user clicked on colors[which]
            Toast.makeText(context, "Aguarde... Aperte Voltar para dar play", Toast.LENGTH_SHORT).show();
            //PlayerActivity.startPlayerActivity(context,getUrl(),options[which]);
        });
        builder.show();*/
    }

    /*@Override
    public String toString() {
        return "TimeData: " +getId().toString() + " " + getTitle();
    }*/
}
