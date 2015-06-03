package com.two_two.offshoreview.activity;


import android.content.ComponentName;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.two_two.offshoreview.R;
import com.two_two.offshoreview.fragment.FragmentEmoneyView;
import com.two_two.offshoreview.fragment.FragmentOffshoreView;
import com.two_two.offshoreview.fragment.FragmentVentureView;
import com.two_two.offshoreview.fragment.NavigationDrawerFragment;
import com.two_two.offshoreview.service.MyLoadService;

import me.tatarka.support.job.JobInfo;
import me.tatarka.support.job.JobScheduler;
import me.tatarka.support.os.PersistableBundle;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    //id of our JobSchedulerService
    private static final int JOB_ID = 100;
    //Run the JobSchedulerService every 2 minutes
    private static final long POLL_FREQUENCY = 2880;
    private JobScheduler jobScheduler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Fragment fragment = new FragmentOffshoreView();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment).commit();


        //setupStartService();


        //work for Toolbar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //work for navigation drawer
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
    }
//    private void setupStartService() {
//        jobScheduler = JobScheduler.getInstance(this);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                buildJob();
//            }
//        }, 3000);
//    }
//
//    private void buildJob(){
//        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, new ComponentName(this, MyLoadService.class));
//        builder.setPeriodic(POLL_FREQUENCY)
//                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
//                .setPersisted(true);
//        jobScheduler.schedule(builder.build());
//    }

    public void onSelectDrawerItem(int position){
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentOffshoreView();
                break;
            case 1:
                fragment = new FragmentEmoneyView();
                break;
            case 2:
                fragment = new FragmentVentureView();
            default:
                break;
        }
        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment).commit();
        }
    }
}
