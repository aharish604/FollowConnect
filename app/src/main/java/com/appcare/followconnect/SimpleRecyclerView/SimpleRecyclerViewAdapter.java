package com.appcare.followconnect.SimpleRecyclerView;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

public class SimpleRecyclerViewAdapter <T> extends RecyclerView.Adapter {
    private ArrayList<T> commonArrayList = new ArrayList();
    private int resource;
    private AdapterInterface adapterInterface = null;
    private View listView;
    private View noDataFound;
    private Context mContext;

    public SimpleRecyclerViewAdapter(Context mContext, int resource, AdapterInterface adapterInterface, View listView, View noDataFound) {
        this.mContext = mContext;
        this.resource = resource;
        this.adapterInterface = adapterInterface;
        this.listView = listView;
        this.noDataFound = noDataFound;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (this.adapterInterface != null) {
            View viewItem = LayoutInflater.from(parent.getContext()).inflate(this.resource, parent, false);
            return this.adapterInterface.onCreateViewHolder(parent, viewType, viewItem);
        } else {
            return null;
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final T t = this.commonArrayList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (SimpleRecyclerViewAdapter.this.adapterInterface != null) {
                    SimpleRecyclerViewAdapter.this.adapterInterface.onItemClick(t, v, position);
                }

            }
        });
        if (this.adapterInterface != null) {
            this.adapterInterface.onBindViewHolder(holder, position, t);
        }

    }

    public int getItemCount() {
        return this.commonArrayList.size();
    }

    public void refreshAdapter(ArrayList<T> commonArrayList) {
        this.commonArrayList.clear();
        this.commonArrayList.addAll(commonArrayList);
        this.notifyDataSetChanged();
        if (this.commonArrayList.isEmpty()) {
            if (this.noDataFound != null) {
                this.noDataFound.setVisibility(0);
            }

            if (this.listView != null) {
                this.listView.setVisibility(8);
            }
        } else {
            if (this.noDataFound != null) {
                this.noDataFound.setVisibility(8);
            }

            if (this.listView != null) {
                this.listView.setVisibility(0);
            }
        }

    }

    public void searchFilter(final ArrayList<T> commonArrayList, final SearchFilterInterface searchFilterInterface) {
        (new Thread(new Runnable() {
            public void run() {
                final Collection filterList = SearchFilter.filter(commonArrayList, searchFilterInterface);
                ((Activity)SimpleRecyclerViewAdapter.this.mContext).runOnUiThread(new Runnable() {
                    public void run() {
                        SimpleRecyclerViewAdapter.this.refreshAdapter((ArrayList)filterList);
                    }
                });
            }
        })).start();
    }
}
