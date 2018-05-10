package com.m.emad.madarsoft.module;


import com.m.emad.madarsoft.scope.ActivityScoped;
import com.m.emad.madarsoft.view.home.view.HomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBindingModule {


    @ActivityScoped
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeActivity homeActivity();


}
