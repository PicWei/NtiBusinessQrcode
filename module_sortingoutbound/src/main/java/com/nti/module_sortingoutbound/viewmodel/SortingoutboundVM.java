package com.nti.module_sortingoutbound.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.nti.lib_common.bean.Paramer;
import com.nti.lib_common.bean.ReturnoutboundOrderInfo;
import com.nti.lib_common.bean.SortingoutboundOrderInfo;
import com.nti.module_sortingoutbound.repository.SortingoutboundRepo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author: weiqiyuan
 * @date: 2022/8/11
 * @describe
 */
public class SortingoutboundVM extends AndroidViewModel {

    private MutableLiveData<List<SortingoutboundOrderInfo>> data = new MutableLiveData<>();
    private SortingoutboundRepo repository = new SortingoutboundRepo();

    public SortingoutboundVM(@NonNull @NotNull Application application) {
        super(application);
    }

    public MutableLiveData<List<SortingoutboundOrderInfo>> PDA_H(Paramer paramer){
        data = repository.PDA_H(paramer);
        return data;
    }
}
