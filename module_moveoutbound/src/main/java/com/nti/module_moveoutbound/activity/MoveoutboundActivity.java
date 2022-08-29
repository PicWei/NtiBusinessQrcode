package com.nti.module_moveoutbound.activity;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;
import com.nti.lib_common.activity.BaseActivity;
import com.nti.lib_common.bean.MoveoutboundOrderInfo;
import com.nti.lib_common.bean.MessageEvent;
import com.nti.lib_common.bean.MoveoutboundOrderInfo;
import com.nti.lib_common.bean.Paramer;
import com.nti.lib_common.bean.Params;
import com.nti.lib_common.constants.ARouterPath;
import com.nti.lib_common.constants.BusinessType;
import com.nti.lib_common.utils.DeviceUtils;
import com.nti.module_moveoutbound.R;
import com.nti.module_moveoutbound.databinding.ActivityMoveoutboundBinding;
import com.nti.module_moveoutbound.fragment.CompletedFragment;
import com.nti.module_moveoutbound.fragment.IncompleteFragment;
import com.nti.module_moveoutbound.fragment.OngoingFragment;
import com.nti.module_moveoutbound.viewmodel.MoveoutboundVM;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.LitePal;

import java.util.List;

@Route(path = ARouterPath.MOVEOUTBOUND_PATH)
public class MoveoutboundActivity extends BaseActivity implements View.OnClickListener{

