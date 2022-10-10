package com.nti.module_returninbound.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.nti.lib_common.bean.DataResult;
import com.nti.lib_common.bean.ErrorSignReceiveParamer;
import com.nti.lib_common.bean.ReturninboundOrderInfo;
import com.nti.lib_common.bean.Paramer;
import com.nti.lib_common.bean.ReturninboundOrderInfo;
import com.nti.lib_common.bean.ReturnoutboundOrderInfo;
import com.nti.lib_common.bean.UpParamer;
import com.nti.module_returninbound.repository.ReturninboundRepo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author: weiqiyuan
 * @date: 2022/8/11
 * @describe
 */
public class ReturninboundVM extends AndroidViewModel {
    private  ReturninboundRepo repository = new ReturninboundRepo();

    private MutableLiveData<JsonObject> data;
    private MutableLiveData<JsonObject> data3;
    private MutableLiveData<DataResult<List<ReturninboundOrderInfo>>> data2 = new MutableLiveData<>();

    public ReturninboundVM(@NonNull @NotNull Application application) {
        super(application);
    }

    public MutableLiveData<DataResult<List<ReturninboundOrderInfo>>> PDA_H(Paramer paramer){
        data2 = repository.PDA_H(paramer);
        return data2;
    }

    public MutableLiveData<JsonObject> updataSellListStatues(UpParamer paramer){
        data = repository.updataSellListStatues(paramer);
        return data;
    }

    public LiveData<JsonObject> errorSignReceive(ErrorSignReceiveParamer paramer){
        data3 = repository.errorSignReceive(paramer);
        return data3;
    }
}
