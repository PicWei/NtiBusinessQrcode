package com.nti.module_returninbound.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nti.lib_common.bean.ReturninboundOrderInfo;
import com.nti.lib_common.bean.ReturninboundOrderInfo;
import com.nti.module_returninbound.R;
import com.nti.module_returninbound.databinding.RiincompleteItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author: weiqiyuan
 * @date: 2022/8/1
 * @describe
 */
public class IncompleteAdapter extends RecyclerView.Adapter<IncompleteAdapter.ViewHolder>{

    private Context context;
    private List<ReturninboundOrderInfo> orderInfos;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public IncompleteAdapter(Context context, List<ReturninboundOrderInfo> orderInfos) {
        this.context = context;
        this.orderInfos = orderInfos;
        inflater = LayoutInflater.from(this.context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        RiincompleteItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.riincomplete_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        ReturninboundOrderInfo orderInfo = orderInfos.get(position);
        holder.binding.setBean(orderInfo);
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

    class ViewHolder extends RecyclerView.ViewHolder{
        RiincompleteItemBinding binding;

        public ViewHolder(RiincompleteItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickListener{
        void onItemClick(ReturninboundOrderInfo orderInfo);
    }

}
