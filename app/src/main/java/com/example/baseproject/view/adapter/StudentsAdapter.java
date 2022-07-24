package com.example.baseproject.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseproject.databinding.ItemStudentBinding;
import com.example.baseproject.room.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.ViewHolder> {
    private List<Student> mListData;
    private final Context mContext;
    private CallBack mListener;

    @SuppressLint("NotifyDataSetChanged")
    public void setListData(List<Student> mListData) {
        this.mListData = mListData;
        notifyDataSetChanged();
    }


    public StudentsAdapter(Context context, CallBack listener) {
        this.mContext = context;
        this.mListener = listener;
        mListData = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemStudentBinding itemView =
                ItemStudentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Student student = mListData.get(position);
        holder.mBinding.tvName.setText(student.getName());
        holder.mBinding.tvAge.setText("Age: " + student.getAge());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickStudent(student);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemStudentBinding mBinding;

        public ViewHolder(@NonNull ItemStudentBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    public interface CallBack {
        void onClickStudent(Student student);
    }

}

