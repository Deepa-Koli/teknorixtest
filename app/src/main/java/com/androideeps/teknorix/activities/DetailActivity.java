package com.androideeps.teknorix.activities;

import android.os.Bundle;

import com.androideeps.teknorix.R;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.androideeps.teknorix.databinding.ActivityDetailBinding;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityDetailBinding binding;

    private ImageView iv;
    TextView tvname, tvcountry, tvcity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        iv = findViewById(R.id.iv);
        tvname = findViewById(R.id.name);
        tvcountry = findViewById(R.id.country);
        tvcity = findViewById(R.id.city);

        final String getAvatarImg = getIntent().getStringExtra("avatar");
        final String getEmail = getIntent().getStringExtra("email");
        final String getFirstName = getIntent().getStringExtra("first_name");
        final String getLastName = getIntent().getStringExtra("last_name");

        Picasso.get().load(getAvatarImg).into(iv);
        tvname.setText(getEmail);
        tvcountry.setText(getFirstName);
        tvcity.setText(getLastName);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
       return false;
    }
}