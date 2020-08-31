package com.biozat.aadproject1;


import android.net.Uri;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ApiUtil {

    private ApiUtil(){}

    public static final String BASE_API_URL =
            "https://gadsapi.herokuapp.com/api/hours";

    public static final String BASE_API_URL_IQ =
            "https://gadsapi.herokuapp.com/api/skilliq";

    public static final String BASE_FORM_URL = "https://docs.google.com/forms/d/e/";

    public static APIService getAPIService(){
        return RetrofitClient.getClient(BASE_FORM_URL).create(APIService.class);
    }

    public static URL buildUrl(){

        URL url = null;
        Uri uri = Uri.parse(BASE_API_URL);


        try{

          url = new URL(uri.toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return url;
    }

    public static URL buildIQUrl(){

        URL url = null;
        Uri uri = Uri.parse(BASE_API_URL_IQ);


        try{

          url = new URL(uri.toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return url;
    }

    public static String getJson(URL url) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();


        try {
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");

            boolean hashData = scanner.hasNext();
            if (hashData) {
                return scanner.next();
            } else {
                return null;
            }
        }
        catch (Exception e){
            Log.d("Error", e.toString());
            return  null;
        }
        finally {
            connection.disconnect();
        }

    }

    public static ArrayList<Leader> getLeaderFromJson(String json){
        final String NAME = "name";
        final String SCORE = "hours";
        final String COUNTRY = "country";
        final String BADGEURL = "badgeUrl";

        ArrayList<Leader> leaders = new ArrayList<Leader>();

        try{

            JSONArray arrayLeader = new JSONArray(json);

            int numberOfLeaders = arrayLeader.length();
            for(int i =0; i<numberOfLeaders; i++){
                JSONObject leaderJson = arrayLeader.getJSONObject(i);
                Leader leader = new Leader(leaderJson.getString(NAME),
                        leaderJson.getString(SCORE),
                        leaderJson.getString(COUNTRY),
                        leaderJson.getString(BADGEURL));

                leaders.add(leader);

            }


        }catch (JSONException e){
           e.printStackTrace();
        }
        return leaders;
    }

    public static ArrayList<Leader> getLeaderIQFromJson(String json){
        final String NAME = "name";
        final String SCORE = "score";
        final String COUNTRY = "country";
        final String BADGEURL = "badgeUrl";

        ArrayList<Leader> leaders = new ArrayList<Leader>();

        try{

            JSONArray arrayLeader = new JSONArray(json);

            int numberOfLeaders = arrayLeader.length();
            for(int i =0; i<numberOfLeaders; i++){
                JSONObject leaderJson = arrayLeader.getJSONObject(i);
                Leader leader = new Leader(leaderJson.getString(NAME),
                        leaderJson.getString(SCORE),
                        leaderJson.getString(COUNTRY),
                        leaderJson.getString(BADGEURL));

                leaders.add(leader);

            }


        }catch (JSONException e){
           e.printStackTrace();
        }
        return leaders;
    }
}
