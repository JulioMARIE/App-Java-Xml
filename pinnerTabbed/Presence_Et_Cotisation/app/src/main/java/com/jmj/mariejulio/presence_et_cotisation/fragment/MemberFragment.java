package com.jmj.mariejulio.presence_et_cotisation.fragment;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.jmj.mariejulio.presence_et_cotisation.AddEditActivity;
import com.jmj.mariejulio.presence_et_cotisation.EditAddActivity;
import com.jmj.mariejulio.presence_et_cotisation.R;
import com.jmj.mariejulio.presence_et_cotisation.SlideActivity;
import com.jmj.mariejulio.presence_et_cotisation.adapter.MemberAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import Data.DBHelper;
import Model.Member;

import static android.app.Activity.RESULT_OK;

public class MemberFragment extends Fragment {

    private static int ADD_OK = 99;
    private RecyclerView recyclerView;
     public static RecyclerView.Adapter adapter;
     public static ArrayList<Member> memberList;
    private  DBHelper databaseHelper = null;
    private TextView total;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_members, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.rview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));

        memberList = new ArrayList<>();

        memberList = getAllMember();

        adapter = new MemberAdapter(getActivity().getBaseContext(), memberList);

        recyclerView.setAdapter(adapter);


        total = (TextView) v.findViewById(R.id.txttotal);
        total.setText(String.valueOf(adapter.getItemCount()+" membre(s)"));

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AddEditActivity.class);
                startActivityForResult(intent, ADD_OK);
            }
        });

        return v;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK  && requestCode == ADD_OK){
//            memberList.clear();
//            memberList.addAll(getAllMember());
//            adapter.notifyDataSetChanged();
//            total.setText(String.valueOf(adapter.getItemCount()+" membres"));
            Intent intent =new Intent(getContext(), SlideActivity.class);
            startActivity(intent);
        }
//        if(resultCode == RESULT_OK ){
//            memberList.clear();
//            memberList.addAll(getAllMember());
//            adapter.notifyDataSetChanged();
//        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public ArrayList<Member> getAllMember() {
        ArrayList<Member> memberList = new ArrayList<>();
        try {
            memberList.addAll(getHelper().getAllOrdered(Member.class, "prenom", true));
        } catch (java.sql.SQLException e) {
            Toast(getString(R.string.errorlistloading));
            e.printStackTrace();
        }
        return memberList;
    }



    public void Toast(String msg){
        Toast.makeText(getActivity().getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    private DBHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper =
                    OpenHelperManager.getHelper(getActivity().getApplicationContext(), DBHelper.class);
        }
        return databaseHelper;
    }



    @Override
    public void onStart() {
        memberList.clear();
        memberList.addAll(getAllMember());
        adapter.notifyDataSetChanged();
        total.setText(String.valueOf(adapter.getItemCount()+" membre(s)"));
        super.onStart();
    }


//    @Override
//    public void onResume() {
//        memberList.clear();
//        memberList.addAll(getAllMember());
//        adapter.notifyDataSetChanged();
//        total.setText(String.valueOf(adapter.getItemCount()+" membres"));
//        super.onResume();
//    }

    //    @Override
//    public boolean onLongClick(View v) {
//        return false;
//    }
}
