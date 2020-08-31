package com.biozat.aadproject1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LearningLeaderFragment extends Fragment {
    private ProgressDialog pDialog;
    private TextView textView;
    private String jsonResult;
    public RecyclerAdapter noteAdaptor;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private AlertDialog alertWarning;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //inflate our fragment edit layout
      View fragmentLayout = inflater.inflate(R.layout.mainlist_fragment_layout, container, false);

        recyclerView = (RecyclerView) fragmentLayout.findViewById(R.id.recylerview);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        if(isOnline()){
            showData();
        }else{
            buildAlertWarning();
        }
        return fragmentLayout;
    }

    private void showData(){
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading data. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = ApiUtil.buildUrl();
                    jsonResult = ApiUtil.getJson(url);

                }
                catch (Exception e){
                    Log.d("Error", e.getMessage());
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pDialog.dismiss();
                        ArrayList<Leader> leaders = ApiUtil.getLeaderFromJson(jsonResult);

                        noteAdaptor = new RecyclerAdapter(getActivity(), leaders);
                        recyclerView.setAdapter(noteAdaptor);


                    }
                });


            }
        }).start();

    }

    private  boolean isOnline(){
        ConnectivityManager cm  = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    private void buildAlertWarning(){
        final AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(getContext());
        final View dilaogView = LayoutInflater.from(getContext()).inflate(R.layout.alert_network_warning, null);
        confirmBuilder.setView(dilaogView);

        confirmBuilder.setPositiveButton("", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {



            }
        });


        alertWarning = confirmBuilder.create();
        alertWarning.show();

    }


}
