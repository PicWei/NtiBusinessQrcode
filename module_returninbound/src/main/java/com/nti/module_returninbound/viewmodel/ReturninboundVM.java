package com.nti.module_returninbound.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.nti.lib_common.bean.Paramer;
import com.nti.lib_common.bean.ReturninboundOrderInfo;
import com.nti.lib_common.bean.ReturnoutboundOrderInfo;
import com.nti.module_returninbound.repository.ReturninboundRepo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author: weiqiyuan
 * @date: 2022/8/11
 * @describe
 */
public class ReturninboundVM extends AndroidViewModel {

    private MutableLiveData<List<ReturninboundOrderInfo>> data = new MutableLiveData<>();
    private ReturninboundRepo repository = new ReturninboundRepo();

    public ReturninboundVM(@NonNull @NotNull Application application) {
        super(application);
    }

    public MutableLiveData<List<ReturninboundOrderInfo>> PDA_H(Paramer paramer){
        data = repository.PDA_H(paramer);
        return data;
    }

}
