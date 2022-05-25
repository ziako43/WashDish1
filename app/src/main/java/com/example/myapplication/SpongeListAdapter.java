package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpongeListAdapter extends ArrayAdapter<Sponge> {

    class ViewHolder {
        TextView name;//название
        TextView price;//цена
        Button btn;//нажата кнопка

    }

    public interface onItemClickListner {
        public void Clicuha(int money,int pos);
    }

    onItemClickListner onItemClickListner;

    public void setOnItemClickListner(onItemClickListner listner) {
        this.onItemClickListner = listner;
    }

    Context context;

    public SpongeListAdapter(@NonNull Context context, @NonNull Sponge[] objects) {
        super(context, R.layout.sponge, objects);
        this.context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        final Sponge sponges = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sponge, null);
            viewHolder = new ViewHolder();
            viewHolder.name = convertView.findViewById(R.id.nameofsponge);
            viewHolder.price = convertView.findViewById(R.id.priceofsponge);
            viewHolder.btn = convertView.findViewById(R.id.btn_buy);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(getItem(position).name);
        viewHolder.price.setText(String.valueOf(getItem(position).price));

        switch (sponges.price) {
            case 100:
                ((ImageView) convertView.findViewById(R.id.this_sponge)).setImageResource(R.drawable.spongev2);
                break;
            case 200:
                ((ImageView) convertView.findViewById(R.id.this_sponge)).setImageResource(R.drawable.spongev3);
                break;
            case 400:
                ((ImageView) convertView.findViewById(R.id.this_sponge)).setImageResource(R.drawable.spongev4);
                break;
            case 1000:
                ((ImageView) convertView.findViewById(R.id.this_sponge)).setImageResource(R.drawable.spongev5);
                break;
        }

        viewHolder.btn.setOnClickListener(v -> {
            if (Game.score >= sponges.price) {
                Game.score -= sponges.price;
                if (onItemClickListner != null) {
                    onItemClickListner.Clicuha(Game.score, position);
                }
                Toast.makeText(context, "Успешная покупка ", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(context, "Ты слишком беден ", Toast.LENGTH_SHORT).show();

        });
        return convertView;
    }
}
