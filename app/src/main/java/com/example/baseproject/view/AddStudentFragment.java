package com.example.baseproject.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.baseproject.R;
import com.example.baseproject.room.Student;
import com.example.baseproject.viewmodel.StudentHomeViewModel;


public class AddStudentFragment extends Fragment {

    private StudentHomeViewModel mStudentHomeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_add_student, container, false);
        mStudentHomeViewModel = new ViewModelProvider(requireActivity()).get(StudentHomeViewModel.class);
        EditText edtName, edtAge;
        edtName = view.findViewById(R.id.edt_name);
        edtAge = view.findViewById(R.id.edt_age);
        view.findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.setAge(edtAge.getText().toString());
                student.setName(edtName.getText().toString());
                mStudentHomeViewModel.addStudent(student);
                Navigation.findNavController(view).navigateUp();
            }
        });
        return view;
    }
}