package com.example.baseproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.baseproject.R;
import com.example.baseproject.room.MyDataBase;
import com.example.baseproject.room.Student;
import com.example.baseproject.room.StudentDAO;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "ptit";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		getData();
	}

	private void getData() {
		Thread thread = new Thread(() -> {
			MyDataBase myDataBase = MyDataBase.getInstance(this);
			StudentDAO studentDAO = myDataBase.studentDAO();
			studentDAO.insertStudent(new Student(1, "2", "3"));
		//	Log.d(TAG, "getData: " + );
		});
		thread.start();
	}
}