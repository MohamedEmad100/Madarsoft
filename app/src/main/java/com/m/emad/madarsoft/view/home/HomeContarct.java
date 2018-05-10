package com.m.emad.madarsoft.view.home;

import com.m.emad.madarsoft.base.BaseContract;
import com.m.emad.madarsoft.data.model.Coord;
import com.m.emad.madarsoft.data.model.CurrentWearher;

import java.util.List;


public interface HomeContarct {

    interface View extends BaseContract.View{
        void updateList(List<CurrentWearher> currentWearherList);
        void showError();
        void noLoactionsExist();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void loadList(List<Coord> coords);
        void getSaveCoord();

    }
}
