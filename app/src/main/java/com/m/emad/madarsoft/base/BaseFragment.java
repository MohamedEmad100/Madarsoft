package com.m.emad.madarsoft.base;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.m.emad.madarsoft.R;
import com.m.emad.madarsoft.view.utils.FragmentUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;


public abstract class BaseFragment<P extends BaseContract.BasePresenter> extends DaggerFragment implements BaseContract.View<P> , BaseOnBack {

    protected Context mContext;
    protected View rootView;
    protected ViewStub mainViewStub;


    @BindView(R.id.main_content)
    View contentView;


    protected P mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mainViewStub = (ViewStub) rootView.findViewById(R.id.main_stub);

        if (getLayoutRes() != -1) {
            mainViewStub.setLayoutResource(getLayoutRes());
            mainViewStub.inflate();
        }

        ButterKnife.bind(this, rootView);
        return rootView;
    }

    /**
     * Called when a fragment is first attached to its context.
     * {@link #onCreate(Bundle)} will be called after this.
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    /**
     * Called when the fragment is no longer attached to its activity.  This
     * is called after {@link #onDestroy()}.
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        FragmentUtil.reAttachCurrentFragment(this);
    }

    @Override
    public boolean onBackPressed() {
        int childCount = getChildFragmentManager().getBackStackEntryCount();
        if (childCount > 0) {
            getChildFragmentManager().popBackStackImmediate();
            return true;
        }
        return false;
    }

    protected abstract int getLayoutRes();


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }


    public void setPresenter(P mPresenter) {
        this.mPresenter = mPresenter;
        this.mPresenter.attachView(this);
    }


    /**
     * Creates a new presenter instance, if needed. Will reuse the previous presenter instance if
     */
    public P getPresenter() {
        return mPresenter;
    }


    public void setBackView(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }


    protected void showSnackbarView(View parentView, String message, @SnackViewType int type) {
        if (isVisible()) {
            Snackbar snackbar = Snackbar
                    .make(parentView, message, Snackbar.LENGTH_LONG);

            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);

            switch (type) {
                case SnackViewType.Info:
                    sbView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    break;
                case SnackViewType.error:
                    sbView.setBackgroundColor(getResources().getColor(R.color.redDark));
                    break;
            }

            snackbar.show();
        }
    }

    @IntDef({SnackViewType.Info, SnackViewType.warning, SnackViewType.error})
    public @interface SnackViewType {
        int Info = 0;
        int warning = 1;
        int error = 2;
    }
}