package com.nti.module_sortinginbound.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.nti.lib_common.bean.Paramer;
import com.nti.lib_common.bean.SortinginboundOrderInfo;
import com.nti.lib_common.bean.SortingoutboundOrderInfo;
import com.nti.module_sortinginbound.repository.SortinginboundRepo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author: weiqiyuan
 * @date: 2022/8/11
 * @describe
 */
public class SortinginboundVM extends AndroidViewModel {
    private MutableLiveData<List<SortinginboundOrderInfo>> data = new MutableLiveData<>();
    private SortinginboundRepo repository = new SortinginboundRepo();

    public SortinginboundVM(@NonNull @NotNull Application application) {
        super(application);
    }

    public MutableLiveData<List<SortinginboundOrderInfo>> PDA_H(Paramer paramer){
        data = repository.PDA_H(paramer);
        return data;
    }
}
