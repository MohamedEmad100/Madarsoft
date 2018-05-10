package com.m.emad.madarsoft.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;

import com.m.emad.madarsoft.R;

import butterknife.ButterKnife;


public abstract class BaseDialog extends android.support.v4.app.DialogFragment {

    protected ViewStub dialogViewStub;

    protected Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.dialog, container, false);
        dialogViewStub = (ViewStub) rootView.findViewById(R.id.dialog_stub);

        if (getLayoutRes() != -1) {
            dialogViewStub.setLayoutResource(getLayoutRes());
            dialogViewStub.inflate();
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        return dialog;
    }

    protected abstract int getLayoutRes();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context ;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }
}
