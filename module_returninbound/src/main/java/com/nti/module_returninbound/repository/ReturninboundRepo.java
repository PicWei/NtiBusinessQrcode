package com.nti.module_returninbound.repository;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nti.lib_common.bean.Paramer;
import com.nti.lib_common.bean.ReturninboundBarcode;
import com.nti.lib_common.bean.ReturninboundDetail;
import com.nti.lib_common.bean.ReturninboundOrderInfo;
import com.nti.lib_common.bean.ReturninboundBarcode;
import com.nti.lib_common.bean.ReturninboundDetail;
import com.nti.lib_common.bean.ReturninboundOrderInfo;
import com.nti.lib_common.service.IService;
import com.nti.lib_common.utils.HttpUtils;

import org.jetbrains.annotations.NotNull;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: weiqiyuan
 * @date: 2022/8/11
 * @describe
 */
public class ReturninboundRepo {
    public MutableLiveData<List<ReturninboundOrderInfo>> PDA_H(Paramer paramer){
        final MutableLiveData<List<ReturninboundOrderInfo>> data = new MutableLiveData<>();
        final List<ReturninboundOrderInfo> orderInfos = new ArrayList<>();
        HttpUtils.getInstance().with(IService.class).PDA_H(paramer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull JsonObject jsonObject) {
                        JsonArray jsonArray1 = jsonObject.getAsJsonArray("CONTRACT_DETAIL_LIST");
                        List<ReturninboundDetail> salesFactoryDetails = new ArrayList<>();
                        try {
                            for (int i = 0; i < jsonArray1.size(); i++){
                                JsonObject object = jsonArray1.get(i).getAsJsonObject();
                                String pickName = "";
                                if (object.has("BD_PCIG_NAME")){
                                    pickName = object.get("BD_PCIG_NAME").toString().replace("\"", "");
                                }
                                String billPNum = "";
                                if (object.has("BD_BILL_PNUM")){
                                    billPNum = object.get("BD_BILL_PNUM").toString().replace("\"", "");
                                }
                                String billBNum = "";
                                if (object.has("BD_BILL_BNUM")){
                                    billBNum = object.get("BD_BILL_BNUM").toString().replace("\"", "");
                                }
                                String uuid = "";
                                if (object.has("BD_BB_UUID")){
                                    uuid = object.get("BD_BB_UUID").toString().replace("\"", "");
                                }
                                String pickCode = "";
                                if (object.has("BD_PCIG_CODE")){
                                    pickCode = object.get("BD_PCIG_CODE").toString().replace("\"", "");
                                }
                                String scanBNum = "";
                                if (object.has("BD_SCAN_BNUM")) {
                                    scanBNum = object.get("BD_SCAN_BNUM").toString().replace("\"", "");
                                }
                                String scanNum = "";
                                if (object.has("BD_SCAN_NUM")){
                                    scanNum = object.get("BD_SCAN_NUM").toString().replace("\"", "");
                                }
                                List<ReturninboundBarcode> barcodes = LitePal.where("UUID = ? and pcigcode = ? and isSubmit = 0", uuid, pickCode).find(ReturninboundBarcode.class);
                                int size = barcodes.size();
                                int mscanQty;
                                if (TextUtils.isEmpty(scanNum)){
                                    mscanQty = 0 + size;
                                }else {
                                    mscanQty = Integer.valueOf(scanNum) + size;
                                }
                                ReturninboundDetail salesFactoryDetail = new ReturninboundDetail(pickName, billPNum, billBNum, uuid, pickCode, scanBNum, String.valueOf(mscanQty));
                                salesFactoryDetails.add(salesFactoryDetail);
                            }
                            LitePal.deleteAll(ReturninboundBarcode.class);
                            LitePal.saveAll(salesFactoryDetails);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        JsonArray jsonArray = jsonObject.getAsJsonArray("SELL_BB_DATA");
                        try {
                            for (int i = 0; i < jsonArray.size(); i++){
                                JsonObject object = jsonArray.get(i).getAsJsonObject();
                                String BB_CONTRACT_NO = "";
                                if (object.has("BB_CONTRACT_NO")){
                                    BB_CONTRACT_NO = object.get("BB_CONTRACT_NO").toString().replace("\"", "");
                                }
                                String BB_BT_CODE = object.get("BB_BT_CODE").toString().replace("\"", "");
                                String BB_TICKET_NO = object.get("BB_TICKET_NO").toString().replace("\"", "");
                                String BB_WS_CODE = object.get("BB_WS_CODE").toString().replace("\"", "");
                                String BB_RELATE_CONTRACT_NO = "";
                                if (object.has("BB_RELATE_CONTRACT_NO")){
                                    BB_RELATE_CONTRACT_NO = object.get("BB_RELATE_CONTRACT_NO").toString().replace("\"", "");
                                }
                                String B_NAME = null;
                                if (object.has("B_NAME")){
                                    B_NAME = object.get("B_NAME").toString().replace("\"", "");
                                }
                                String BB_INPUT_DATE = object.get("BB_INPUT_DATE").toString().replace("\"", "");
                                String BB_UUID = object.get("BB_UUID").toString().replace("\"", "");
                                String BB_TOTAL_ALL_NUM1 = "0";
                                if (object.has("BB_TOTAL_ALL_NUM1")){
                                    BB_TOTAL_ALL_NUM1 = object.get("BB_TOTAL_ALL_NUM1").toString().replace("\"", "");
                                }
                                int total_scannum = 0;
                            /*if (object.has("BB_TOTAL_SCAN_NUM")){
                                BB_TOTAL_SCAN_NUM = object.get("BB_TOTAL_SCAN_NUM").toString().replace("\"", "");
                            }*/
                                List<ReturninboundDetail> details = LitePal.where("BD_BB_UUID = ?", BB_UUID).find(ReturninboundDetail.class);
                                for (int j = 0; j < details.size(); j++){
                                    String bd_total_scaned = details.get(j).getBD_SCAN_NUM();
                                    int scannum = Integer.parseInt(bd_total_scaned);
                                    total_scannum += scannum;
                                }
                                String BB_TOTAL_SCAN_NUM = String.valueOf(total_scannum);
                                String BB_FLOW_NAME = "";
                                if (object.has("BB_FLOW_NAME")){
                                    BB_FLOW_NAME = object.get("BB_FLOW_NAME").toString().replace("\"", "");
                                }
                                String BB_TOTAL_PNUM = "";
                                if (object.has("BB_TOTAL_PNUM")){
                                    BB_TOTAL_PNUM = object.get("BB_TOTAL_PNUM").toString().replace("\"", "");
                                }
                                String BB_STATE = object.get("BB_STATE").toString().replace("\"", "");
                                ReturninboundOrderInfo orderInfo = new ReturninboundOrderInfo(BB_UUID, BB_CONTRACT_NO, BB_BT_CODE, BB_TICKET_NO, BB_WS_CODE,
                                        BB_RELATE_CONTRACT_NO, B_NAME, BB_INPUT_DATE, BB_TOTAL_ALL_NUM1, BB_TOTAL_SCAN_NUM, BB_TOTAL_PNUM, BB_FLOW_NAME, BB_STATE);
                                orderInfos.add(orderInfo);
                            }
                            LitePal.deleteAll(ReturninboundOrderInfo.class);
                            LitePal.saveAll(orderInfos);
                            data.setValue(orderInfos);
                        }catch (Exception e){
                            e.printStackTrace();
                            data.setValue(null);
                        }
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        data.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return data;
    }
}