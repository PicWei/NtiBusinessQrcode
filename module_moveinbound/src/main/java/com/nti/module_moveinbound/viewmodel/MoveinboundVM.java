package com.nti.module_moveinbound.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.nti.lib_common.bean.MoveinboundOrderInfo;
import com.nti.lib_common.bean.Paramer;
import com.nti.module_moveinbound.repository.MoveinboundRepo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author: weiqiyuan
 * @date: 2022/8/10
 * @describe
 */
public class MoveinboundVM extends AndroidViewModel {
    private MutableLiveData<List<MoveinboundOrderInfo>> data = new MutableLiveData<>();
    private MoveinboundRepo repository = new MoveinboundRepo();

    public MoveinboundVM(@NonNull @NotNull Application application) {
        super(application);
    }

    public MutableLiveData<List<MoveinboundOrderInfo>> PDA_H(Paramer paramer){
        data = repository.PDA_H(paramer);
        return data;
    }
}
