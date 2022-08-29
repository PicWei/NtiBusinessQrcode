package com.nti.module_device.service;

import com.google.gson.JsonObject;
import com.nti.module_device.bean.PdaRegisterParamer;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author: weiqiyuan
 * @date: 2022/8/1
 * @describe
 */
public interface IDeviceServie {
    @POST("/api/fdevelop/tool/front/runtime-server/common/transflow/executeCode/BUSSIN_PDA_LOGIN")
    Observable<JsonObject> register (@Body PdaRegisterParamer paramer);
}
