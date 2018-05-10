package com.m.emad.madarsoft.view.home.view;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.m.emad.madarsoft.R;
import com.m.emad.madarsoft.base.BaseActivity;
import com.m.emad.madarsoft.view.addlocation.view.LocationFragment;
import com.m.emad.madarsoft.view.utils.FragmentUtil;

import butterknife.BindView;
import butterknife.OnClick;


public class HomeActivity extends BaseActivity {

    @BindView(R.id.select_location_fab)
    FloatingActionButton Fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();
        }
        setContentView(R.layout.activity_home);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.mainFragmentContainer);
        if (fragment == null) {
            FragmentUtil.replaceFragment(
                    getSupportFragmentManager(), R.id.mainFragmentContainer, HomeFragment.newInstance(), null, false);
        }

        Fab = (FloatingActionButton) findViewById(R.id.select_location_fab);
        Fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(LocationFragment.newInstance());
            }
        });

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment currentFragment = FragmentUtil.getCurrentFragment(getSupportFragmentManager(), R.id.mainFragmentContainer);
                if (currentFragment != null) {
                    if (currentFragment instanceof LocationFragment) {
                        Fab.hide();
                    } else {
                        Fab.show();
                    }
                }
            }
        });
    }


    @OnClick(R.id.select_location_fab)
    void onButtonClicked() {
        pushFragment(LocationFragment.newInstance());
    }


    @Override
    public void pushFragment(Fragment fragment) {
        FragmentUtil.replaceFragment(
                getSupportFragmentManager(), R.id.mainFragmentContainer, fragment);
    }

    @Override
    public void addFragment(Fragment fragment) {
        FragmentUtil.addFragment(getSupportFragmentManager(), R.id.mainFragmentContainer, fragment, R.anim.slide_in_up, R.anim.slide_in_down, R.anim.slide_out_down, R.anim.slide_out_up);
    }


}
