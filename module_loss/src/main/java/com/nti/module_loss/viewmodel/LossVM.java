package com.nti.module_loss.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.nti.lib_common.bean.LossOrderInfo;
import com.nti.lib_common.bean.Paramer;
import com.nti.module_loss.repository.LossRepo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author: weiqiyuan
 * @date: 2022/8/11
 * @describe
 */
public class LossVM extends AndroidViewModel {
    private MutableLiveData<List<LossOrderInfo>> data = new MutableLiveData<>();
    private LossRepo repository = new LossRepo();

    public LossVM(@NonNull @NotNull Application application) {
        super(application);
    }

    public MutableLiveData<List<LossOrderInfo>> PDA_H(Paramer paramer){
        data = repository.PDA_H(paramer);
        return data;
    }
}
