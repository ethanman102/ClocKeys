package com.example.clockeys.Management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Pair;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.clockeys.R;
import com.google.android.material.datepicker.MaterialDatePicker;

public class TimecardActivity extends AppCompatActivity {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timecard);

        toolbar = findViewById(R.id.timecardToolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_timecard_menu,menu);
       return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        int id = item.getItemId();
        if (id == R.id.dateRangeTimecard){
            Toast.makeText(this, "Date Range clicked", Toast.LENGTH_SHORT).show();
            MaterialDatePicker materialDatePicker = createDateRangePicker();
            materialDatePicker.show(getSupportFragmentManager(),"MATERIAL_DATE_PICKER");
            return Boolean.TRUE;
        }
        return super.onOptionsItemSelected(item);
    }

    private MaterialDatePicker createDateRangePicker(){
        MaterialDatePicker.Builder<Pair<Long, Long>> materialDateBuilder = MaterialDatePicker.Builder.dateRangePicker();
        materialDateBuilder.setTitleText("Punch Date Range");

        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();
        return  materialDatePicker;
    }

}