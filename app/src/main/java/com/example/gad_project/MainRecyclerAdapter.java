package com.example.gad_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

    private ArrayList<Person>  personArrayList;
    private Context mContext;
    private String listType;

    public MainRecyclerAdapter(ArrayList<Person> list, String listType){
        this.personArrayList = list;
        this.listType = listType;
    }


    @NonNull
    @Override
    public MainRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_learner_item, parent, false);

        mContext = parent.getContext();

        return new MainRecyclerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerAdapter.ViewHolder holder, int position) {
        Person currPerson = personArrayList.get(position);
        holder.bind(currPerson);

    }

    @Override
    public int getItemCount() {
        return personArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView badgeImg;
        private TextView nameTextView, detailsTextView;
        private static final String skillText = "skill IQ score,";
        private static final String hoursText = "learning hours,";

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            badgeImg = itemView.findViewById(R.id.badgeImg);
            nameTextView = itemView.findViewById(R.id.name_textView);
            detailsTextView = itemView.findViewById(R.id.details_textView);

        }

        public void bind(Person currPerson) {
            Glide.with(mContext).load(currPerson.getBadgeUrl()).placeholder(R.drawable.ic_baseline_image_24).into(badgeImg);

            String displayText = listType.equals("skill") ? skillText : hoursText;
            int number = listType.equals("skill") ? currPerson.getScore() : currPerson.getHours();

            nameTextView.setText(currPerson.getName());
            detailsTextView.setText(new StringBuilder(number+displayText+currPerson.getCountry()));

        }
    }
}
