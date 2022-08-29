package com.nti.module_sortingoutbound.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nti.lib_common.bean.SortingoutboundDetail;
import com.nti.lib_common.bean.SortingoutboundOrderInfo;
import com.nti.lib_common.bean.SortingoutboundOrderInfo;
import com.nti.module_sortingoutbound.R;
import com.nti.module_sortingoutbound.databinding.SortingoutbounddetailItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author: weiqiyuan
 * @date: 2022/8/11
 * @describe
 */
public class SortingoutboundDetailAdapter extends RecyclerView.Adapter<SortingoutboundDetailAdapter.ViewHolder>{

    private List<SortingoutboundDetail> details;
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public SortingoutboundDetailAdapter(List<SortingoutboundDetail> details, Context context) {
        this.details = details;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        SortingoutbounddetailItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.sortingoutbounddetail_item, parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        SortingoutboundDetail detail = details.get(position);
        holder.binding.brandName.setText(detail.getBD_PCIG_CODE() + "-" + detail.getBD_PCIG_NAME());
        String bill_pnum = detail.getBD_BILL_PNUM();
        int billPum = Integer.parseInt(bill_pnum);
        String scan_num = detail.getBD_SCAN_NUM();
        int scanNum = Integer.parseInt(scan_num);
        holder.binding.progressbarScaned.setMax(billPum);
        holder.binding.progressbar.setMax(billPum);
        holder.binding.progressTv.setText(scan_num+"/"+bill_pnum);
        if (bill_pnum.equals(scan_num)){
            holder.binding.status.setText("扫描完成");
            holder.binding.status.setTextColor(this.context.getColor(R.color.scaned_txt_bg));
            holder.binding.scanDot.setBackground(this.context.getDrawable(R.drawable.scaned_dot));
            holder.binding.progressbarScaned.setVisibility(View.VISIBLE);
            holder.binding.progressbar.setVisibility(View.GONE);
            holder.binding.progressbarScaned.setProgress(billPum);
        }else {
            holder.binding.status.setText("扫描中");
            holder.binding.progressbar.setProgress(scanNum);
        }

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        SortingoutbounddetailItemBinding binding;

        public ViewHolder(SortingoutbounddetailItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickListener{
        void onItemClick(SortingoutboundDetail detail);
    }
    
}
