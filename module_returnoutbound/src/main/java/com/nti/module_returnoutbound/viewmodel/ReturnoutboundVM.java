package com.nti.module_returnoutbound.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.nti.lib_common.bean.ArrivalInboundOrderInfo;
import com.nti.lib_common.bean.Paramer;
import com.nti.lib_common.bean.ReturnoutboundOrderInfo;
import com.nti.module_returnoutbound.repository.ReturnoutboundRepo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author: weiqiyuan
 * @date: 2022/8/10
 * @describe
 */
public class ReturnoutboundVM extends AndroidViewModel {
    private MutableLiveData<List<ReturnoutboundOrderInfo>> data = new MutableLiveData<>();
    private ReturnoutboundRepo repository = new ReturnoutboundRepo();

    public ReturnoutboundVM(@NonNull @NotNull Application application) {
        super(application);
    }

    public MutableLiveData<List<ReturnoutboundOrderInfo>> PDA_H(Paramer paramer){
        data = repository.PDA_H(paramer);
        return data;
    }
}
