package com.example.studentmanagement.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentmanagement.Util.DBHelper;
import com.example.studentmanagement.Fragment.DashboardFragment;
import com.example.studentmanagement.Fragment.HomeFragment;
import com.example.studentmanagement.Fragment.PasswordFragment;
import com.example.studentmanagement.Fragment.ProfileFragment;
import com.example.studentmanagement.R;
import com.example.studentmanagement.Util.ShaaredPrefereanceManager;
import com.example.studentmanagement.Model.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private SharedPreferences sharedPreferences;
    private TextView headerTV,profilePhone;
    private DBHelper dbHelper;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        loadHeaderData();
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new HomeFragment()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.homeMenu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new HomeFragment()).commit();
                        return true;
                    default:
                        return  false;

                }
            }
        });
    }

    private void loadHeaderData() {
        dbHelper = new DBHelper(getApplicationContext());
        ShaaredPrefereanceManager spm =ShaaredPrefereanceManager.getInstance(getApplicationContext());
        String loginEmail = spm.getLoggedEmail();
        UserModel userModel = dbHelper.getSingleUserInfo(loginEmail);
        headerTV.setText(userModel.getName());
        profilePhone.setText(userModel.getMobile());
    }

    private void init() {
        navigationView = findViewById(R.id.drawer_navigation);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);

        //drawer navigation get her
        View headerView = navigationView.getHeaderView(0);
        //header menu find here
        headerTV=headerView.findViewById(R.id.profileName);
        profilePhone=headerView.findViewById(R.id.profilePhone);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dashboardMenu:
                toolbar.setTitle("Home");
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new DashboardFragment()).commit();
                //DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.profileMenu:
                toolbar.setTitle("Profile");
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new ProfileFragment()).commit();
                //DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.passwordMenu:
                toolbar.setTitle("Password Change");
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new PasswordFragment()).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.aboutMenu:
                toolbar.setTitle("About Us");
                Toast.makeText(this, "Registration Menu", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.logout:
               ShaaredPrefereanceManager shm=new ShaaredPrefereanceManager(getApplicationContext());
               shm.setLoginStatus("false");
               startActivity(new Intent(getApplicationContext(),LoginActivity.class));
               finish();
                return true;
            case R.id.homeMenu:
                Toast.makeText(this, "Home Menu", Toast.LENGTH_SHORT).show();

                return true;
            default:
        }

        //if(item.getItemId() == R.id.profileMenu){
            //Toast.makeText(this, "okay", Toast.LENGTH_SHORT).show();
            //getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new ProfileFragment()).commit();

            //DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
            //drawerLayout.closeDrawer(GravityCompat.START);
            //return true;
        //}
        return false;
    }
}