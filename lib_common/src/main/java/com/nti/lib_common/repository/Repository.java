package com.nti.lib_common.repository;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nti.lib_common.bean.ErrorSignReceiveParamer;
import com.nti.lib_common.bean.Paramer;
import com.nti.lib_common.bean.SellBarcodeReciveParamer;
import com.nti.lib_common.bean.UpParamer;
import com.nti.lib_common.service.IService;
import com.nti.lib_common.utils.HttpUtils;

import org.jetbrains.annotations.NotNull;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: weiqiyuan
 * @date: 2022/7/29
 * @describe
 */
public class Repository {
    public MutableLiveData<JsonObject> updataSellListStatues(UpParamer paramer){
        final MutableLiveData<JsonObject> data = new MutableLiveData<>();
        HttpUtils.getInstance().with(IService.class).updataSellListStatues(paramer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull JsonObject jsonObject) {
                        data.setValue(jsonObject);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        data.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return data;
    }

    public MutableLiveData<JsonObject> sellBarcodeRecive(SellBarcodeReciveParamer paramer){
        final MutableLiveData<JsonObject> data = new MutableLiveData<>();

        HttpUtils.getInstance().with(IService.class).sellBarcodeRecive(paramer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull JsonObject jsonObject) {
                        data.setValue(jsonObject);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        data.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return data;
    }

    public MutableLiveData<JsonObject> errorSignReceive(ErrorSignReceiveParamer paramer){
        final MutableLiveData<JsonObject> data = new MutableLiveData<>();
        HttpUtils.getInstance().with(IService.class).errorSignReceive(paramer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull JsonObject jsonObject) {
                        data.setValue(jsonObject);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        data.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return data;
    }
}
