package com.example.administrator.helloword;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.administrator.helloword.adapter.CustomAdapter;
import com.example.administrator.helloword.data.DBManager;
import com.example.administrator.helloword.model.Student;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtAddress;
    private EditText edtPhoneNumber;
    private EditText edtEmail;
    private Button btnSave;
    private ListView lvStudent;
    private DBManager dbManager;
    private CustomAdapter customAdapter;
    private List<Student> studentList;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DBManager(this);
        initWidget();

        studentList = dbManager.getAllStudent();
        setAdapter();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = createStudent();
                if (student != null) {
                    dbManager.addStudent(student);
                }
                studentList.clear();
                studentList.addAll(dbManager.getAllStudent());
                setAdapter();
            }
        });
    }

    private Student createStudent() {
        String name = edtName.getText().toString();
        String address = edtAddress.getText().toString();
        String phoneNumber = edtPhoneNumber.getText() + "";
        String email = edtEmail.getText().toString();
        Student student = new Student(name, address, phoneNumber, email);
        return student;
    }

    private void initWidget() {
        edtName = findViewById(R.id.edit_name);
        edtAddress = findViewById(R.id.edit_address);
        edtPhoneNumber = findViewById(R.id.edit_email);
        edtEmail = findViewById(R.id.edit_email);
        btnSave = findViewById(R.id.btn_save);
        lvStudent = findViewById(R.id.lv_student);
    }

    public void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new CustomAdapter(this, R.layout.item_list_student, studentList);
        } else {
            customAdapter.notifyDataSetChanged();
            lvStudent.setSelection(customAdapter.getCount()-1);
            // cm
        }

    }
}
