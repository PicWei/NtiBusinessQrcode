package com.nti.lib_common.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.nti.lib_common.bean.ErrorSignReceiveParamer;
import com.nti.lib_common.bean.SellBarcodeReciveParamer;
import com.nti.lib_common.bean.UpParamer;
import com.nti.lib_common.repository.Repository;

import org.jetbrains.annotations.NotNull;

/**
 * @author: weiqiyuan
 * @date: 2022/8/1
 * @describe
 */
public class ViewModel extends AndroidViewModel {
    private Repository repository = new Repository();
    private MutableLiveData<JsonObject> data;
    private MutableLiveData<JsonObject> data2;
    private MutableLiveData<JsonObject> data3;

    public ViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public MutableLiveData<JsonObject> updataSellListStatues(UpParamer paramer){
        data = repository.updataSellListStatues(paramer);
        return data;
    }

    public MutableLiveData<JsonObject> sellBarcodeRecive(SellBarcodeReciveParamer paramer){
        data2 = repository.sellBarcodeRecive(paramer);
        return data2;
    }

    public LiveData<JsonObject> errorSignReceive(ErrorSignReceiveParamer paramer){
        data3 = repository.errorSignReceive(paramer);
        return data3;
    }
}
