package com.appcare.followconnect.SimpleRecyclerView;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public interface AdapterInterface<T> {
    void onItemClick(T var1, View var2, int var3);

    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup var1, int var2, View var3);

    void onBindViewHolder(RecyclerView.ViewHolder var1, int var2, T var3);
}
