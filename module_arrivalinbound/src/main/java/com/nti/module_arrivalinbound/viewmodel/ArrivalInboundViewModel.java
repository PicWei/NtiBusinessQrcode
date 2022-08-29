package com.nti.module_arrivalinbound.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.nti.lib_common.bean.ArrivalInboundOrderInfo;
import com.nti.lib_common.bean.ErrorSignReceiveParamer;
import com.nti.lib_common.bean.Paramer;
import com.nti.lib_common.bean.UpParamer;
import com.nti.module_arrivalinbound.repository.ArrivalInboundRepo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author: weiqiyuan
 * @date: 2022/8/1
 * @describe
 */
public class ArrivalInboundViewModel extends AndroidViewModel {

    private MutableLiveData<List<ArrivalInboundOrderInfo>> data = new MutableLiveData<>();
    private ArrivalInboundRepo repository = new ArrivalInboundRepo();

    public ArrivalInboundViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public MutableLiveData<List<ArrivalInboundOrderInfo>> PDA_H(Paramer paramer){
        data = repository.PDA_H(paramer);
        return data;
    }

}
