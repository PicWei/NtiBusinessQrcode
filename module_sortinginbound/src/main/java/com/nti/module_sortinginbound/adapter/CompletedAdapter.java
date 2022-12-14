package com.nti.module_sortinginbound.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nti.lib_common.bean.SortinginboundOrderInfo;
import com.nti.lib_common.bean.SortinginboundOrderInfo;
import com.nti.module_sortinginbound.R;
import com.nti.module_sortinginbound.databinding.SicompletedItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author: weiqiyuan
 * @date: 2022/8/1
 * @describe
 */
public class CompletedAdapter extends RecyclerView.Adapter<CompletedAdapter.ViewHolder>{

    private Context context;
    private List<SortinginboundOrderInfo> orderInfos;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    private onCheckListener listener = null;

    public CompletedAdapter(Context context, List<SortinginboundOrderInfo> orderInfos) {
        this.context = context;
        this.orderInfos = orderInfos;
        inflater = LayoutInflater.from(this.context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnCheckListener(onCheckListener listener){
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        SicompletedItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.sicompleted_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        SortinginboundOrderInfo orderInfo = orderInfos.get(position);
        holder.binding.setBean(orderInfo);
        String BB_TOTAL_PNUM = orderInfo.getBB_TOTAL_PNUM();
        String BB_TOTAL_SCAN_NUM = orderInfo.getBB_TOTAL_SCAN_NUM();
        holder.binding.progressbar.setMax(Integer.parseInt(BB_TOTAL_PNUM));
        holder.binding.progressbar.setProgress(Integer.parseInt(BB_TOTAL_SCAN_NUM));
        holder.binding.progressbarTv.setText(BB_TOTAL_SCAN_NUM + "/" + BB_TOTAL_PNUM);
        if (orderInfo.isIspressed()){
            holder.binding.checkbox.setImageResource(R.mipmap.radion_pressed);
        }else {
            holder.binding.checkbox.setImageResource(R.mipmap.radio_normal);
        }
        holder.binding.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (orderInfo.isIspressed()){
                    orderInfo.setIspressed(false);
                    holder.binding.checkbox.setImageResource(R.mipmap.radio_normal);
                }else {
                    orderInfo.setIspressed(true);
                    holder.binding.checkbox.setImageResource(R.mipmap.radion_pressed);
                }
                if (listener != null){
                    listener.onCheckChange(orderInfos);
                }
            }
        });
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
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

    class ViewHolder extends RecyclerView.ViewHolder{
        SicompletedItemBinding binding;

        public ViewHolder(SicompletedItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickListener{
        void onItemClick(SortinginboundOrderInfo orderInfo);
    }

    public interface onCheckListener{
        void onCheckChange(List<SortinginboundOrderInfo> orderInfos);
    }
}
