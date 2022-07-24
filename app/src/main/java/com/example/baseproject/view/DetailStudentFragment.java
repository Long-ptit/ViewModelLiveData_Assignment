package com.example.baseproject.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baseproject.R;
import com.example.baseproject.databinding.FragmentAddStudentBinding;
import com.example.baseproject.databinding.FragmentDetailStudentBinding;
import com.example.baseproject.room.Student;
import com.example.baseproject.viewmodel.StudentHomeViewModel;

public class DetailStudentFragment extends Fragment {

    private StudentHomeViewModel mStudentHomeViewModel;
    private FragmentDetailStudentBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentDetailStudentBinding.inflate(inflater, container, false);
        mStudentHomeViewModel = new ViewModelProvider(requireActivity()).get(StudentHomeViewModel.class);
        mStudentHomeViewModel.getCurrentStudent().observe(requireActivity(), new Observer<Student>() {
            @Override
            public void onChanged(Student student) {
                mBinding.tvName.setText(student.getName());
                mBinding.tvAge.setText("Age: " + student.getAge());
            }
        });
        return mBinding.getRoot();
    }
}