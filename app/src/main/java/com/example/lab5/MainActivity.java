package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab5.Database.DBHandler;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button selectAllBtn,addBtn,signIn,delBtn,updateBtn;
    String TAG="MainActivity";
    DBHandler dh=new DBHandler(this);
    public EditText name,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectAllBtn=findViewById(R.id.select_all_btn);
        addBtn=findViewById(R.id.add_btn);
        signIn=findViewById(R.id.signin);
        delBtn=findViewById(R.id.del_btn);
        updateBtn=findViewById(R.id.update_btn);

        name=findViewById(R.id.name_et);
        password=findViewById(R.id.password_et);

        selectAllBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        signIn.setOnClickListener(this);
        delBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.select_all_btn:
                Log.d(TAG,"get all clicked");
                List<String> users=dh.ReadAllInfo();
                for(String name: users){
                    Log.d(TAG,"username is "+name);
                }
            break;
            case R.id.add_btn:
                dh.addInfo(name.getText().toString(),password.getText().toString());
                Log.d(TAG,"add btn clicked");
                break;
            case R.id.signin:
                Boolean status=dh.readInfo(name.getText().toString(),password.getText().toString());
               // Log.d(TAG,status.toString());
                signIn(status);
                break;
            case R.id.update_btn:
                dh.updateInfo(name.getText().toString(),password.getText().toString());

                break;
            case R.id.del_btn:
                 dh.deleteInfo(name.getText().toString());
                break;
        }
    }

    public void signIn(Boolean status){
       Context context = getApplicationContext();
        CharSequence text="User doesn't exist";
       if(status)
           text = "User exist!";


        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }
}
