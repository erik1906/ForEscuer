package com.company.erde.forescuer.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.erde.forescuer.Model.Places;
import com.company.erde.forescuer.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



/**
 * Created by Erik on 20/09/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.PlacesViewHolder> {

    private ArrayList<Places> places;
    private RecyclerViewClickListener listener;

    public RecyclerViewAdapter(ArrayList<Places> places, RecyclerViewClickListener listener) {
        this.places = places;
        this.listener = listener;
    }

    public RecyclerViewAdapter(ArrayList<Places> places) {
        this.places = places;
    }

    @Override
    public RowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards, parent, false);
        //PlacesViewHolder placesViewHolder = new PlacesViewHolder(v);
        return new RowViewHolder(v,listener);
    }

    @Override
    public void onBindViewHolder(PlacesViewHolder holder, int position) {
        holder.tvPlaceName.setText(places.get(position).getName());

        Picasso.with(holder.itemView.getContext()).load("http://i.imgur.com/DvpvklR.png").into(holder.ivPhoto);

    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public static  class PlacesViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPhoto;
        private TextView tvPlaceName;

        public PlacesViewHolder(View itemView) {
            super(itemView);

            ivPhoto =(ImageView) itemView.findViewById(R.id.ivPlace);
            tvPlaceName = (TextView) itemView.findViewById(R.id.tvPlace);
        }
    }

    public interface  RecyclerViewClickListener{
        void onClick(View view, int position);
    }

    public class RowViewHolder extends RecyclerViewAdapter.PlacesViewHolder implements View.OnClickListener{

        private RecyclerViewClickListener listener;

        public RowViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            this.listener = listener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(itemView, getAdapterPosition());
        }


    }
}
