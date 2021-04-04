package com.trainersstocks.CustomersApp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.trainersstocks.CustomersApp.Utils.LogoutDialog;
import com.trainersstocks.CustomersApp.Utils.PreferenceManger;
import com.trainersstocks.CustomersApp.fragments.AboutswithoutloginFragment;
import com.trainersstocks.CustomersApp.fragments.AccountsFragment;
import com.trainersstocks.CustomersApp.fragments.HomeFragment;
import com.trainersstocks.CustomersApp.fragments.PricingFragment;
import com.trainersstocks.CustomersApp.fragments.ServicesFragment;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.abstractactivity.MyAbstractActivity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class NavigationActivity extends MyAbstractActivity {

    Fragment fragment;
    private boolean isHome = false;


    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;

    @BindView(R.id.nav_view)
    NavigationView nav_view;


    private AppBarConfiguration mAppBarConfiguration;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        initview();
        listners();
    }

    public void showFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeholder, fragment).commit();
        new Handler().post(closeDrawer);

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void Exit() {
        this.finish();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (isHome) {
            finish();
        } else {
            fragment = new HomeFragment();
            setTitle(getResources().getString(R.string.menu_home));
            showFragment(fragment);
            isHome = true;
        }

    }

    CircleImageView imageView;
    AppCompatTextView tv_name;
    AppCompatTextView tv_profile_complete;
    ProgressBar profilebar;


    @Override
    public void initview() {
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        showFragment(new HomeFragment());
        View headerLayout = nav_view.getHeaderView(0); // 0-index header
    }


    @Override
    public void listners() {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawer_layout.isDrawerOpen(Gravity.LEFT)) {
                drawer_layout.closeDrawers();
            } else {
                drawer_layout.openDrawer(Gravity.LEFT);
            }
        } else if (item.getItemId() == R.id.action_notification) {
            startActivity(new Intent(this, NotificationActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
        return true;
    }


    private Runnable closeDrawer = () -> {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        }
    };

    public void HomeWithLogin(View view) {


    }

    public void logout(View view) {
        LogoutDialog dialog = new LogoutDialog(this, () -> {
            PreferenceManger.Logout(this);
            startActivity(new Intent(this, SplashActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        });
        dialog.show();
    }


    public void livemarket(View view) {

    }

    public void trade(View view) {
    }

    public void aboutcompany(View view) {

    }

    public void profile(View view) {

    }

    public void portfolio(View view) {

    }

    public void homewithoutlogin(View view) {
        fragment = new HomeFragment();
        setTitle(getResources().getString(R.string.menu_home));
        showFragment(fragment);
        isHome = true;

    }

    public void services(View view) {
        fragment = new ServicesFragment();
        setTitle(getResources().getString(R.string.menu_home));
        showFragment(fragment);
        isHome = false;
    }


    public void aboutus(View view) {
        fragment = new AboutswithoutloginFragment();
        setTitle(getResources().getString(R.string.menu_home));
        showFragment(fragment);
        isHome = false;
    }

    public void account(View view) {
        fragment = new AccountsFragment();
        setTitle(getResources().getString(R.string.menu_home));
        showFragment(fragment);
        isHome = false;
    }

    public void pricing(View view) {
        fragment = new PricingFragment();
        setTitle(getResources().getString(R.string.menu_home));
        showFragment(fragment);
        isHome = false;
    }

    public void login(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
