package com.example.clockeys.Management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.clockeys.Adapters.NotificationAdapter;
import com.example.clockeys.Adapters.PunchAdapter;
import com.example.clockeys.Models.Company;
import com.example.clockeys.Notifications.AnnouncementNotification;
import com.example.clockeys.Notifications.ImageNotification;
import com.example.clockeys.Notifications.Notification;
import com.example.clockeys.R;
import com.example.clockeys.Time.Punch;
import com.example.clockeys.Time.Timecard;
import com.example.clockeys.Users.Employee;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    private NotificationAdapter notificationAdapter;
    private RecyclerView notificationRecyclerView;
    private List<Notification> notifications;
    private Employee employee;
    private Company company;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        employee = new Employee(1092,"Zion Keys",new Date(),new Timecard(new ArrayList<Punch>()),new Date(),"Worker","bio","address");
        ArrayList<String> urls = new ArrayList<>();
        urls.add("https://www.geeksforgeeks.org/wp-content/uploads/gfg_200X200-1.png");
        urls.add("https://th.bing.com/th/id/R.07c1ac85b2efd015deb654898d9db27f?rik=K%2fbd%2boTaPkCfSA&pid=ImgRaw&r=0");
        urls.add("https://th.bing.com/th/id/OIP.2jNruqshISvBDRXat-ag0QAAAA?rs=1&pid=ImgDetMain");

        notifications = new ArrayList<>();
        Notification notification1 = new ImageNotification(90,901,"Welcome to Mcdonalds!",new Date(),9,10,"Ethan Keys",new Date(),"Today is your first shift at our new location! Welcome to the Mcondalds family all!",urls);
        notifications.add(notification1);
        Notification notification2 = new AnnouncementNotification(102,3930,"New Hire: Welcome Isabella",new Date(),2,901,"Alexis Johnson",new Date(),"Isabella is our newest addition to the team, Please welcome her with open arms and show her our Mcdonalds Spirit!");
        notifications.add(notification2);

        setContentView(R.layout.activity_notification);

        notificationRecyclerView = findViewById(R.id.notificationRecyclerView);

        notificationAdapter = new NotificationAdapter(this,notifications);
        notificationRecyclerView.setAdapter(notificationAdapter);

        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        bindViews();
    }

    @Override
    protected void  onNewIntent(Intent intent){
        super.onNewIntent(intent);
        setIntent(intent);
        notifications.add(intent.getSerializableExtra("notification", Notification.class));
        notificationAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_notification_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        int id = item.getItemId();
        if (id == R.id.notificationButton){
            Intent intent = new Intent(this,AnnouncementNotificationActivity.class);
            intent.putExtra("companyId",123);
            intent.putExtra("employee",employee);
            startActivity(intent);
            return Boolean.TRUE;
        }
        return super.onOptionsItemSelected(item);
    }

    public void bindViews(){
        toolbar = findViewById(R.id.notificationToolbar);
        setSupportActionBar(toolbar);
    }
}