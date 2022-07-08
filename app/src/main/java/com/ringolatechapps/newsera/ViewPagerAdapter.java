package com.ringolatechapps.newsera;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

    ArrayList<Arcticle> arcticles_list;
    Context context;

    public ViewPagerAdapter(ArrayList<Arcticle> arcticles_list,Context context) {
        this.arcticles_list = arcticles_list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_arcticle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(arcticles_list.get(position).getImg_url()).into(holder.img);
        holder.heading.setText(arcticles_list.get(position).getHeading());
        holder.description.setText(arcticles_list.get(position).getDescription());
        holder.content.setText(arcticles_list.get(position).getContent());
        holder.link = arcticles_list.get(position).getUrl();

        holder.readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,MainActivity2.class);
                intent.putExtra("link",holder.link);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return arcticles_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView heading,description,content,readmore;
        ImageView img;
        String link;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            heading = itemView.findViewById(R.id.heading_id);
            description = itemView.findViewById(R.id.description_id);
            content = itemView.findViewById(R.id.content_id);
            img = itemView.findViewById(R.id.img_id);
            readmore = itemView.findViewById(R.id.link_id);
        }


    }

}
