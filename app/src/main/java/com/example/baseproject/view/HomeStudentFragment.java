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
import com.example.baseproject.room.Student;
import com.example.baseproject.view.adapter.StudentsAdapter;
import com.example.baseproject.viewmodel.StudentHomeViewModel;

import java.util.List;


public class HomeStudentFragment extends Fragment implements StudentsAdapter.CallBack{

    private static final String TAG = "HomeStudentFragment";
    private StudentHomeViewModel mStudentHomeViewModel;
    private View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_home_student, container, false);
        mStudentHomeViewModel = new ViewModelProvider(requireActivity()).get(StudentHomeViewModel.class);

        RecyclerView recyclerView = mView.findViewById(R.id.rcv);
        StudentsAdapter studentsAdapter = new StudentsAdapter(requireActivity(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(studentsAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        mStudentHomeViewModel.getListStudents().observe(requireActivity(), new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                studentsAdapter.setListData(mStudentHomeViewModel.getListStudents().getValue());
            }
        });
        mView.findViewById(R.id.btn_add_student).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(mView).navigate(R.id.action_homeStudentFragment_to_addStudentFragment);
            }
        });
        return mView;
    }

    @Override
    public void onClickStudent(Student student) {
        Log.d(TAG, "onClickStudent: " + student.getName());
        mStudentHomeViewModel.setCurrentData(student);
        NavController navController = Navigation.findNavController(mView);
        navController.navigate(R.id.action_homeStudentFragment_to_detailStudentFragment );
    }
}