package com.pallaw.tmassignment.UI.Gallary;

import com.pallaw.tmassignment.Data.Models.TMResponse;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface GalleryContract {

    interface View {

        void showMsg(String msg);

        void showData(TMResponse tmResponse);
    }

    interface Presenter {
        void onDownloadData();
    }
}
