package com.trainersstocks.CustomersApp.activities;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.abstractactivity.MyAbstractActivity;
import com.trainersstocks.CustomersApp.fragments.AboutUsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TermOfusesActivity extends MyAbstractActivity {
    @BindView(R.id.toolbar_notification)
    Toolbar toolbar_notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_ofuses);
        ButterKnife.bind(this);
        setToolbar(toolbar_notification);
        showBackButton();
        initview();
        listners();
    }

    @Override
    public void initview() {
        if (getIntent() == null) {
            finish();
        }
        String what = getIntent().getStringExtra("WHAT");
        AboutUsFragment fragment = new AboutUsFragment();
        if (what == null && what.isEmpty()) {
            finish();
        } else if (what.equalsIgnoreCase("TERMS")) {
            setTitle(getResources().getString(R.string.menu_termsandconditions));
            Bundle bundle = new Bundle();
            bundle.putString("URL", "https://moneycrop.in/api/content/term");
            fragment.setArguments(bundle);
            showFragment(fragment);
        } else {
            setTitle(getResources().getString(R.string.menu_privacypolicy));
            Bundle bundle = new Bundle();
            bundle.putString("URL", "https://moneycrop.in/api/content/privacy");
            fragment.setArguments(bundle);
            showFragment(fragment);
        }
    }


    private void showFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.gl, fragment).commit();
    }

    @Override
    public void listners() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.action_notification) {
            startActivity(new Intent(this, NotificationActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }

        return super.onOptionsItemSelected(item);
    }

}