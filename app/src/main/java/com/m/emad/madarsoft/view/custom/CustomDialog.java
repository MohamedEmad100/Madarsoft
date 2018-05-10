package com.m.emad.madarsoft.view.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.m.emad.madarsoft.R;
import com.m.emad.madarsoft.base.BaseDialog;

import butterknife.BindView;
public class CustomDialog extends BaseDialog {

    @BindView(R.id.error_title)
    TextView txtTitle;

    @BindView(R.id.error_message)
    TextView txtMessage;

    @BindView(R.id.btn_primary)
    Button btnPrimary;

    @BindView(R.id.btn_secondary)
    Button btnSecondary;

    String mstrTitle;
    String mstrMessage;
    String mstrPrimaryButton;
    String mstrSecondaryButton;
    View.OnClickListener primaryButtonListener;
    View.OnClickListener secondaryButtonListener;

    public static CustomDialog getInstance(String title , String message) {
        return new CustomDialog().setTitle(title).setMessage(message);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtTitle.setText(mstrTitle);
        txtMessage.setText(mstrMessage);
        if (primaryButtonListener != null){
            btnPrimary.setOnClickListener(primaryButtonListener);
            btnPrimary.setText(mstrPrimaryButton);
            btnPrimary.setVisibility(View.VISIBLE);
        }

        if (secondaryButtonListener != null){
            btnSecondary.setOnClickListener(secondaryButtonListener);
            btnSecondary.setText(mstrSecondaryButton);
            btnSecondary.setVisibility(View.VISIBLE);
        }
    }


    public CustomDialog setTitle(String title) {
        this.mstrTitle = title;
        return this;
    }

    public CustomDialog setMessage(String message) {
        this.mstrMessage = message;
        return this;
    }

    public CustomDialog setPrimaryButton(String buttonText ,View.OnClickListener primaryButtonListener) {
        this.primaryButtonListener = primaryButtonListener;
        this.mstrPrimaryButton = buttonText;
        return this;
    }

    public CustomDialog setSecondaryButton(String buttonText ,View.OnClickListener secondaryButtonListener) {
        this.secondaryButtonListener = secondaryButtonListener;
        this.mstrSecondaryButton = buttonText;
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_custom;
    }

}
