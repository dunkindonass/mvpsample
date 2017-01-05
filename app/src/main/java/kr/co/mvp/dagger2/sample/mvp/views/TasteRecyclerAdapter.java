package kr.co.mvp.dagger2.sample.mvp.views;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kr.co.mvp.dagger2.sample.R;
import kr.co.mvp.dagger2.sample.mvp.model.Place;

/**
 * Created by 8454 on 2016-08-12.
 */

public class TasteRecyclerAdapter extends RecyclerView.Adapter<TasteRecyclerAdapter.TasteViewHolder> {


    private Context context;
    private ArrayList<Place> searchItems;

    private ItemClick itemClickCallback;


    public TasteRecyclerAdapter(Context context, ArrayList<Place> searchItems) {
        this.searchItems = searchItems;
        this.context = context;
    }

    @Override
    public TasteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasterecylerrow, parent, false);
        final TasteViewHolder viewHolder = new TasteViewHolder(itemView);
        return viewHolder;
    }

    public void setItemClickCallback(ItemClick itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    @Override
    public void onBindViewHolder(TasteViewHolder holder, int position) {
        holder.nameTextView.setText(searchItems.get(position).getName());
        holder.descriptionTextView.setText(searchItems.get(position).getAddress() + " " + searchItems.get(position).getStreetNameAddress());
        holder.cateTextView.setText(searchItems.get(position).getCategory().get(0));
        if (searchItems.get(position).getCategory().size() > 1) {
            holder.cateTextView.append("/" + searchItems.get(position).getCategory().get(1));
        }
        holder.telTextview.setText(searchItems.get(position).getPrimaryPhone());
        Glide.with(context).load(Uri.parse(searchItems.get(position).getImageLink())).placeholder(R.drawable.noimage).into(holder.thumbview);
        holder.containLayout.setOnClickListener((view) -> itemClickCallback.callWebView(searchItems.get(position).getWebLink()));
    }

    @Override
    public int getItemCount() {
        return searchItems.size();
    }

    public static class TasteViewHolder extends RecyclerView.ViewHolder {
        public TextView cateTextView;
        public TextView descriptionTextView;
        public ImageView thumbview;
        public TextView telTextview;
        public TextView nameTextView;
        public LinearLayout containLayout;

        public TasteViewHolder(View itemView) {
            super(itemView);

            containLayout = (LinearLayout) itemView.findViewById(R.id.taste_containlayout);
            cateTextView = (TextView) itemView.findViewById(R.id.cate_text);
            descriptionTextView = (TextView) itemView.findViewById(R.id.sub_text);
            thumbview = (ImageView) itemView.findViewById(R.id.taste_thumb);
            telTextview = (TextView) itemView.findViewById(R.id.tel_text);
            nameTextView = (TextView) itemView.findViewById(R.id.name_text);
        }
    }


    public interface ItemClick {
        void callWebView(String url);
    }


}
