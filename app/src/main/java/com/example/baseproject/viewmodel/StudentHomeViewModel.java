package com.example.baseproject.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.example.baseproject.room.Student;
import com.example.baseproject.room.StudentRepository;

import java.util.List;

public class StudentHomeViewModel extends AndroidViewModel {

    private StudentRepository mStudentRepository;
    private LiveData<List<Student>> mListStudentLiveData;
    private MutableLiveData<Student> mCurrentStudentLiveData = new MutableLiveData<>(new Student());
    private MutableLiveData<String> mNameLiveData = new MutableLiveData<>();
    private MutableLiveData<String> mAgeLiveData = new MutableLiveData<>();
    private MutableLiveData<Student> mEditStudent = new MutableLiveData<>(new Student());

    public MutableLiveData<Student> getEditStudent() {
        return mEditStudent;
    }

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
//        if (mEditStudent.getValue().getAge() <= 0){
//            Toast.makeText(getApplication(), "Nhap sai r", Toast.LENGTH_SHORT).show();
//            return;
//            Navigation.findNavController().navigateUp();
//        }
        mStudentRepository.insertStudent(mEditStudent.getValue());
        mEditStudent.postValue(new Student());
    }
}
