package com.example.revisedandroidlightsout.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revisedandroidlightsout.R;
import com.example.revisedandroidlightsout.models.RankingEntry;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RankingEntryAdapter extends RecyclerView.Adapter<RankingEntryAdapter.RankingEntryViewHolder> {

    ArrayList<RankingEntry> allEntries;

    public RankingEntryAdapter(ArrayList<RankingEntry> pEntries)
    {
        allEntries = pEntries;
    }
    @NonNull
    @Override
    public RankingEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.ranking_entry,parent,false);

        RankingEntryViewHolder viewHolder = new RankingEntryViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RankingEntryViewHolder holder, int position) {

        RankingEntry entry = allEntries.get(position);
        holder.bind(entry);

    }

    @Override
    public int getItemCount() {
        return allEntries.size();
    }

    public class RankingEntryViewHolder extends RecyclerView.ViewHolder {

        private TextView place;
        private TextView playerName;
        private TextView time;
        private View view;


        public RankingEntryViewHolder(@NonNull View itemView) {
            super(itemView);

            this.view = itemView;
            place = view.findViewById(R.id.place);
            playerName = view.findViewById(R.id.playerName);
            time = view.findViewById(R.id.time);
        }

        public void bind(RankingEntry entry)
        {
            int position = allEntries.indexOf(entry)+1;
            String placee = Integer.toString(position);
            switch(position)
            {
                case 1:
                {
                    placee += "st.";
                    break;
                }
                case 2:
                {
                    placee += "nd.";
                    break;
                }
                case 3:
                {
                    placee += "rd.";
                    break;
                }
                default:
                {
                    placee += "th.";
                    break;
                }
            }
            place.setText(placee);
            playerName.setText(entry.playerName);
            time.setText(Long.toString(entry.time) + "s");
        }
    }
}
