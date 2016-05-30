package in.ashwanik.popularmovie1.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.ashwanik.popularmovie1.R;
import in.ashwanik.popularmovie1.entities.Result;
import in.ashwanik.popularmovie1.utils.Helpers;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ItemViewHolder> {

    List<Result> arrayList;
    private LayoutInflater inflater;

    public ReviewsAdapter(Context context, List<Result> reviews) {
        inflater = LayoutInflater.from(context);
        this.arrayList = reviews;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.r_review, parent, false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Result result = arrayList.get(position);
        holder.author.setText(result.getAuthor());
        holder.review.setText(result.getContent());
        holder.more.setText(result.getUrl());

        Helpers.recyclerAnim(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @Bind(R.id.author)
        TextView author;
        @Bind(R.id.review)
        TextView review;
        @Bind(R.id.more)
        TextView more;
        @Bind(R.id.llWrapper)
        RelativeLayout llWrapper;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            llWrapper.setOnClickListener(this);
            llWrapper.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }

        @Override
        public boolean onLongClick(View view) {

            return true;
        }
    }

}
