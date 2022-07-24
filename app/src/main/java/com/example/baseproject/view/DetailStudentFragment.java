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
import com.example.baseproject.room.Student;
import com.example.baseproject.viewmodel.StudentHomeViewModel;

public class DetailStudentFragment extends Fragment {

    private StudentHomeViewModel mStudentHomeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_student, container, false);

        mStudentHomeViewModel = new ViewModelProvider(requireActivity()).get(StudentHomeViewModel.class);
        TextView textView = view.findViewById(R.id.tv_data);
        mStudentHomeViewModel.getCurrentStudent().observe(requireActivity(), new Observer<Student>() {
            @Override
            public void onChanged(Student student) {
                textView.setText(student.getName());
            }
        });
        return view;
    }
}