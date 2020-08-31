package com.biozat.aadproject1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivty extends AppCompatActivity {
    private  final  int SPLASH_DISPLAY_LENGTH = 2000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        ImageView imageView = (ImageView) findViewById(R.id.splash_img);
        String url = "https://drive.google.com/file/d/15zrGImDIVAuC-T2HKdNhLmAdf3qjzHm5/view?usp=drivesdk";

        Picasso.get().load(R.drawable.gads)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher).into(imageView);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivty.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
