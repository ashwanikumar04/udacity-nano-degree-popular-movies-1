package in.ashwanik.popularmovie1.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joanzapata.iconify.fonts.MaterialIcons;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.ashwanik.popularmovie1.R;
import in.ashwanik.popularmovie1.common.BaseApplication;
import in.ashwanik.popularmovie1.entities.Youtube;
import in.ashwanik.popularmovie1.interfaces.IClickHandler;
import in.ashwanik.popularmovie1.utils.FontIconHelper;
import in.ashwanik.popularmovie1.utils.Helpers;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.ItemViewHolder> {

    List<Youtube> arrayList;
    private LayoutInflater inflater;
    IClickHandler handler;

    public TrailersAdapter(Context context, List<Youtube> trailers, IClickHandler handler) {
        inflater = LayoutInflater.from(context);
        this.arrayList = trailers;
        this.handler = handler;

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.r_trailer, parent, false);
        ItemViewHolder holder = new ItemViewHolder(view);
        holder.setClickHandler(handler);

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Youtube result = arrayList.get(position);
        holder.image.setImageResource(R.drawable.placeholder);
        if (!TextUtils.isEmpty(result.getFullPosterPath())) {
            Glide.with(BaseApplication.getInstance())
                    .load(result.getFullPosterPath())
                    .placeholder(R.drawable.placeholder)
                    .into(holder.image);
        }
        holder.text.setText(result.getName());
        holder.play.setImageDrawable(FontIconHelper.getFontDrawable(BaseApplication.getInstance(), MaterialIcons.md_play_circle_filled));
        holder.share.setImageDrawable(FontIconHelper.getFontDrawable(BaseApplication.getInstance(), MaterialIcons.md_share));
        Helpers.recyclerAnim(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @Bind(R.id.image)
        ImageView image;
        @Bind(R.id.text)
        TextView text;
        @Bind(R.id.play)
        ImageButton play;

        @Bind(R.id.share)
        ImageButton share;

        @Bind(R.id.llWrapper)
        RelativeLayout llWrapper;
        IClickHandler clickHandler;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            llWrapper.setOnClickListener(this);
            llWrapper.setOnLongClickListener(this);
            play.setOnClickListener(this);
            share.setOnClickListener(this);
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
                if (view instanceof ImageButton) {
                    getClickHandler().onButtonAction(view, getLayoutPosition());

                } else {
                    getClickHandler().onItemClicked(view, getLayoutPosition());
                }
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
