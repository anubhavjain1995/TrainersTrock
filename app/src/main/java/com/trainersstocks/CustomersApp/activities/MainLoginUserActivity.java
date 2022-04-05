package com.trainersstocks.CustomersApp.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.trainersstocks.CustomersApp.Models.UMModel;
import com.trainersstocks.CustomersApp.Models.UserDetailsMModel;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.Utils.LogoutDialog;
import com.trainersstocks.CustomersApp.Utils.MyLog;
import com.trainersstocks.CustomersApp.Utils.PreferenceManger;
import com.trainersstocks.CustomersApp.abstractactivity.BaseActivity;
import com.trainersstocks.CustomersApp.abstractactivity.MyAbstractActivity;
import com.trainersstocks.CustomersApp.fragments.AboutCompanyFragment;
import com.trainersstocks.CustomersApp.fragments.AccountFragment;
import com.trainersstocks.CustomersApp.fragments.ContactUsFragment;
import com.trainersstocks.CustomersApp.fragments.ForexTradeFragment;
import com.trainersstocks.CustomersApp.fragments.Home2Fragment;
import com.trainersstocks.CustomersApp.fragments.IPOFragment;
import com.trainersstocks.CustomersApp.fragments.LimitFragment;
import com.trainersstocks.CustomersApp.fragments.LiveMarketFragment;
import com.trainersstocks.CustomersApp.fragments.MoreOptionsFragment;
import com.trainersstocks.CustomersApp.fragments.MutalfundFragment;
import com.trainersstocks.CustomersApp.fragments.MyProfileFragment;
import com.trainersstocks.CustomersApp.fragments.OffersFragment;
import com.trainersstocks.CustomersApp.fragments.PortfolioFragment;
import com.trainersstocks.CustomersApp.fragments.ReferandEarnFragment;
import com.trainersstocks.CustomersApp.fragments.StockSipFragment;
import com.trainersstocks.CustomersApp.fragments.TradeFragment;
import com.trainersstocks.CustomersApp.fragments.ViewProfileFragment;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.trainersstocks.CustomersApp.retrofit_provider.RetrofitService;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainLoginUserActivity extends MyAbstractActivity implements LogoutDialog.OnDismiss {


    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;

    @BindView(R.id.nav_view)
    NavigationView nav_view;


    @BindView(R.id.bottom_navigation_view_linear)
    BubbleNavigationLinearView bubbleNavigationLinearView;

    @BindView(R.id.tootbar)
    Toolbar tootbar;

    @BindView(R.id.l_details)
    LinearLayout l_details;

    @BindView(R.id.image_logo)
    ImageView image_logo;

    @BindView(R.id.txt_name)
    TextView txt_name;

    @BindView(R.id.txt_username)
    TextView txt_username;

    @BindView(R.id.txt_login_date)
    TextView txt_login_date;

    @BindView(R.id.img_arrow)
    ImageView img_arrow;

    @BindView(R.id.img_arrow_set)
    ImageView img_arrow_set;

    @BindView(R.id.rl_trade_drop)
    RelativeLayout rl_trade_drop;

    @BindView(R.id.rl_set_drop)
    RelativeLayout rl_set_drop;

    ObjectAnimator oa1,oa2;
    private UserDetailsMModel userDetail = null;
    boolean is_dropdown_open= false;
    boolean is_dropdown_set_open= false;

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
        
        drawer_layout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                animateView();
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                showStaticBlink();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        if (PreferenceManger.getUserDetailS() != null) {
            getUserDeatils();
        }
    }

    public void animateView(){
        oa1 = ObjectAnimator.ofFloat(l_details, "scaleX", 1f, 0f);
        oa2 = ObjectAnimator.ofFloat(image_logo, "scaleX", 0f, 1f);
        oa1.setInterpolator(new DecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                l_details.setVisibility(View.INVISIBLE);
                image_logo.setVisibility(View.VISIBLE);
                oa2.start();
            }
        });
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               oa1.start();
           }
       },1000);


        oa2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                l_details.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        image_logo.setVisibility(View.INVISIBLE);
                        oa1.start();
                    }
                },3000);

            }
        });
        oa1.setDuration(2000);
        oa2.setDuration(2000);
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
        new Handler().post(closeDrawer);
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
        /*TradeFragment fragment = new TradeFragment(getSupportFragmentManager());
        fragment.show(getSupportFragmentManager(), "");
        new Handler().post(closeDrawer);*/
        if (is_dropdown_open){
            is_dropdown_open = false;
            img_arrow.setRotation(0);
            rl_trade_drop.setVisibility(View.GONE);
        }else{
            is_dropdown_open = true;
            img_arrow.setRotation(180);
            rl_trade_drop.setVisibility(View.VISIBLE);
        }
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

    public void showStaticBlink(){
        oa1.cancel();
        oa2.cancel();
    }

    public void more(View view) {
        Toast.makeText(MainLoginUserActivity.this, "More click", Toast.LENGTH_SHORT).show();
    }

    public void offers(View view) {
        OffersFragment fragment = new OffersFragment();
        fragment.show(getSupportFragmentManager(), "");
        new Handler().post(closeDrawer);
        is_dropdown_open = false;
        img_arrow.setRotation(0);
        rl_trade_drop.setVisibility(View.GONE);
    }

    public void refer_n_earn(View view) {
        startActivity(new Intent(MainLoginUserActivity.this,ReferEarnActivity.class));
        new Handler().post(closeDrawer);
    }

    private void getUserDeatils() {
       // getDialog().show();
        Map<String, String> jsonParams = new ArrayMap<>();
        jsonParams.put("id", PreferenceManger.getUserDetailS().getUserDetails().getId());
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());

        Call<UMModel> call = RetrofitService.RetrofitService().getUserdetail(body);
        call.enqueue(new Callback<UMModel>() {
            @Override
            public void onResponse(Call<UMModel> call, Response<UMModel> response) {
                Log.e("TAG", ">>onResponse onResponse " + new Gson().toJson(response.body()));
                //getDialog().dismiss();
                if (response.body() != null && !response.body().getError()) {
                    userDetail = response.body().getModel();
                    setData(response.body().getModel());
                } else {
                    Toasty.error(MainLoginUserActivity.this, "Failed to load data", Toasty.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UMModel> call, Throwable t) {
                Toasty.error(MainLoginUserActivity.this, "Failed to load data", Toasty.LENGTH_LONG).show();
               // getDialog().dismiss();
            }
        });
    }

    private void setData(UserDetailsMModel model) {
        if (model != null){
            txt_name.setText(model.getFirstName() + " "+ model.getLastName());
            txt_username.setText(model.getUserId());
            if (PreferenceManger.getLastLoginn() != null) {
                txt_login_date.setText(PreferenceManger.getLastLoginn());
            }else{
                Date date = new Date();
                txt_login_date.setText(""+ DateFormat.getDateTimeInstance().format(date));
            }
        }
    }

    public void stockSipOrderBook(View view) {
    }

    public void stockSip(View view) {
        StockSipFragment fragment = new StockSipFragment();
        fragment.show(getFragmentManager(), "");
        new Handler().post(closeDrawer);
        is_dropdown_open = false;
        img_arrow.setRotation(0);
        rl_trade_drop.setVisibility(View.GONE);
    }

    public void limits(View view) {
        LimitFragment fragment = new LimitFragment();
        fragment.show(getSupportFragmentManager(), "");
        new Handler().post(closeDrawer);
        is_dropdown_open = false;
        img_arrow.setRotation(0);
        rl_trade_drop.setVisibility(View.GONE);
    }

    public void openaccount(View view) {
        AccountFragment fragment = new AccountFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeholder1, fragment).addToBackStack("account").commit();
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        new Handler().post(closeDrawer);
    }

    public void myprofile(View view) {
        MyProfileFragment fragment = new MyProfileFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeholder1, fragment).addToBackStack("my_profile").commit();
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        new Handler().post(closeDrawer);
    }

    public void portfolio(View view) {
    }

    public void funds(View view) {
        startActivity(new Intent(MainLoginUserActivity.this, AddFundsActivity.class));
        new Handler().post(closeDrawer);
    }

    public void forextrade(View view) {
        ForexTradeFragment fragment = new ForexTradeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeholder1, fragment).addToBackStack("forex_trade").commit();
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        new Handler().post(closeDrawer);

    }

    public void settings(View view) {
        MoreOptionsFragment fragment = new MoreOptionsFragment();
        fragment.show(getSupportFragmentManager(), "");
        new Handler().post(closeDrawer);
    }

    public void set(View view) {
        if (is_dropdown_set_open){
            is_dropdown_set_open = false;
            img_arrow_set.setRotation(0);
            rl_set_drop.setVisibility(View.GONE);
        }else{
            is_dropdown_set_open = true;
            img_arrow_set.setRotation(180);
            rl_set_drop.setVisibility(View.VISIBLE);
        }
    }

    public void contact_us(View view) {
        ContactUsFragment fragment = new ContactUsFragment();
        fragment.show(getSupportFragmentManager(), "");
        new Handler().post(closeDrawer);
    }
}

