package com.example.gad_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;

    //fragments
    private HoursFragment hoursFragment;
    private SkillFragment skillFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("LeaderBoard");

        viewPager = findViewById(R.id.view_pager);


        toolbar.findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent submitIntent = new Intent(MainActivity.this, SubmitActivity.class);
                startActivity(submitIntent);
            }
        });

        setUpViewPager();
    }

    private void setUpViewPager(){
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        hoursFragment = new HoursFragment();
        skillFragment = new SkillFragment();
        pagerAdapter.addFragment(hoursFragment);
        pagerAdapter.addFragment(skillFragment);

        viewPager.setAdapter(pagerAdapter);


        //set the tab layout and link to the viewpager
        TabLayout tabLayout = findViewById(R.id.tabs_bar);
        tabLayout.setupWithViewPager(viewPager);

        //setting the text in the tab layout
        tabLayout.getTabAt(0).setText(R.string.learning_leaders);
        tabLayout.getTabAt(1).setText(R.string.skill_iq_leaders);

    }


    @Override
    protected void onResume() {
        super.onResume();


    }



}