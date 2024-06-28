package com.example.clockeys.Management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clockeys.R;
import com.example.clockeys.Users.Employee;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;

public class ProfileInputActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextInputEditText editText;
    private TextInputLayout inputLayout;
    private TextView description;
    private Employee employee;
    private Intent intent,updatedIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_input);

        intent = getIntent();
        employee = intent.getSerializableExtra("employee", Employee.class);

        updatedIntent = new Intent();


        bindViews();
        setViews();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED,updatedIntent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_edit_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.confirmButton){
            String info = editText.getText().toString();
            if (intent.getStringExtra("hint").equals(getString(R.string.name))){
                employee.setName(info);
            } else if (intent.getStringExtra("hint").equals(getString(R.string.bio))) {
                employee.setBio(info);
            }


        }
        updatedIntent.putExtra("employee",(Serializable) employee);
        setResult(RESULT_OK,updatedIntent);

        finish();
        return Boolean.TRUE;
    }


    private void bindViews(){
        toolbar = findViewById(R.id.profileInputToolbar);
        setSupportActionBar(toolbar);

        editText = findViewById(R.id.profileInputEditText);
        inputLayout = findViewById(R.id.profileInputInputLayout);
        description = findViewById(R.id.profileInputDescription);
    }

    private void setViews(){
        toolbar.setTitle(intent.getStringExtra("hint"));
        editText.setText(intent.getStringExtra("text"));
        inputLayout.setHint(intent.getStringExtra("hint"));
        inputLayout.setCounterEnabled(Boolean.TRUE);
        inputLayout.setCounterMaxLength(intent.getIntExtra("length",0));
        description.setText(intent.getStringExtra("description"));
    }
}