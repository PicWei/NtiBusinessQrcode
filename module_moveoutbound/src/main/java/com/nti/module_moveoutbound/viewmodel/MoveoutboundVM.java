package com.nti.module_moveoutbound.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.nti.lib_common.bean.AdjustinboundOrderInfo;
import com.nti.lib_common.bean.MoveoutboundOrderInfo;
import com.nti.lib_common.bean.Paramer;
import com.nti.module_moveoutbound.repository.MoveoutboundRepo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author: weiqiyuan
 * @date: 2022/8/10
 * @describe
 */
public class MoveoutboundVM extends AndroidViewModel {
    private MutableLiveData<List<MoveoutboundOrderInfo>> data = new MutableLiveData<>();
    private MoveoutboundRepo repository = new MoveoutboundRepo();

    public MoveoutboundVM(@NonNull @NotNull Application application) {
        super(application);
    }

    public MutableLiveData<List<MoveoutboundOrderInfo>> PDA_H(Paramer paramer){
        data = repository.PDA_H(paramer);
        return data;
    }
}
