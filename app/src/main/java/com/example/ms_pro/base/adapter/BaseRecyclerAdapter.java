package com.example.ms_pro.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    public RecyclerView recyclerView;
    public Context mContext;
    public List<T> list = new ArrayList<>();
    public final LayoutInflater layoutInflater;

    public OnLongClickListener onLongClickListener;
    public OnItemClickListener onItemClickListener;
    public OnItemClickListener onItem2ClickListener;
    public OnItemClickListener onItem3ClickListener;
    public OnItemRemoveListener onItemRemoveListener;
    public OnClickItemAdapterListener<T> onClickItemAdapterListener;
    public OnClickItemListListener<T> onClickItemListListener;
    public OnFirstItemClickListener onFirstItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public void setOnClickItemAdapterListener(OnClickItemAdapterListener<T> onClickItemAdapterListener) {
        this.onClickItemAdapterListener = onClickItemAdapterListener;
    }

    public void setOnItem2ClickListener(OnItemClickListener onItem2ClickListener) {
        this.onItem2ClickListener = onItem2ClickListener;
    }

    public void setOnItem3ClickListener(OnItemClickListener onItem3ClickListener) {
        this.onItem3ClickListener = onItem3ClickListener;
    }

    public void setOnClickItemListListener(OnClickItemListListener<T> onClickItemListListener) {
        this.onClickItemListListener = onClickItemListListener;
    }

    public void setOnFirstItemClickListener(OnFirstItemClickListener onFirstItemClickListener) {
        this.onFirstItemClickListener = onFirstItemClickListener;
    }

    public void setOnItemRemoveListener(OnItemRemoveListener onItemRemoveListener) {
        this.onItemRemoveListener = onItemRemoveListener;
    }

    public BaseRecyclerAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.list = list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(int position, T item) {
        try {
            list.set(position, item);
            this.notifyItemChanged(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setData(int position, T item, boolean animation) {
        list.set(position, item);
        if (!animation) {
            this.notifyDataSetChanged();
        } else {
            this.notifyItemChanged(position);
        }
    }

    public void setData(T item) {
        this.notifyDataSetChanged();
    }

    public void clear() {
        try {
            list.clear();
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(int position) {
        try {
            list.remove(position);
            this.notifyItemRemoved(position);
            this.notifyItemRangeChanged(position, list.size());
//            this.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(int pos, T item) {
        try {
            list.add(pos, item);
            notifyItemInserted(pos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(T item) {
        try {
            list.add(item);
            notifyItemInserted(list.size() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addAll(List<T> listItems) {
        try {
            list.addAll(listItems);
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(List<T> listItems) {
        for (T tmp : listItems) {
            add(tmp);
        }
    }

    public void swapData(List<T> listItems) {
        if (getDiffCallback(listItems) != null) {
            final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(getDiffCallback(listItems));
            this.list.clear();
            this.list.addAll(listItems);
            diffResult.dispatchUpdatesTo(this);
        } else {
            clear();
            add(listItems);
        }
    }

    public DiffUtil.Callback getDiffCallback(List<T> newList) {
        return null;
    }

    public T getItembyPostion(int position) {
        if (position >= 0 && position < getItemCount()) {
            return list.get(position);
        } else {
            return null;
        }
    }

    public abstract void onBindViewHolder(VH holder, final int position);

    public boolean contain(T item) {
        try {
            return list.contains(item);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onViewRecycled(VH holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnClickItemAdapterListener<T> {
        void onItemClick(int pos, T item);

    }

    public interface OnClickItemListListener<T> {
        void onItemClick(int pos, List<T> list);
    }

    public interface OnLongClickListener {
        void onItemLongClick(int pos);
    }

    public interface OnFirstItemClickListener {
        void onFirstItemClick();
    }

    public interface OnItemRemoveListener<T> {
        void onItemRemove(T item);
    }

}