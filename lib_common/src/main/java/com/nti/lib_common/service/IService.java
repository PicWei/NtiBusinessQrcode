package com.nti.lib_common.service;

import com.google.gson.JsonObject;
import com.nti.lib_common.bean.ErrorSignReceiveParamer;
import com.nti.lib_common.bean.Paramer;
import com.nti.lib_common.bean.SellBarcodeReciveParamer;
import com.nti.lib_common.bean.UpParamer;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author: weiqiyuan
 * @date: 2022/7/29
 * @describe
 */
public interface IService {

    @POST("/api/fdevelop/tool/front/runtime-server/common/transflow/executeCode/BUSSIN_DATA_LOADING")
    Observable<JsonObject> PDA_H(@Body Paramer paramer);

    @POST("/api/fdevelop/tool/front/runtime-server/common/transflow/executeCode/BUSSIN_CHANGE_STATE")
    Observable<JsonObject> updataSellListStatues(@Body UpParamer paramer);

    @POST("/api/fdevelop/tool/front/runtime-server/common/transflow/executeCode/BUSSIN_UNIFIED_ERROR_BARCODE")
    Observable<JsonObject> errorSignReceive(@Body ErrorSignReceiveParamer paramer);

    //接收PDA回送的扫描条码信息
    @POST("/api/fdevelop/tool/front/runtime-server/common/transflow/executeCode/BUSSIN_RECEIVE_QR")
    Observable<JsonObject> sellBarcodeRecive(@Body SellBarcodeReciveParamer paramer);

}
