package in.ashwanik.popularmovie1.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.ashwanik.popularmovie1.R;
import in.ashwanik.popularmovie1.common.BaseApplication;
import in.ashwanik.popularmovie1.entities.Movie;
import in.ashwanik.popularmovie1.interfaces.IClickHandler;
import in.ashwanik.popularmovie1.utils.Helpers;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ItemViewHolder> {

    List<Movie> arrayList;
    IClickHandler handler;
    private LayoutInflater inflater;

    public MoviesAdapter(Context context, List<Movie> movies, IClickHandler handler) {
        inflater = LayoutInflater.from(context);
        this.handler = handler;
        this.arrayList = movies;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.r_movie, parent, false);
        ItemViewHolder holder = new ItemViewHolder(view);
        holder.setClickHandler(handler);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Movie movie = arrayList.get(position);
        holder.moviePoster.setImageResource(R.drawable.placeholder);
        if (!TextUtils.isEmpty(movie.getFullPosterPath())) {
            Glide
                    .with(BaseApplication.getInstance())
                    .load(movie.getFullPosterPath())
                    .placeholder(R.drawable.placeholder)
                    .into(holder.moviePoster);
        }
        holder.moviePoster.setContentDescription(movie.getTitle());
        if (Build.VERSION.SDK_INT >= 21) {
            holder.moviePoster.setTransitionName("icon_" + position);
        }
        Helpers.recyclerAnim(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @Bind(R.id.iv_movie_poster)
        ImageView moviePoster;
        @Bind(R.id.llWrapper)
        RelativeLayout llWrapper;
        IClickHandler clickHandler;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            llWrapper.setOnClickListener(this);
            llWrapper.setOnLongClickListener(this);
        }

        public IClickHandler getClickHandler() {
            return clickHandler;
        }

        public void setClickHandler(IClickHandler clickHandler) {
            this.clickHandler = clickHandler;
        }

        @Override
        public void onClick(View view) {
            if (getClickHandler() != null) {
                getClickHandler().onItemClicked(view, getLayoutPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (getClickHandler() != null) {
                getClickHandler().onItemLongClicked(view, getLayoutPosition());
            }
            return true;
        }
    }

}
