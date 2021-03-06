package com.allinmyapp.news.UI;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allinmyapp.news.Model.NewsEntity;
import com.allinmyapp.news.R;
import com.allinmyapp.news.UI.NewsFragment.OnListFragmentInteractionListener;
import com.allinmyapp.news.UI.dummy.DummyContent.DummyItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {

    private final List<NewsEntity> mValues;
    private final OnListFragmentInteractionListener mListener;

    public NewsRecyclerViewAdapter(List<NewsEntity> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitle.setText(mValues.get(position).getTitle());
        holder.mPubDate.setText(mValues.get(position).getPubDate());
        if(mValues.get(position).getImageUrl() != null) {
            Picasso.with(holder.mView.getContext())
                    .load(mValues.get(position).getImageUrl())
                    .into(holder.mImageView);
        }


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitle;
        public final TextView mPubDate;
        private final ImageView mImageView;
        public NewsEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = (TextView) view.findViewById(R.id.news);
            mImageView = (ImageView) view.findViewById(R.id.image_icon);
            mPubDate = (TextView) view.findViewById(R.id.pubDate);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mPubDate.getText() + "'";
        }
    }
}
