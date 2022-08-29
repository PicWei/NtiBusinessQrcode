package com.nti.module_adjustoutbound.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nti.lib_common.bean.AdjustoutboundOrderInfo;
import com.nti.lib_common.bean.ArrivalInboundOrderInfo;
import com.nti.module_adjustoutbound.R;
import com.nti.module_adjustoutbound.databinding.AoongoingItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author: weiqiyuan
 * @date: 2022/8/1
 * @describe
 */
public class OngoingAdapter extends RecyclerView.Adapter<OngoingAdapter.ViewHolder>{

    private Context context;
    private List<AdjustoutboundOrderInfo> orderInfos;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public OngoingAdapter(Context context, List<AdjustoutboundOrderInfo> orderInfos) {
        this.context = context;
        this.orderInfos = orderInfos;
        inflater = LayoutInflater.from(this.context);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        AoongoingItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.aoongoing_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        AdjustoutboundOrderInfo orderInfo = orderInfos.get(position);
        holder.binding.setBean(orderInfo);
        String BB_TOTAL_PNUM = orderInfo.getBB_TOTAL_PNUM();
        String BB_TOTAL_SCAN_NUM = orderInfo.getBB_TOTAL_SCAN_NUM();
        holder.binding.progressbar.setMax(Integer.parseInt(BB_TOTAL_PNUM));
        holder.binding.progressbar.setProgress(Integer.parseInt(BB_TOTAL_SCAN_NUM));
        holder.binding.progressbarTv.setText(BB_TOTAL_SCAN_NUM + "/" + BB_TOTAL_PNUM);
        holder.binding.scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(orderInfo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderInfos.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        AoongoingItemBinding binding;

        public ViewHolder(AoongoingItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickListener{
        void onItemClick(AdjustoutboundOrderInfo orderInfo);
    }
}
