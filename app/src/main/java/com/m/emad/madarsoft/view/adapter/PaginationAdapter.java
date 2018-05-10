package com.m.emad.madarsoft.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.m.emad.madarsoft.base.BaseAdapter;
import com.m.emad.madarsoft.base.BaseViewHolder;


public abstract class PaginationAdapter<T> extends BaseAdapter<T> {
    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private boolean isLoadingData;
    private boolean isLoadedAllPages;

    private boolean displayLoadingRow = false;

    public PaginationAdapter(Context objView) {
        super(objView);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        BaseViewHolder viewHolder;
        View view;
        switch (viewType) {
            case LOADING:
                view = inflater.inflate(getLoadingResourceId(), parent, false);
                viewHolder = getLoadingInstance(view);
                break;
            default:
                view = inflater.inflate(getHolderResourceId(), parent, false);
                viewHolder = getHolderInstance(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case ITEM:
                T obj = mlstData.get(position);
                holder.bindData(obj, position);
                break;
            case LOADING:
                holder.bindData(null, position);
                break;
        }
    }


    public void displayLoadingRow(boolean displayLoadingRow) {
        if (this.displayLoadingRow != displayLoadingRow) {
            this.displayLoadingRow = displayLoadingRow;
            if (displayLoadingRow) {
                notifyItemInserted(getLoadingRowPosition());
            } else {
                notifyItemRemoved(getLoadingRowPosition());
            }
        }
    }

    boolean isLoadingRow(int position) {
        return displayLoadingRow && position == getLoadingRowPosition();
    }

    private int getLoadingRowPosition() {
        return displayLoadingRow ? getItemCount() - 1 : -1;
    }

    @Override
    public int getItemViewType(int position) {
        return isLoadingRow(position) ? LOADING : ITEM;
    }

    @Override
    public int getItemCount() {
        return displayLoadingRow ? mlstData.size() + 1 : mlstData.size();
    }

    protected abstract BaseViewHolder getLoadingInstance(View view);

    protected abstract int getLoadingResourceId();

    public boolean isLoadingData() {
        return isLoadingData;
    }

    public void setLoadingData(boolean loadingData) {
        isLoadingData = loadingData;
    }

    public boolean isLoadedAllPages() {
        return isLoadedAllPages;
    }

    public void setLoadedAllPages(boolean loadedAllPages) {
        isLoadedAllPages = loadedAllPages;
    }
}
