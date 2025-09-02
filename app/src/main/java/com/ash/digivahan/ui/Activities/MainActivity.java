package com.ash.digivahan.ui.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;

import com.ash.digivahan.R;
import com.ash.digivahan.data.adapters.ExpandableListAdapter;
import com.ash.digivahan.data.model.MenuModel;
import com.ash.digivahan.databinding.ActivityMainBinding;
import com.ash.digivahan.ui.Activities.infoPages.AboutUsPage;
import com.ash.digivahan.ui.Activities.infoPages.PrivacyPolicyPage;
import com.ash.digivahan.ui.Activities.infoPages.TermsConditionPage;
import com.ash.digivahan.ui.Fragments.QRHomeFragment;
import com.ash.digivahan.ui.Fragments.dashboard.DashBoardFragment;
import com.ash.digivahan.ui.Fragments.my_order.MyOrderFragment;
import com.ash.digivahan.ui.Fragments.notification.NotificationPageFragment;
import com.ash.digivahan.ui.Fragments.profileDetails.UpdateProfileFragment;
import com.ash.digivahan.ui.Fragments.virtualQR.VirtualQRListPage;
import com.ash.digivahan.utils.CommonLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.BuildConfig;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivityData";

    ActivityMainBinding binding;

    TextView navHeaderUserId, navHeaderUserName;

    NavController navController;
    private AppBarConfiguration mAppBarConfiguration;
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    TextView footer_version;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    private MainActivity activity;

    public FragmentManager fm = null;
    public Fragment activeFragment, homeFragment, notificationFragment, myOrderFragment, virtualQRListFragment, updateProfileFragment, dashBoardFragment;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        activity = this;

        fm = getSupportFragmentManager();

        expandableListView = findViewById(R.id.expandableListView);
        footer_version = findViewById(R.id.footer_version);
        footer_version.setText("Version " + BuildConfig.VERSION_NAME);

        prepareMenuData();

        expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        binding.toolbarLayout.ivProfile.setOnClickListener(v -> {
            binding.drawerLayout.openDrawer(GravityCompat.START);
        });


        homeFragment = new QRHomeFragment();
        notificationFragment = new NotificationPageFragment();
        myOrderFragment = new MyOrderFragment();
        virtualQRListFragment = new VirtualQRListPage();
        updateProfileFragment = new UpdateProfileFragment();
        dashBoardFragment = new DashBoardFragment();

        activeFragment = CommonLogic.switchFragment(fm, homeFragment, activeFragment, R.id.content_frame);
        loadFragment("QR Code");

        binding.qrHomeBtn.setOnClickListener(v -> {
            activeFragment = CommonLogic.switchFragment(fm, homeFragment, activeFragment, R.id.content_frame);
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        });

        binding.dashBoardLayout.setOnClickListener(v -> {
            activeFragment = CommonLogic.switchFragment(fm, dashBoardFragment, activeFragment, R.id.content_frame);
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        });


        /*DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        navHeaderUserId = navigationView.getHeaderView(0).findViewById(R.id.userId);
        navHeaderUserName = navigationView.getHeaderView(0).findViewById(R.id.userName);

        setHeaderData();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_dashboard, R.id.nav_jobs, R.id.nav_applied_users, R.id.nav_subscription, R.id.nav_profile, R.id.help, R.id.nav_subscription_plan, R.id.nav_job_post, R.id.nav_notification)
                .setOpenableLayout(drawer)
                .build();
        navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(MainActivity.this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);*/
    }

    private void loadFragment(String tittle) {
        binding.toolbarLayout.tvTitle.setText(tittle);
    }

    @SuppressLint("SetTextI18n")
    public void setHeaderData() {
//        navHeaderUserId.setText(getString(R.string.user_id_bold_text)+ helper.getUserIdData());
//        navHeaderUserName.setText(getString(R.string.user_name_bold_text)+ helper.getUserProfileData().getName());
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private void prepareMenuData() {
        MenuModel menuModel = new MenuModel(activity.getResources().getString(R.string.nav_notification), activity.getResources().getDrawable(R.drawable.notification_bell_icon), true, false, true); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        menuModel = new MenuModel(activity.getResources().getString(R.string.nav_my_order), activity.getResources().getDrawable(R.drawable.my_order_icon), true, false, false);
        headerList.add(menuModel);

        menuModel = new MenuModel(activity.getResources().getString(R.string.nav_virtual_qrs), activity.getResources().getDrawable(R.drawable.ic_qr), true, false, false);
        headerList.add(menuModel);

        menuModel = new MenuModel(activity.getResources().getString(R.string.nav_update_profile), activity.getResources().getDrawable(R.drawable.profile_icon1), true, false, false);
        headerList.add(menuModel);

        menuModel = new MenuModel(activity.getResources().getString(R.string.nav_about_us), activity.getResources().getDrawable(R.drawable.profile_icon1), true, false, false);
        headerList.add(menuModel);

        menuModel = new MenuModel(activity.getResources().getString(R.string.nav_term_condition), activity.getResources().getDrawable(R.drawable.profile_icon1), true, false, false);
        headerList.add(menuModel);

        menuModel = new MenuModel(activity.getResources().getString(R.string.nav_privacy_policy), activity.getResources().getDrawable(R.drawable.profile_icon1), true, false, false);
        headerList.add(menuModel);

    }


    public void drawerClicked(MenuModel menuItem, boolean switchBtnClicked, boolean switchBtnChecked) {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }
        if (this.getResources().getString(R.string.nav_notification).equalsIgnoreCase(menuItem.menuName)) {
            if (switchBtnClicked){
                Toast.makeText(activity, "switchBtnClicked", Toast.LENGTH_SHORT).show();
            }else {
                activeFragment = CommonLogic.switchFragment(fm, notificationFragment, activeFragment, R.id.content_frame);
                loadFragment("Notification");
            }
        }
        else if (this.getResources().getString(R.string.nav_my_order).equalsIgnoreCase(menuItem.menuName)) {
            activeFragment = CommonLogic.switchFragment(fm, myOrderFragment, activeFragment, R.id.content_frame);
            loadFragment("Order QR Code");
        }
        else if (this.getResources().getString(R.string.nav_virtual_qrs).equalsIgnoreCase(menuItem.menuName)) {
            activeFragment = CommonLogic.switchFragment(fm, virtualQRListFragment, activeFragment, R.id.content_frame);
            loadFragment("Download Virtual QR Code");
        }
        else if (this.getResources().getString(R.string.nav_update_profile).equalsIgnoreCase(menuItem.menuName)) {
            activeFragment = CommonLogic.switchFragment(fm, updateProfileFragment, activeFragment, R.id.content_frame);
            loadFragment("Profile");
        }else if (this.getResources().getString(R.string.nav_about_us).equalsIgnoreCase(menuItem.menuName)) {
            Intent aboutUsPage = new Intent(MainActivity.this, AboutUsPage.class);
            startActivity(aboutUsPage);
        } else if (this.getResources().getString(R.string.nav_term_condition).equalsIgnoreCase(menuItem.menuName)) {
            Intent termConditionPage = new Intent(MainActivity.this, TermsConditionPage.class);
            startActivity(termConditionPage);
        }else if (this.getResources().getString(R.string.nav_privacy_policy).equalsIgnoreCase(menuItem.menuName)) {
            Intent termConditionPage = new Intent(MainActivity.this, PrivacyPolicyPage.class);
            startActivity(termConditionPage);
        }
        /*if (this.getResources().getString(R.string.menu_home).equalsIgnoreCase(id)) {
            fm.beginTransaction().hide(active).show(homeFragment).commit();
            active = homeFragment;
            loadFragment(getResources().getString(R.string.app_name));
        } else if (getResources().getString(R.string.profile).equalsIgnoreCase(id)) {
            overridePendingTransition(R.anim.enter, R.anim.exit);
            Constant.goToNextActivity(activity, ProfileActivity.class);
        } else if (getResources().getString(R.string.wallet).equalsIgnoreCase(id)) {
            overridePendingTransition(R.anim.enter, R.anim.exit);
            Constant.goToNextActivity(activity, AddWalletAmountActivity.class);
        }else if (getResources().getString(R.string.membership).equalsIgnoreCase(id)) {
            overridePendingTransition(R.anim.enter, R.anim.exit);
            Constant.goToNextActivity(activity, MembershipActivity.class);
        } else if (getResources().getString(R.string.booking).equalsIgnoreCase(id)) {
            overridePendingTransition(R.anim.enter, R.anim.exit);
            Constant.goToNextActivity(activity, BookingActivity.class);
        } else if (getResources().getString(R.string.menu_driver).equalsIgnoreCase(id)) {
            overridePendingTransition(R.anim.enter, R.anim.exit);
            Constant.goToNextActivity(activity, MyDriversAndCabs.class);

//            Constant.goToNextActivity(activity, DriverActivity.class);
        } else if (getResources().getString(R.string.post_booking).equalsIgnoreCase(id)) {
            overridePendingTransition(R.anim.enter, R.anim.exit);
            Constant.goToNextActivity(activity, PostBookingActivity.class);
        } else if (getResources().getString(R.string.transactions).equalsIgnoreCase(id)) {
            overridePendingTransition(R.anim.enter, R.anim.exit);
            Constant.goToNextActivity(activity, TransHistoryActivity.class);
        } *//*else if (getResources().getString(R.string.cab_list).equalsIgnoreCase(id)) {
            overridePendingTransition(R.anim.enter, R.anim.exit);
            Constant.goToNextActivity(activity, CabListActivity.class);
        }*//*
        else if (getResources().getString(R.string.privacy_policy).equalsIgnoreCase(id)) {
            overridePendingTransition(R.anim.enter, R.anim.exit);
            Intent intent = new Intent(activity, Policy.class);
            intent.putExtra("policyName", "PrivacyPolicy");
            startActivity(intent);}
        else if (getResources().getString(R.string.Terms_Condition).equalsIgnoreCase(id)) {
            overridePendingTransition(R.anim.enter, R.anim.exit);
            Intent intent = new Intent(activity, Policy.class);
            intent.putExtra("policyName", "Terms");
            startActivity(intent);}
        else if (getResources().getString(R.string.Refund_policy).equalsIgnoreCase(id)) {
            overridePendingTransition(R.anim.enter, R.anim.exit);
            Intent intent = new Intent(activity, Policy.class);
            intent.putExtra("policyName", "Refund");
            startActivity(intent);}
        else if (getResources().getString(R.string.support).equalsIgnoreCase(id)) {
            overridePendingTransition(R.anim.enter, R.anim.exit);
            Constant.goToNextActivity(activity, SupportActivity.class);
        } else if (getResources().getString(R.string.logout).equalsIgnoreCase(id)) {
            session.logout(MainActivity.this);
        }*/
//        drawer.closeDrawer(GravityCompat.START);
    }


}