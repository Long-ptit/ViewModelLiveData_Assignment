package com.example.baseproject.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.baseproject.R;
import com.example.baseproject.databinding.FragmentAddStudentBinding;
import com.example.baseproject.room.Student;
import com.example.baseproject.viewmodel.StudentHomeViewModel;


public class AddStudentFragment extends Fragment {

    private StudentHomeViewModel mStudentHomeViewModel;
    private FragmentAddStudentBinding mBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentAddStudentBinding.inflate(inflater, container, false);
        mStudentHomeViewModel = new ViewModelProvider(requireActivity()).get(StudentHomeViewModel.class);
        mBinding.setLifecycleOwner(this);
        mBinding.setViewModel(mStudentHomeViewModel);


        mBinding.btnSave.setOnClickListener(v -> {
            mStudentHomeViewModel.addStudent();
        });

        mBinding.btnBack.setOnClickListener(v -> {
            Navigation.findNavController(mBinding.getRoot()).navigateUp();
        });
        return mBinding.getRoot();
    }
}