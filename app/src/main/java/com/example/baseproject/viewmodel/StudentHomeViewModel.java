package com.example.baseproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.baseproject.room.Student;
import com.example.baseproject.room.StudentRepository;

import java.util.List;

public class StudentHomeViewModel extends AndroidViewModel {

    private StudentRepository mStudentRepository;
    private LiveData<List<Student>> mListStudentLiveData;
    private MutableLiveData<Student> mCurrentStudentLiveData = new MutableLiveData<>(new Student());
    private MutableLiveData<String> mNameLiveData = new MutableLiveData<>();
    private MutableLiveData<String> mAgeLiveData = new MutableLiveData<>();

    public MutableLiveData<String> getNameLiveData() {
        return mNameLiveData;
    }

    public MutableLiveData<String> getAgeLiveData() {
        return mAgeLiveData;
    }

    public LiveData<Student> getCurrentStudent() {
        return mCurrentStudentLiveData;
    }


    public void setCurrentData(Student student) {
        mCurrentStudentLiveData.postValue(student);
    }

    public StudentHomeViewModel(@NonNull Application application) {
        super(application);

        mStudentRepository = new StudentRepository(application);
        mListStudentLiveData = mStudentRepository.getAllStudents();
    }

    public LiveData<List<Student>> getListStudents() {
        return mListStudentLiveData;
    }



    public void addStudent() {
        Student student = new Student();
        student.setAge(Integer.valueOf(mAgeLiveData.getValue()));
        student.setName(mNameLiveData.getValue());
        mStudentRepository.insertStudent(student);
    }
}
