package com.m.emad.madarsoft.view.home.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.m.emad.madarsoft.R;
import com.m.emad.madarsoft.base.BaseFragment;
import com.m.emad.madarsoft.data.model.CurrentWearher;
import com.m.emad.madarsoft.view.adapter.HomeItemsAdapter;
import com.m.emad.madarsoft.view.home.HomeContarct;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class HomeFragment extends BaseFragment implements HomeContarct.View{


    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.tv_error)
    TextView tv_Error;

    @BindView(R.id.rv_mylist)
    RecyclerView mList;


    @BindView(R.id.no_data)
    TextView tv_noData;

    @BindView(R.id.loadmore_progress)
    ProgressBar progressBar;

    private HomeItemsAdapter homeItemsAdapter;

    @Inject
    HomeContarct.Presenter homePresenter;


    public static HomeFragment newInstance(){
        Bundle args = new Bundle();
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(args);
        return homeFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setPresenter(homePresenter);

        homeItemsAdapter = new HomeItemsAdapter(mContext, new ArrayList<CurrentWearher>());
        final GridLayoutManager layoutManager = new GridLayoutManager(mContext,2);
        mList.setLayoutManager(layoutManager);
        mList.setItemAnimator(new DefaultItemAnimator());
        mList.setAdapter(homeItemsAdapter);

        homePresenter.getSaveCoord();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                homePresenter.getSaveCoord();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }

    @Override
    protected int getLayoutRes() {
        return R.layout.home_fragment;
    }

    @Override
    public void updateList(List<CurrentWearher> currentWearherList) {
        if(!currentWearherList.isEmpty()) {
            homeItemsAdapter.setDataList(currentWearherList);
        }
    }

    @Override
    public void showError() {
        tv_Error.setVisibility(View.VISIBLE);

    }

    @Override
    public void showLoading() {
        tv_Error.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        tv_noData.setVisibility(View.GONE);
    }

    @Override
    public void showContent() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void noLoactionsExist() {
        tv_noData.setVisibility(View.VISIBLE);
    }
}
