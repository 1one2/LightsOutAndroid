package com.example.lightsoutandroid.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lightsoutandroid.R;
import com.example.lightsoutandroid.interfaces.LightClickListener;
import com.example.lightsoutandroid.interfaces.OnItemClickListener;
import com.example.lightsoutandroid.models.Board;
import com.example.lightsoutandroid.models.Light;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.LightViewHolder> {

    private Board board;
    private LightClickListener clickListener;

    public MyAdapter(Board mBoard,LightClickListener mClickListener)
    {
        board = mBoard;
        clickListener = mClickListener;
    }

    @NonNull
    @Override
    public LightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.grid_cell,parent,false);
        LightViewHolder viewHolder = new LightViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LightViewHolder holder, int position)
    {
            Light light = board.getLights().get(position);
            holder.bind(light);
    }

    @Override
    public int getItemCount() {
        return board.getLights().size();
    }

    public class LightViewHolder extends RecyclerView.ViewHolder{

        private View view;
        private ImageView imageView;

        public LightViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            imageView = view.findViewById(R.id.lightImageView);
        }

        public void bind(Light light)
        {

            imageView.setImageResource(light.getImageSource());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onLightClicked(light);
                }
            });
        }
    }
}
