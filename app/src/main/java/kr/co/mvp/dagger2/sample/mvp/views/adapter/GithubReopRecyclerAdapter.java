package kr.co.mvp.dagger2.sample.mvp.views.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.mvp.dagger2.sample.R;
import kr.co.mvp.dagger2.sample.mvp.model.Item;

/**
 * Created by 8454 on 2016-08-12.
 */

public class GithubReopRecyclerAdapter extends RecyclerView.Adapter<GithubReopRecyclerAdapter.GitReopViewHolder> {


    private Context context;
    private ArrayList<Item> gitRepo = new ArrayList<>();

    private OnItemSelectedCallBack itemSelectedCallBack;

    public interface OnItemSelectedCallBack {
        void onSelected(String url);
    }

    @Inject
    public GithubReopRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Item> datas) {
        Log.e("item size", datas.size() + "");
        if (gitRepo.size() > 0) {
            gitRepo.clear();
        }

        gitRepo.addAll(datas);

        Log.e("item size", gitRepo.size() + "");
    }

    public void setListener(OnItemSelectedCallBack callBack) {
        itemSelectedCallBack = callBack;
    }

    @Override
    public GitReopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_repo, parent, false);
        final GitReopViewHolder viewHolder = new GitReopViewHolder(itemView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(GitReopViewHolder holder, int position) {


        holder.starNumText.setText(gitRepo.get(position).getStargazers_count() + "");
        holder.nameText.setText(gitRepo.get(position).getName());
        holder.descriptionText.setText(gitRepo.get(position).getDescription());
        holder.starNumText.setText("STAR : " + gitRepo.get(position).getStargazers_count() + "");
        Glide.with(context).load(Uri.parse(gitRepo.get(position).getOwner().getAvatar_url())).placeholder(R.drawable.noimage).into(holder.reopThumb);
        holder.githubContainlayout.setOnClickListener((view) -> {
            if (itemSelectedCallBack != null) {
                itemSelectedCallBack.onSelected(gitRepo.get(position).getHtml_url());
            }
        });
    }

    @Override
    public int getItemCount() {
        return gitRepo.size();
    }


    static class GitReopViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.reop_thumb)
        ImageView reopThumb;
        @BindView(R.id.name_text)
        TextView nameText;
        @BindView(R.id.description_text)
        TextView descriptionText;
        @BindView(R.id.star_num_text)
        TextView starNumText;
        @BindView(R.id.github_repo_containlayout)
        LinearLayout githubContainlayout;
        @BindView(R.id.card_view)
        CardView cardView;

        GitReopViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