    private ActivityMoveoutboundBinding binding;
    private MoveoutboundVM viewModel;
    private LoadingPopupView loadingPopup;
    private int current = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_moveoutbound);
        EventBus.getDefault().register(this);
        binding.incompleteCl.setOnClickListener(this);
        binding.ongoingCl.setOnClickListener(this);
        binding.completedCl.setOnClickListener(this);
        binding.titleBar.findViewById(R.id.right_cl).setOnClickListener(this);
        viewModel = new ViewModelProvider(this).get(MoveoutboundVM.class);
        String deviceId = DeviceUtils.getDevUUID(this);
        String SYSTEM_SERVICE_TYPE = "US_MOVEOUT_BILL_BASE";
        Params params = new Params(deviceId, SYSTEM_SERVICE_TYPE);
        Paramer paramer = new Paramer(params);
        if (loadingPopup == null) {
            loadingPopup = (LoadingPopupView)new XPopup.Builder(this)
                    .dismissOnBackPressed(true)
                    .isLightNavigationBar(true)
                    .asLoading("加载中...")
                    .show();
        }else {
            loadingPopup.show();
        }
        viewModel.PDA_H(paramer).observe(this, new Observer<List<MoveoutboundOrderInfo>>() {
            @Override
            public void onChanged(List<MoveoutboundOrderInfo> moveoutboundOrderInfos) {
                loadingPopup.dismiss();
                if (moveoutboundOrderInfos == null){
                    Toast.makeText(MoveoutboundActivity.this, "数据为空", Toast.LENGTH_SHORT).show();
                }else {
                    binding.incompleteCl.performClick();
                    List<MoveoutboundOrderInfo> orderInfos = LitePal.where("BB_STATE = ?", "4").find(MoveoutboundOrderInfo.class);
                    List<MoveoutboundOrderInfo> orderInfos2 = LitePal.where("BB_STATE = ?", "1").find(MoveoutboundOrderInfo.class);
                    List<MoveoutboundOrderInfo> orderInfos3 = LitePal.where("BB_STATE = ?", "3").find(MoveoutboundOrderInfo.class);
                    int incompleteNum = orderInfos.size();
                    int ongoingNum = orderInfos2.size();
                    int completeNUm = orderInfos3.size();
                    binding.incompleteNum.setText(incompleteNum+"");
                    binding.ongoingNum.setText(ongoingNum+"");
                    binding.completedNum.setText(completeNUm+"");
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<MoveoutboundOrderInfo> orderInfos = LitePal.where("BB_STATE = ?", "4").find(MoveoutboundOrderInfo.class);
        List<MoveoutboundOrderInfo> orderInfos2 = LitePal.where("BB_STATE = ?", "1").find(MoveoutboundOrderInfo.class);
        List<MoveoutboundOrderInfo> orderInfos3 = LitePal.where("BB_STATE = ?", "3").find(MoveoutboundOrderInfo.class);
        int incompleteNum = orderInfos.size();
        int ongoingNum = orderInfos2.size();
        int completeNUm = orderInfos3.size();
        binding.incompleteNum.setText(incompleteNum+"");
        binding.ongoingNum.setText(ongoingNum+"");
        binding.completedNum.setText(completeNUm+"");
        if (current == 1){
            binding.incompleteCl.performClick();
        }
        if (current == 2){
            binding.ongoingCl.performClick();
        }
        if (current == 3){
            binding.completedCl.performClick();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.incomplete_cl){
            current = 1;
            swithTab(R.id.incomplete_cl);
            Fragment incompleteFragment = IncompleteFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, incompleteFragment).commit();
        }else if (view.getId() == R.id.ongoing_cl){
            current = 2;
            swithTab(R.id.ongoing_cl);
            Fragment ongoingFragment = OngoingFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, ongoingFragment).commit();
        }else if (view.getId() == R.id.completed_cl){
            current = 3;
            swithTab(R.id.completed_cl);
            Fragment completedFragment = CompletedFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, completedFragment).commit();
        }else if ((view.getId()) == R.id.right_cl){
            String deviceId = DeviceUtils.getDevUUID(this);
            String SYSTEM_SERVICE_TYPE = "US_MOVEOUT_BILL_BASE";
            Params params = new Params(deviceId, SYSTEM_SERVICE_TYPE);
            Paramer paramer = new Paramer(params);
            if (loadingPopup == null) {
                loadingPopup = (LoadingPopupView)new XPopup.Builder(this)
                        .dismissOnBackPressed(true)
                        .isLightNavigationBar(true)
                        .asLoading("加载中...")
                        .show();
            }else {
                loadingPopup.show();
            }
            viewModel.PDA_H(paramer).observe(this, new Observer<List<MoveoutboundOrderInfo>>() {
                @Override
                public void onChanged(List<MoveoutboundOrderInfo> salesFactoryOrderInfos) {
                    loadingPopup.dismiss();
                    if (salesFactoryOrderInfos == null){
                        Toast.makeText(MoveoutboundActivity.this, "数据为空", Toast.LENGTH_SHORT).show();
                    }else {
                        binding.incompleteCl.performClick();
                        List<MoveoutboundOrderInfo> orderInfos = LitePal.where("BB_STATE = ?", "4").find(MoveoutboundOrderInfo.class);
                        List<MoveoutboundOrderInfo> orderInfos2 = LitePal.where("BB_STATE = ?", "1").find(MoveoutboundOrderInfo.class);
                        List<MoveoutboundOrderInfo> orderInfos3 = LitePal.where("BB_STATE = ?", "3").find(MoveoutboundOrderInfo.class);
                        int incompleteNum = orderInfos.size();
                        int ongoingNum = orderInfos2.size();
                        int completeNUm = orderInfos3.size();
                        binding.incompleteNum.setText(incompleteNum+"");
                        binding.ongoingNum.setText(ongoingNum+"");
                        binding.completedNum.setText(completeNUm+"");
                    }
                }
            });
        }
    }

    private void swithTab(int tabId){
        if (tabId == R.id.incomplete_cl){
            binding.incompleteTv.setTextColor(getColor(R.color.text_press_bg));
            binding.incompleteNum.setTextColor(getColor(R.color.num_press));
            binding.rrNum01.setBackground(getDrawable(R.drawable.num_press_bg));
            binding.view01.setVisibility(View.VISIBLE);

            binding.ongoingTv.setTextColor(getColor(R.color.text_normal));
            binding.ongoingNum.setTextColor(getColor(R.color.num_normal));
            binding.rrNum02.setBackground(getDrawable(R.drawable.num_normal));
            binding.view02.setVisibility(View.GONE);

            binding.completedTv.setTextColor(getColor(R.color.text_normal));
            binding.completedNum.setTextColor(getColor(R.color.num_normal));
            binding.rrNum03.setBackground(getDrawable(R.drawable.num_normal));
            binding.view03.setVisibility(View.GONE);
        }else if (tabId == R.id.ongoing_cl){
            binding.incompleteTv.setTextColor(getColor(R.color.text_normal));
            binding.incompleteNum.setTextColor(getColor(R.color.num_normal));
            binding.rrNum01.setBackground(getDrawable(R.drawable.num_normal));
            binding.view01.setVisibility(View.GONE);

            binding.ongoingTv.setTextColor(getColor(R.color.text_press_bg));
            binding.ongoingNum.setTextColor(getColor(R.color.num_press));
            binding.rrNum02.setBackground(getDrawable(R.drawable.num_press_bg));
            binding.view02.setVisibility(View.VISIBLE);

            binding.completedTv.setTextColor(getColor(R.color.text_normal));
            binding.completedNum.setTextColor(getColor(R.color.num_normal));
            binding.rrNum03.setBackground(getDrawable(R.drawable.num_normal));
            binding.view03.setVisibility(View.GONE);
        }else if (tabId == R.id.completed_cl){
            binding.incompleteTv.setTextColor(getColor(R.color.text_normal));
            binding.incompleteNum.setTextColor(getColor(R.color.num_normal));
            binding.rrNum01.setBackground(getDrawable(R.drawable.num_normal));
            binding.view01.setVisibility(View.GONE);

            binding.ongoingTv.setTextColor(getColor(R.color.text_normal));
            binding.ongoingNum.setTextColor(getColor(R.color.num_normal));
            binding.rrNum02.setBackground(getDrawable(R.drawable.num_normal));
            binding.view02.setVisibility(View.GONE);

            binding.completedTv.setTextColor(getColor(R.color.text_press_bg));
            binding.completedNum.setTextColor(getColor(R.color.num_press));
            binding.rrNum03.setBackground(getDrawable(R.drawable.num_press_bg));
            binding.view03.setVisibility(View.VISIBLE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event){
        switch (event.what){
            case BusinessType.UPDATA_ONGOING:
                List<MoveoutboundOrderInfo> orderInfos2 = LitePal.where("BB_STATE = ? or BB_STATE = ? or BB_STATE = ?", "1", "3", "4").find(MoveoutboundOrderInfo.class);
                int ongoingNum = orderInfos2.size();
                binding.ongoingNum.setText(ongoingNum+"");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}