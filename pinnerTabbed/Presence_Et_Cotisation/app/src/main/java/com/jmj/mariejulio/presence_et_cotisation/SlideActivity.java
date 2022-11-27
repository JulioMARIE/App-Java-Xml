package com.jmj.mariejulio.presence_et_cotisation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.jmj.mariejulio.presence_et_cotisation.adapter.MemberAdapter;
import com.jmj.mariejulio.presence_et_cotisation.fragment.CotisationFragment;
import com.jmj.mariejulio.presence_et_cotisation.fragment.DusFragment;
import com.jmj.mariejulio.presence_et_cotisation.fragment.MemberFragment;


public class SlideActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SearchView searchView = (SearchView) findViewById(R.id.action_search);

        //add action to filter

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState==null){
            toolbar.setTitle(getString(R.string.les_membres));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MemberFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_member);
        }
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
        getMenuInflater().inflate(R.menu.slide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()){
            case R.id.nav_member:
                toolbar.setTitle(item.getTitle());
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MemberFragment()).commit();
                break;
            case R.id.nav_cot:
                toolbar.setTitle(item.getTitle());
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CotisationFragment()).commit();
                break;
            case R.id.nav_dus:
                toolbar.setTitle(item.getTitle());
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DusFragment()).commit();
                break;
            case R.id.nav_joke:
                toolbar.setTitle(item.getTitle());
                Toast.makeText(getBaseContext(), "Enjoy it!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_about:
                toolbar.setTitle(item.getTitle());
                Toast.makeText(getApplicationContext(), "GRACIAS DEI!", Toast.LENGTH_SHORT).show();
                break;
        }


//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//
//    @Override
//    protected void onPostResume() {
//        Intent intent = new Intent(this, SlideActivity.class);
//        startActivity(intent);
//        super.onPostResume();
//    }

    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(resultCode == RESULT_OK ){
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MemberFragment()).commit();
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }


//    @Override
//    protected void onPause() {
//        finish();
//        super.onPause();
//    }
}
