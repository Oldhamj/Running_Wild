package com.example.jackson.simplegeolocator;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.LinkedList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.WordViewHolder>{
    private final LinkedList<String> mRunList;
    private LayoutInflater mInflator;
    private Context context;

    public ListAdapter(Context context, LinkedList<String> runList) {
        mInflator = LayoutInflater.from(context);
        this.mRunList = runList;
        this.context = context;
    }

    @NonNull
    @Override
    public ListAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflator.inflate(R.layout.list_item, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        String mCurrent = mRunList.get(position);
        holder.runItemView.setText(mCurrent);

    }

    @Override
    public int getItemCount() {
        return mRunList.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView runItemView;
        final ListAdapter mAdapter;

        public WordViewHolder(View itemView, ListAdapter adapter) {
            super(itemView);
            runItemView = itemView.findViewById(R.id.run);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int mPosition = getLayoutPosition();
            String mCurrent = mRunList.get(mPosition);
            Intent intent1 = new Intent(context, RunListingActivity.class);
            intent1.putExtra("RunName", mCurrent);
            context.startActivity(intent1);
        }
    }
}
