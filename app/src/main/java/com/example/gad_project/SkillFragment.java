package com.example.gad_project;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SkillFragment extends Fragment {


    private RecyclerView rv_skills;
    private ArrayList<Person> dataList;
    private  MainRecyclerAdapter adapter;
    private static final String LIST_TYPE ="skills";
    private static final String TAG = "SkillFragment";



    public SkillFragment() {
        // Required empty public constructor
    }






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_skill, container, false);
        rv_skills = view.findViewById(R.id.rv_skills);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rv_skills.setLayoutManager(linearLayoutManager);

        getPersonList();

        return view;
    }



    public void getPersonList(){
        AsyncTask<String, Void, ArrayList<Person> >  task = new AsyncTask<String, Void, ArrayList<Person>>() {
            @Override
            protected ArrayList<Person> doInBackground(String... type) {
                String gottenJson = null;
                try {
                    gottenJson = ApiUtil.getDataJson(type[0]);
                    Log.d(TAG, "doInBackground: "+gottenJson);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Error", e.getMessage());
                }
                ArrayList<Person> personArrayList = ApiUtil.getPersonFromJSON(gottenJson, type[0]);
               return personArrayList;
            }

            @Override
            protected void onPostExecute(ArrayList<Person> people) {


                if (people == null){
                    Log.e("Error", "Unable to load books");
                }


                dataList = people;
                adapter = new MainRecyclerAdapter(dataList, LIST_TYPE);

                rv_skills.setAdapter(adapter);
            }
        };

        task.execute(LIST_TYPE);
    }


}