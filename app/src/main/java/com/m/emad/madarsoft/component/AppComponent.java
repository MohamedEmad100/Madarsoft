package com.m.emad.madarsoft.component;

import android.app.Application;

import com.m.emad.madarsoft.WeatherApp;
import com.m.emad.madarsoft.module.ActivityBindingModule;
import com.m.emad.madarsoft.module.ApplicationModule;
import com.m.emad.madarsoft.module.ServiceModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules = {ApplicationModule.class, ServiceModule.class, ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(WeatherApp application);


    @Override
    void inject(DaggerApplication instance);

    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
