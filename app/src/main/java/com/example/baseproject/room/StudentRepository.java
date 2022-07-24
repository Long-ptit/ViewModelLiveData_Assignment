package com.example.baseproject.room;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentRepository {
    private StudentDAO mStudentDAO;
    private LiveData<List<Student>> listLiveData;
    private HandlerThread mHandlerThread;
    private Handler mHandler;

    public StudentRepository(Application application) {
        MyDataBase myDataBase = MyDataBase.getInstance(application);
        mStudentDAO = myDataBase.studentDAO();

        mHandlerThread = new HandlerThread("IO");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper());
    }

    public LiveData<List<Student>> getAllStudents() {
        return mStudentDAO.getAllStudents();
    }

    public void insertStudent(Student student) {
        mHandler.post(() -> mStudentDAO.insertStudent(student));
    }
}
