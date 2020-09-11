package com.example.gad_project;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;

public class HoursFragment extends Fragment {

    private RecyclerView rv_hours;
    private ArrayList<Person> dataList;
    private  MainRecyclerAdapter adapter;
    private static final String LIST_TYPE ="hours";


    public HoursFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_hours, container, false);

        rv_hours = view.findViewById(R.id.rv_hours);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rv_hours.setLayoutManager(linearLayoutManager);

        getPersonList();

        return view;
    }


    public void getPersonList(){
        AsyncTask<String, Void, ArrayList<Person>> task = new AsyncTask<String, Void, ArrayList<Person>>() {
            @Override
            protected ArrayList<Person> doInBackground(String... strings) {
                String gottenJson = null;
                try {
                    gottenJson = ApiUtil.getDataJson(LIST_TYPE);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Error", e.getMessage());
                }
                ArrayList<Person> personArrayList = ApiUtil.getPersonFromJSON(gottenJson, LIST_TYPE);
                return personArrayList;
            }

            @Override
            protected void onPostExecute(ArrayList<Person> people) {


                if (people == null){
                    Log.e("Error", "Unable to load people");
                }


                dataList = people;
                adapter = new MainRecyclerAdapter(dataList, LIST_TYPE);

                rv_hours.setAdapter(adapter);
            }
        };

        task.execute(LIST_TYPE);
    }


}