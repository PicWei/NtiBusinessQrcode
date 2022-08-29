package com.nti.nti_business_qrcode.fragment;

import android.os.Bundle;

import android.view.CollapsibleActionView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gyf.immersionbar.ImmersionBar;
import com.nti.lib_common.constants.ARouterPath;
import com.nti.module_arrivalinbound.activity.ArrivalInboundActivity;
import com.nti.nti_business_qrcode.R;
import com.nti.nti_business_qrcode.databinding.FragmentHomeBinding;

public class HomeFragment extends SimpleImmersionFragment implements View.OnClickListener {

    private FragmentHomeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.rrSydhrk.setOnClickListener(this);
        binding.rrSythck.setOnClickListener(this);
        binding.rrSytjck.setOnClickListener(this);
        binding.rrSytjrk.setOnClickListener(this);
        binding.rrSyykrk.setOnClickListener(this);
        binding.rrSsykck.setOnClickListener(this);
        binding.rrFjlyck.setOnClickListener(this);
        binding.rrFjlyth.setOnClickListener(this);
        binding.rrXsthrk.setOnClickListener(this);
        binding.rrSysy.setOnClickListener(this);
        return root;
    }

    public void immersionBarInit(){
        ImmersionBar.with(this)
                .statusBarColor(R.color.white)
                .fitsSystemWindows(false)
                .autoDarkModeEnable(true)
                .navigationBarColor(R.color.white)
                .init();
    }

    @Override
    public void initImmersionBar() {
        immersionBarInit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rr_sydhrk:
                ARouter.getInstance().build(ARouterPath.ARRIVALINBOUND_PATH)
                        .navigation();
                break;
            case R.id.rr_sythck:
                ARouter.getInstance().build(ARouterPath.RETURNOUTBOUND_PATH)
                        .navigation();
                break;
            case R.id.rr_sytjck:
                ARouter.getInstance().build(ARouterPath.ADJUSTOUTBOUND_PATH)
                        .navigation();
                break;
            case R.id.rr_sytjrk:
                ARouter.getInstance().build(ARouterPath.ADJUSTINBOUND_PATH)
                        .navigation();
                break;
            case R.id.rr_syykrk:
                ARouter.getInstance().build(ARouterPath.MOVEINBOUND_PATH)
                        .navigation();
                break;
            case R.id.rr_ssykck:
                ARouter.getInstance().build(ARouterPath.MOVEOUTBOUND_PATH)
                        .navigation();
                break;
            case R.id.rr_fjlyck:
                ARouter.getInstance().build(ARouterPath.SORTINGOUTBOUND_PATH)
                        .navigation();
                break;
            case R.id.rr_fjlyth:
                ARouter.getInstance().build(ARouterPath.SORTINGINBOUND_PATH)
                        .navigation();
                break;
            case R.id.rr_xsthrk:
                ARouter.getInstance().build(ARouterPath.RETURNINBOUND_PATH)
                        .navigation();
                break;
            case R.id.rr_sysy:
                ARouter.getInstance().build(ARouterPath.LOSS_PATH)
                        .navigation();
                break;
        }
    }
}