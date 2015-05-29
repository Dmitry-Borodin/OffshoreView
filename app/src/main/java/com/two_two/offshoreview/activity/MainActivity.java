package com.two_two.offshoreview.activity;


import android.os.Bundle;
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


public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    //Tabs


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Fragment fragment = new FragmentOffshoreView();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment).commit();

        //work for Toolbar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //work for navigation drawer
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
    }
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
