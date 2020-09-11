package com.example.gad_project;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class ApiUtil {

    private static final String BASE_URL = "https://gadsapi.herokuapp.com/api";
    private static final String SKILL_ROUTE = "/skilliq";
    private static final String HOURS_ROUTE = "/hours";
    private static HttpURLConnection connection;
    private static final String TAG = "ApiUtil";


    private static URL buildUrl(String listType){
        URL url = null;

        String route = listType.equals("skills") ? SKILL_ROUTE : HOURS_ROUTE;
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append(route);

        Log.d(TAG, "buildUrl: "+sb.toString());

        Uri uri = Uri.parse(sb.toString()).buildUpon().build();

        try {
            url = new URL(uri.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "buildUrl: "+url.toString());
        return url;
    }

    public static String getDataJson(String listType) throws IOException {

        String line="";
        try {
             connection = (HttpURLConnection) buildUrl(listType).openConnection();




            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));

            while ((line = rd.readLine()) != null) {
                Log.i("data", line);
                return line;
            }





        } catch (Exception e) {
            Log.e(TAG, "getDataJson: Error here"+e.getMessage(),e.fillInStackTrace() );

        }finally {
            connection.disconnect();
        }
        return  null;

    }



    public  static ArrayList<Person> getPersonFromJSON(String json, String listType){
        ArrayList<Person> personArrayList = new ArrayList<>();

        Log.d(TAG, "getPersonFromJSON: "+json);

        try {
            JSONArray jsonPersons = new JSONArray(json);
            Log.d(TAG, "getPersonFromJSON: "+jsonPersons.toString());

            for (int i = 0; i <jsonPersons.length() ; i++) {

                JSONObject personObject = jsonPersons.getJSONObject(i);

                Person person = new Person(
                        personObject.getString("name"),
                        personObject.getString("country"),
                        personObject.getString("badgeUrl"),
                        listType.equals("hours") ? personObject.getInt("hours") : personObject.getInt("score")
                );

                personArrayList.add(person);
            }



        }catch (JSONException e) {
            e.printStackTrace();
        }

        return  personArrayList;
    }

}
