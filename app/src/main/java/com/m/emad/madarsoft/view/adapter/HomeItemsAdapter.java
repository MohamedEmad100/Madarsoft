package com.m.emad.madarsoft.view.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.m.emad.madarsoft.R;
import com.m.emad.madarsoft.base.BaseViewHolder;
import com.m.emad.madarsoft.constants.NetworkConstants;
import com.m.emad.madarsoft.data.model.CurrentWearher;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;



public class HomeItemsAdapter extends PaginationAdapter<CurrentWearher> {

    private OnHomeItemClickListener mListener;

    public HomeItemsAdapter(Context objView, List<CurrentWearher> currentWearherList) {
        super(objView);
        setDataList(currentWearherList);
        //mListener = listener;
    }

    public interface OnHomeItemClickListener {
        void onHomeItemClicked(CurrentWearher currentWearher);
    }

    @Override
    protected BaseViewHolder getHolderInstance(View view) {
        return new HomeitemViewHolder(view);
    }

    @Override
    protected BaseViewHolder getLoadingInstance(View view) {
        return new BaseViewHolder(view) {
            @Override
            public void bindViewData(Object businessObject, int position) {

            }
        };
    }

    @Override
    protected int getLoadingResourceId() {
        return R.layout.row_progress;
    }

    @Override
    protected int getHolderResourceId() {
        return R.layout.item_home;
    }

    class HomeitemViewHolder extends BaseViewHolder<CurrentWearher> {
        @BindView(R.id.tv_main)
        TextView tv_main;
        @BindView(R.id.imv_weather)
        CircleImageView imvWeather;
        @BindView(R.id.txt_cityName)
        TextView txtCityName;
        @BindView(R.id.txt_date)
        TextView txtDate;
        @BindView(R.id.txt_temp)
        TextView txtTemp;
        @BindView(R.id.txt_temp_min_max)
        TextView txtTempMinMax;

        public HomeitemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this ,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onHomeItemClicked(mBusinessObject);
                    }
                }
            });

        }

        @Override
        public void bindViewData(CurrentWearher businessObject, int position) {
            String cityName = businessObject.getName();
            DateTime dt = new DateTime();
            int day = dt.getDayOfMonth();
            int month = dt.getMonthOfYear();
            int year = dt.getYear();
            int hour = dt.getHourOfDay();
            int minu = dt.getMinuteOfHour();
            double temp = businessObject.getMain().getTemp();
            double temp_min = businessObject.getMain().getTemp_min();
            double temp_max = businessObject.getMain().getTemp_max();
            String icon = businessObject.getWeather().get(0).getIcon();
            String main = businessObject.getWeather().get(0).getMain();

            txtCityName.setText(cityName);
            txtDate.setText(""+ day +"/"+ month +"/"+ year + "  " +hour+":"+minu);
            tv_main.setText(main);
            txtTemp.setText(""+ temp+" \u2103");
            txtTempMinMax.setText(temp_max +" \u2103"+ " / " + temp_min+" \u2103");

            if (!TextUtils.isEmpty(icon)) {
                Picasso.with(mContext).load(NetworkConstants.IMAGE_URL + icon + ".png").fit().into(imvWeather);
            } else {
                Picasso.with(mContext).load(R.mipmap.ic_launcher).into(imvWeather);
            }

        }
    }

}
