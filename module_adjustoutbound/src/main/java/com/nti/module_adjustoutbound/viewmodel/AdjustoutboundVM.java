package com.nti.module_adjustoutbound.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.nti.lib_common.bean.AdjustoutboundOrderInfo;
import com.nti.lib_common.bean.ArrivalInboundOrderInfo;
import com.nti.lib_common.bean.Paramer;
import com.nti.module_adjustoutbound.repository.AdjustoutboundRepo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author: weiqiyuan
 * @date: 2022/8/10
 * @describe
 */
public class AdjustoutboundVM extends AndroidViewModel {

    private MutableLiveData<List<AdjustoutboundOrderInfo>> data = new MutableLiveData<>();
    private AdjustoutboundRepo repository = new AdjustoutboundRepo();

    public AdjustoutboundVM(@NonNull @NotNull Application application) {
        super(application);
    }

    public MutableLiveData<List<AdjustoutboundOrderInfo>> PDA_H(Paramer paramer){
        data = repository.PDA_H(paramer);
        return data;
    }

}
