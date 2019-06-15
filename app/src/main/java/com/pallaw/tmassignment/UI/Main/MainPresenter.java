package com.pallaw.tmassignment.UI.Main;

import android.widget.Toast;

import com.pallaw.tmassignment.Data.DataManager;
import com.pallaw.tmassignment.Data.Models.TMResponse;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Pallaw Pathak on 2019-06-14. - https://www.linkedin.com/in/pallaw-pathak-a6a324a1/
 */
public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private DataManager dataManager;

    public MainPresenter(MainContract.View view, DataManager dataManager) {
        this.view = view;
        this.dataManager = dataManager;
    }

    @Override
    public void onDownloadData() {
        view.showMsg("Clicked");

        Observable<TMResponse> tmResponse = dataManager.getApiService().getTMResponse();
        tmResponse.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TMResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TMResponse tmResponse) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
