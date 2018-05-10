package com.m.emad.madarsoft.module;


import com.m.emad.madarsoft.scope.FragmentScoped;
import com.m.emad.madarsoft.view.addlocation.AddLocationContarct;
import com.m.emad.madarsoft.view.addlocation.presenter.LocationPresenter;
import com.m.emad.madarsoft.view.addlocation.view.LocationFragment;
import com.m.emad.madarsoft.view.home.HomeContarct;
import com.m.emad.madarsoft.view.home.presenter.HomePresenter;
import com.m.emad.madarsoft.view.home.view.HomeFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class HomeModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract HomeFragment homeFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract LocationFragment locationFragment();

    @FragmentScoped
    @Binds
    abstract HomeContarct.Presenter homePresenter(HomePresenter presenter);

    @FragmentScoped
    @Binds
    abstract AddLocationContarct.Presenter locationPresenter(LocationPresenter presenter);

}
