package com.biozat.aadproject1;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class SubmissionActivityFragment extends Fragment {
 private AlertDialog confirmSubmission, alertSuccess, alertWarning;
 private EditText firstname, lastname, email, link;
 private TextView submit;
 private APIService mAPIService;

    public SubmissionActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentLayout = inflater.inflate(R.layout.fragment_submission, container, false);

        firstname = (EditText) fragmentLayout.findViewById(R.id.first_name);
        lastname = (EditText) fragmentLayout.findViewById(R.id.last_name);
        email = (EditText) fragmentLayout.findViewById(R.id.email);
        link = (EditText) fragmentLayout.findViewById(R.id.github_link);
        submit = (TextView) fragmentLayout.findViewById(R.id.submit);

        final String firtName = firstname.getText().toString().trim();
        final String lastName = lastname.getText().toString().trim();
        final String edit_email = email.getText().toString().trim();
        final String edit_link = link.getText().toString().trim();

        mAPIService = ApiUtil.getAPIService();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildConfirmSubmission(firtName, lastName, edit_email, edit_link);
            }
        });
        return fragmentLayout;
    }

    private void sendPost(String firstname, String lastname, String email, String link){
        mAPIService.savePost(firstname, lastname, email, link).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                buildAlertSuccess();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                buildAlertWarning();
            }
        });
    }


    private void buildConfirmSubmission(final String firstname, final String lastname,
                                        final String email, final String link){
        final AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(getContext());

        final View dilaogView = LayoutInflater.from(getContext()).inflate(R.layout.confrim_layout, null);
        confirmBuilder.setView(dilaogView);
        final TextView sure = (TextView) dilaogView.findViewById(R.id.sure);
        final TextView yes = (TextView) dilaogView.findViewById(R.id.yes);
        final ImageView cancel = (ImageView) dilaogView.findViewById(R.id.cancel);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPost(firstname, lastname, email, link);
            }
        });

        confirmBuilder.setPositiveButton("", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {



            }
        });


        confirmSubmission = confirmBuilder.create();
        confirmSubmission.show();

    }

    private void buildAlertSuccess(){
        final AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(getContext());

        final View dilaogView = LayoutInflater.from(getContext()).inflate(R.layout.alert_success, null);
        confirmBuilder.setView(dilaogView);

        confirmBuilder.setPositiveButton("", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {



            }
        });

        alertSuccess = confirmBuilder.create();
        alertSuccess.show();

    }

    private void buildAlertWarning(){
        final AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(getContext());
        final View dilaogView = LayoutInflater.from(getContext()).inflate(R.layout.alert_warning, null);
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
