package com.example.guanzhuli.wechat.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public abstract class PagenationScrollListener extends RecyclerView.OnScrollListener {
    private int previousTotal = 0;
    private LinearLayoutManager linearLayoutManager;
    private boolean isLoading = false;
    private int currentPage = 1;

    public PagenationScrollListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }


    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        int total = linearLayoutManager.getItemCount();
        int last = linearLayoutManager.findLastCompletelyVisibleItemPosition();
        if (isLoading && total > previousTotal) {
            previousTotal = total;
            isLoading = false;
        }
        if (!isLoading && last  + 1 >= total) {
            Log.i("chestnut", "page" + currentPage);
            currentPage++;
            loadMore(5, 5);
            isLoading = true;
        }
    }

    public void reset() {
        isLoading = false;
        previousTotal = 0;
        currentPage = 0;
    }

    public abstract void loadMore(int limit, int offset);
}
