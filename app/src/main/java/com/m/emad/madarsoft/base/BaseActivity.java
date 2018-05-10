package com.m.emad.madarsoft.base;

import android.support.v4.app.Fragment;

import com.m.emad.madarsoft.R;
import com.m.emad.madarsoft.view.utils.FragmentUtil;

import dagger.android.support.DaggerAppCompatActivity;


public class BaseActivity extends DaggerAppCompatActivity{

    @Override
    public void onBackPressed() {
        Fragment currentFragment = FragmentUtil.getCurrentFragment(getSupportFragmentManager(), R.id.mainFragmentContainer);
        if( currentFragment != null && currentFragment instanceof BaseOnBack){
            if(((BaseOnBack) currentFragment).onBackPressed()){
                return;
            }
        }

        super.onBackPressed();
    }


    public void pushFragment(Fragment fragment){}
    public void addFragment(Fragment fragment){}
}
