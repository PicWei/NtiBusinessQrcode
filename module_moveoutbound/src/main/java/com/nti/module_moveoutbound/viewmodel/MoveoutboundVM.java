package com.nti.module_moveoutbound.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.nti.lib_common.bean.AdjustinboundOrderInfo;
import com.nti.lib_common.bean.DataResult;
import com.nti.lib_common.bean.ErrorSignReceiveParamer;
import com.nti.lib_common.bean.MoveoutboundOrderInfo;
import com.nti.lib_common.bean.MoveoutboundOrderInfo;
import com.nti.lib_common.bean.Paramer;
import com.nti.lib_common.bean.UpParamer;
import com.nti.module_moveoutbound.repository.MoveoutboundRepo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author: weiqiyuan
 * @date: 2022/8/10
 * @describe
 */
public class MoveoutboundVM extends AndroidViewModel {
    private  MoveoutboundRepo repository = new MoveoutboundRepo();

    private MutableLiveData<JsonObject> data;
    private MutableLiveData<JsonObject> data3;
    private MutableLiveData<DataResult<List<MoveoutboundOrderInfo>>> data2 = new MutableLiveData<>();

    public MoveoutboundVM(@NonNull @NotNull Application application) {
        super(application);
    }

    public MutableLiveData<DataResult<List<MoveoutboundOrderInfo>>> PDA_H(Paramer paramer){
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
