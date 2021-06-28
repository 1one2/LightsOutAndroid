package com.example.revisedandroidlightsout.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revisedandroidlightsout.R;
import com.example.revisedandroidlightsout.models.AboutEntry;

import java.util.ArrayList;

public class AboutEntryAdapter extends RecyclerView.Adapter<AboutEntryAdapter.AboutEntryViewHolder> {

    ArrayList<AboutEntry> allEntries;

    public AboutEntryAdapter(ArrayList<AboutEntry> pEntries)
    {
        allEntries = pEntries;
    }

    @NonNull
    @Override
    public AboutEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.about_cell,parent,false);
        AboutEntryViewHolder viewHolder = new AboutEntryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AboutEntryViewHolder holder, int position) {

        AboutEntry aboutEntry =allEntries.get(position);
        holder.bind(aboutEntry);
    }

    @Override
    public int getItemCount() {
        return allEntries.size();
    }

    public class AboutEntryViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private View view;
        public AboutEntryViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            textView = view.findViewById(R.id.aboutCell);
        }

        public void bind(AboutEntry aboutEntry)
        {
            textView.setText(aboutEntry.getText());
        }
    }
}
