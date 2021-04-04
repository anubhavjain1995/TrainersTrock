package com.trainersstocks.CustomersApp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.Utils.LogoutDialog;
import com.trainersstocks.CustomersApp.Utils.MyLog;
import com.trainersstocks.CustomersApp.Utils.PreferenceManger;
import com.trainersstocks.CustomersApp.abstractactivity.MyAbstractActivity;
import com.trainersstocks.CustomersApp.fragments.AboutCompanyFragment;
import com.trainersstocks.CustomersApp.fragments.Home2Fragment;
import com.trainersstocks.CustomersApp.fragments.IPOFragment;
import com.trainersstocks.CustomersApp.fragments.LiveMarketFragment;
import com.trainersstocks.CustomersApp.fragments.MutalfundFragment;
import com.trainersstocks.CustomersApp.fragments.PortfolioFragment;
import com.trainersstocks.CustomersApp.fragments.TradeFragment;
import com.trainersstocks.CustomersApp.fragments.ViewProfileFragment;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainLoginUserActivity extends MyAbstractActivity implements LogoutDialog.OnDismiss {


    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;

    @BindView(R.id.nav_view)
    NavigationView nav_view;


    @BindView(R.id.bottom_navigation_view_linear)
    BubbleNavigationLinearView bubbleNavigationLinearView;

    @BindView(R.id.tootbar)
    Toolbar tootbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainuserlogin);
        ButterKnife.bind(this);
        initview();
        listners();
        MyLog.LogE("TAG", ">>  BubbleNavigationConstraintView ");

    }


    @Override
    public void initview() {
        setSupportActionBar(tootbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setToolbar(tootbar);
        bubbleNavigationLinearView.setNavigationChangeListener((view, position) -> {
            switch (position) {
                case 0:
                    showFragment(new Home2Fragment());
                    break;
                case 1:
                    showFragment(new LiveMarketFragment());
                    break;
                case 2:
                    showFragment(new PortfolioFragment());
                    break;
                case 3:
                    showFragment(new ViewProfileFragment());
                    break;
            }
        });
        showFragment(new Home2Fragment());
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeholder, fragment).commit();
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);

    }


    @Override
    public void listners() {

    }

    @Override
    public void OnDismiss() {
        PreferenceManger.Logout(this);
        startActivity(new Intent(this, SplashActivity.class));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyLog.LogE("TAG", "API Response : " + requestCode);
        MyLog.LogE("TAG", "API Response : " + resultCode);
//        MyLog.LogE("TAG", "API Response : " + new Gson().toJson(data));
        //Prints all extras. Replace with app logic.
        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null)
                for (String key : bundle.keySet()) {
                    if (bundle.getString(key) != null) {
                        MyLog.LogE("TAG", key + " : " + bundle.getString(key));
                    }
                }
        }
    }

    public void logout(View view) {
        new LogoutDialog(this, this).show();
    }


    public void ipo(View view) {
        IPOFragment fragment = new IPOFragment();
        fragment.show(getSupportFragmentManager(), "");
        new Handler().post(closeDrawer);

    }

    public void aboutus(View view) {
        AboutCompanyFragment fragment = new AboutCompanyFragment();
        fragment.show(getSupportFragmentManager(), "");
        new Handler().post(closeDrawer);
    }

    public void mutualfund(View view) {
        MutalfundFragment fragment = new MutalfundFragment();
        fragment.show(getSupportFragmentManager(), "");
        new Handler().post(closeDrawer);

    }

    public void trade(View view) {
        TradeFragment fragment = new TradeFragment(getSupportFragmentManager());
        fragment.show(getSupportFragmentManager(), "");
        new Handler().post(closeDrawer);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawer_layout.isDrawerOpen(Gravity.LEFT)) {
                drawer_layout.closeDrawers();
            } else {
                drawer_layout.openDrawer(Gravity.LEFT);
            }
        }
        return true;
    }


    private Runnable closeDrawer = () -> {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        }
    };

}

