package com.pallaw.tmassignment.UI.Main;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface MainContract {

    interface View {

        void showMsg(String msg);
    }

    interface Presenter {
        void onDownloadData();
    }
}
