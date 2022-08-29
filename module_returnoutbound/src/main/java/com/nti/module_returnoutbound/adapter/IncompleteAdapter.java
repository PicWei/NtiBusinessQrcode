package com.nti.module_returnoutbound.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nti.lib_common.bean.ReturnoutboundOrderInfo;
import com.nti.lib_common.bean.ReturnoutboundOrderInfo;
import com.nti.module_returnoutbound.R;
import com.nti.module_returnoutbound.databinding.RoincompleteItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author: weiqiyuan
 * @date: 2022/8/1
 * @describe
 */
public class IncompleteAdapter extends RecyclerView.Adapter<IncompleteAdapter.ViewHolder>{

    private Context context;
    private List<ReturnoutboundOrderInfo> orderInfos;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public IncompleteAdapter(Context context, List<ReturnoutboundOrderInfo> orderInfos) {
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
        RoincompleteItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.roincomplete_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        ReturnoutboundOrderInfo orderInfo = orderInfos.get(position);
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
        RoincompleteItemBinding binding;

        public ViewHolder(RoincompleteItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickListener{
        void onItemClick(ReturnoutboundOrderInfo orderInfo);
    }

}
