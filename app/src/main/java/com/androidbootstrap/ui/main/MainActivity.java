package com.androidbootstrap.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidbootstrap.R;
import com.androidbootstrap.bean.Person;
import com.androidbootstrap.inject.component.ActivityComponent;
import com.androidbootstrap.base.activity.BaseActivity;
import com.androidbootstrap.ui.list.TestListActivity;
import com.androidbootstrap.util.ToastUtil;
import com.library.constant.Constant;
import com.androidbootstrap.base.fragment.LoadingDialogFragment;
import com.library.ui.view.MultiStateView;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter>
        implements IMainView, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.btn_show)
    Button btnShow;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_progress)
    Button btnProgress;

    @BindView(R.id.tv_info)
    TextView             tvInfo;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.toolbar)
    Toolbar              toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout         drawer;
    @BindView(R.id.nav_view)
    NavigationView       navigationView;
    @BindView(R.id.multiStateView)
    MultiStateView       multiStateView;
    private Button btnRetry;

    LoadingDialogFragment loadingDialogFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setActivityComponent(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }


    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        btnRetry = (Button) multiStateView.findViewById(R.id.retry);

    }

    @Override
    protected void initEvent(@Nullable Bundle savedInstanceState) {
        super.initEvent(savedInstanceState);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //                        .setAction("Action", null).show();
                ToastUtil.show("OOOOOOOOOOOOOO");
            }
        });


        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loadProfile();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestListActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loadProfile();
            }
        });

        btnProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loadProfileWithProgress();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inbox) {
            //            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new InboxFragment()).commit();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void writeEmail(String email) {

    }

    @Override
    public String readEmail() {
        return null;
    }

    @Override
    public void setProfile(Person person) {
        tvInfo.setText(person.toString());
    }


    @Override
    public void setViewState(int state) {
        multiStateView.setViewState(state);
    }

    @Override
    public void displayDialog(int dialogType) {
        switch (dialogType) {
            case Constant.DIALOG_TYPE.LOADING_DIALOG:
                loadingDialogFragment = new LoadingDialogFragment();
                loadingDialogFragment.setCanceledOnTouchOutside(false);
                loadingDialogFragment.setCancelable(false);
                loadingDialogFragment.show(getSupportFragmentManager(), "loading_dialog");
                break;

        }
    }

    @Override
    public void hideDialog(int... dialogType) {
        for (int i : dialogType) {
            switch (i) {
                case Constant.DIALOG_TYPE.LOADING_DIALOG:
                    loadingDialogFragment.dismissAllowingStateLoss();
                    break;
            }
        }
    }
}
