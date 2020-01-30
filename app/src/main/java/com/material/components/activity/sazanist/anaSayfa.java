package com.material.components.activity.sazanist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.material.components.R;
import com.material.components.activity.settings.SettingFlat;
import com.material.components.fragment.sazanistFragment.SazanistFavoritesFragment;
import com.material.components.fragment.sazanistFragment.SazanistHomeFragment;
import com.material.components.fragment.sazanistFragment.SazanistProfileFragment;
import com.material.components.fragment.sazanistFragment.SazanistShoppingFragment;
import com.material.components.utils.Tools;

import java.util.Objects;

public class anaSayfa extends AppCompatActivity {

    String filename = "wizardFile";
    BottomNavigationView bottomNav;
    boolean result;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);

        // read file , if true go to the stepper
        readFile();
        if (!result) {
            startActivity(new Intent(this, StepperWizardColor.class));
        }

        bottomNav = findViewById(R.id.anasayfa_bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new SazanistHomeFragment()).commit();
        }

        initToolbar();

    }

    private void readFile() {
        SharedPreferences sharedPref = getSharedPreferences(filename, Context.MODE_PRIVATE);
        result = sharedPref.getBoolean("isShown", false);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = new SazanistHomeFragment();

                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            bottomNav.setBackgroundColor(getResources().getColor(R.color.blue_grey_700));
                            selectedFragment = new SazanistHomeFragment();
                            break;
                        case R.id.navigation_favorites:
                            bottomNav.setBackgroundColor(getResources().getColor(R.color.pink_800));
                            selectedFragment = new SazanistFavoritesFragment();
                            break;
                        case R.id.navigation_shopping_cart:
                            bottomNav.setBackgroundColor(getResources().getColor(R.color.grey_700));
                            selectedFragment = new SazanistShoppingFragment();
                            break;
                        case R.id.navigation_profile:
                            bottomNav.setBackgroundColor(getResources().getColor(R.color.teal_800));
                            selectedFragment = new SazanistProfileFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_homepage_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }else if (item.getItemId()== R.id.action_about){
            startActivity(new Intent(this, AboutCompanyCard.class));
        }else if (item.getItemId()==R.id.action_settings){
            startActivity(new Intent(this, SettingFlat.class));
        }else if (item.getItemId()==R.id.action_cart){
            startActivity(new Intent(this, ShoppingCartSimple.class));
        }
        else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        //toolbar.setBackgroundColor();
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Bağlama Trainer v1.0");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

}
