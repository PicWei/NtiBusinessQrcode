package com.nti.module_device.activity;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.JsonObject;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.nti.lib_common.activity.BaseActivity;
import com.nti.lib_common.bean.DataResult;
import com.nti.lib_common.constants.ARouterPath;
import com.nti.lib_common.utils.DeviceUtils;
import com.nti.module_device.R;
import com.nti.module_device.bean.PdaRegisterParam;
import com.nti.module_device.bean.PdaRegisterParamer;
import com.nti.module_device.databinding.ActivityDeviceBinding;
import com.nti.module_device.viewmodel.DeviceViewModel;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;


@Route(path = ARouterPath.DEVICE_PATH)
public class DeviceActivity extends BaseActivity {

    @Autowired
    public boolean flag;

    private ActivityDeviceBinding binding;
    private DeviceViewModel viewModel;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String SYSTEM_SERVICE_TYPE = "US_CORP_BILL_BASE";
    private String[] spinner_item = new String[]{"商业到货入库", "商业退货出库", "商业调剂出库", "商业调剂退货入库",
                                                 "商业移库出库", "商业移库入库", "商业分栋领用出库",
                                                 "商业分栋领用退回", "商业销售退货入库","卷烟商业损溢"
                                                 };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_device);
        sp = getSharedPreferences("PDA_ACCOUNT", 0);
        editor = sp.edit();
        String devidename = sp.getString("pdaname", "");

        ARouter.getInstance().inject(this);
        viewModel = new ViewModelProvider(this).get(DeviceViewModel.class);
        if (flag){
            binding.registeType.setVisibility(View.GONE);
            binding.typeLabel.setVisibility(View.GONE);
            binding.typeSpinner.setVisibility(View.GONE);
            binding.deviceNameTv.setVisibility(View.VISIBLE);
            binding.deviceNameTv.setText(devidename);
            binding.deviceNameEt.setVisibility(View.GONE);
            binding.confirmBtn.setVisibility(View.GONE);
        }else {
            binding.registeType.setVisibility(View.VISIBLE);
            binding.typeLabel.setVisibility(View.VISIBLE);
            binding.typeSpinner.setVisibility(View.VISIBLE);
            binding.deviceNameTv.setVisibility(View.GONE);
            binding.deviceNameEt.setVisibility(View.VISIBLE);
            binding.deviceNameEt.setText(devidename);
            binding.confirmBtn.setVisibility(View.VISIBLE);
            binding.typeSpinner.setItems(spinner_item);
            binding.typeSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                @Override
                public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {
                    switch (i){
                        case 0:
                            SYSTEM_SERVICE_TYPE = "US_CORP_BILL_BASE";
                            break;
                        case 1:
                            SYSTEM_SERVICE_TYPE = "US_BACK_BILL_BASE";
                            break;
                        case 2:
                            SYSTEM_SERVICE_TYPE = "US_TRANS_BILL_BASE";
                            break;
                        case 3:
                            SYSTEM_SERVICE_TYPE = "US_TBACK_BILL_BASE";
                            break;
                        case 4:
                            SYSTEM_SERVICE_TYPE = "US_MOVEOUT_BILL_BASE";
                            break;
                        case 5:
                            SYSTEM_SERVICE_TYPE = "US_MOVEIN_BILL_BASE";
                            break;
                        case 6:
                            SYSTEM_SERVICE_TYPE = "US_SORT_BILL_BASE";
                            break;
                        case 7:
                            SYSTEM_SERVICE_TYPE = "US_SBACK_BILL_BASE";
                            break;
                        case 8:
                            SYSTEM_SERVICE_TYPE = "US_RBACK_BILL_BASE";
                            break;
                        case 9:
                            SYSTEM_SERVICE_TYPE = "US_ADJUST_BILL_BASE";
                            break;
                    }
                }
            });
        }
        String deviceModel = DeviceUtils.getModel();
        binding.deviceModelTv.setText(deviceModel);

        String ip = DeviceUtils.getLocalIpAddress(this);
        String mac = DeviceUtils.getNewMac();
        binding.ipAddressTv.setText(ip);
        binding.macAddressTv.setText(mac);
        binding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pdaname = binding.deviceNameEt.getText().toString();
                String deviceId = DeviceUtils.getDevUUID(DeviceActivity.this);
                PdaRegisterParam pdaRegisterParam = new PdaRegisterParam(pdaname, deviceId, deviceModel, SYSTEM_SERVICE_TYPE);
                PdaRegisterParamer paramer = new PdaRegisterParamer(pdaRegisterParam);
                viewModel.register(paramer).observe(DeviceActivity.this, new Observer<DataResult<JsonObject>>() {
                    @Override
                    public void onChanged(DataResult<JsonObject> dataResult) {
                        if (dataResult == null){
                            Toast.makeText(DeviceActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                            return;

                        }

                        int errcode = dataResult.getErrcode();
                        if (errcode == 0){
                            JsonObject jsonObject = dataResult.getT();
                            String code = jsonObject.get("code").toString().replace("\"", "");
                            String message = jsonObject.get("message").toString().replace("\"", "");
                            if (code.equals("0")){
                                editor.putString("pdaname", pdaname);
                                editor.commit();
                                Toast.makeText(DeviceActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(DeviceActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(DeviceActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }


}