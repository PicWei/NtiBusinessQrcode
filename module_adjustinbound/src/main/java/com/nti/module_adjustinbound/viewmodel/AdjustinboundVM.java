package com.nti.module_adjustinbound.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.nti.lib_common.bean.AdjustinboundOrderInfo;
import com.nti.lib_common.bean.AdjustoutboundOrderInfo;
import com.nti.lib_common.bean.Paramer;
import com.nti.module_adjustinbound.repository.AdjustinboundRepo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author: weiqiyuan
 * @date: 2022/8/10
 * @describe
 */
public class AdjustinboundVM extends AndroidViewModel {
    private MutableLiveData<List<AdjustinboundOrderInfo>> data = new MutableLiveData<>();
    private AdjustinboundRepo repository = new AdjustinboundRepo();

    public AdjustinboundVM(@NonNull @NotNull Application application) {
        super(application);
    }

    public MutableLiveData<List<AdjustinboundOrderInfo>> PDA_H(Paramer paramer){
        data = repository.PDA_H(paramer);
        return data;
    }
}
