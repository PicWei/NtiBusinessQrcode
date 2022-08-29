package com.nti.module_sortingoutbound.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nti.lib_common.bean.SortingoutboundOrderInfo;
import com.nti.lib_common.bean.SortingoutboundOrderInfo;
import com.nti.module_sortingoutbound.R;
import com.nti.module_sortingoutbound.databinding.SoincompleteItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author: weiqiyuan
 * @date: 2022/8/1
 * @describe
 */
public class IncompleteAdapter extends RecyclerView.Adapter<IncompleteAdapter.ViewHolder>{

    private Context context;
    private List<SortingoutboundOrderInfo> orderInfos;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public IncompleteAdapter(Context context, List<SortingoutboundOrderInfo> orderInfos) {
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
        SoincompleteItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.soincomplete_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        SortingoutboundOrderInfo orderInfo = orderInfos.get(position);
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
        SoincompleteItemBinding binding;

        public ViewHolder(SoincompleteItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickListener{
        void onItemClick(SortingoutboundOrderInfo orderInfo);
    }

}
