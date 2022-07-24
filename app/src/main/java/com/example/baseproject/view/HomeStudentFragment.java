package com.example.baseproject.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baseproject.R;
import com.example.baseproject.databinding.FragmentDetailStudentBinding;
import com.example.baseproject.databinding.FragmentHomeStudentBinding;
import com.example.baseproject.room.Student;
import com.example.baseproject.view.adapter.StudentsAdapter;
import com.example.baseproject.viewmodel.StudentHomeViewModel;

import java.util.List;


public class HomeStudentFragment extends Fragment implements StudentsAdapter.CallBack {

    private static final String TAG = "HomeStudentFragment";
    private StudentHomeViewModel mStudentHomeViewModel;
    private FragmentHomeStudentBinding mBinding;
    private StudentsAdapter mAdapterStudent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentHomeStudentBinding.inflate(inflater, container, false);
        mStudentHomeViewModel = new ViewModelProvider(requireActivity()).get(StudentHomeViewModel.class);
        setUpRecycleView();
        setOnClick();
        setUpObser();
        return mBinding.getRoot();
    }

    private void setUpObser() {
        mStudentHomeViewModel.getListStudents().observe(requireActivity(), students ->
                mAdapterStudent.setListData(mStudentHomeViewModel.getListStudents().getValue()));
    }

    private void setOnClick() {
        mStudentHomeViewModel.getEditStudent().setValue(new Student());
        mBinding.btnAddStudent.setOnClickListener(v -> {
            Navigation
                    .findNavController(mBinding.getRoot())
                    .navigate(R.id.action_homeStudentFragment_to_addStudentFragment);
        });
    }

    private void setUpRecycleView() {
        mAdapterStudent = new StudentsAdapter(requireActivity(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                requireActivity(), LinearLayoutManager.VERTICAL, false);
        mBinding.rcv.setAdapter(mAdapterStudent);
        mBinding.rcv.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onClickStudent(Student student) {
        Log.d(TAG, "onClickStudent: " + student.getName());
        mStudentHomeViewModel.setCurrentData(student);
        Navigation
                .findNavController(mBinding.getRoot())
                .navigate(R.id.action_homeStudentFragment_to_detailStudentFragment);
    }
}