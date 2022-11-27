package com.jmj.mariejulio.presence_et_cotisation;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.abdularis.civ.AvatarImageView;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.jmj.mariejulio.presence_et_cotisation.fragment.MemberFragment;

import java.sql.SQLException;

import Data.DBHelper;
import Model.Member;
import de.hdodenhof.circleimageview.CircleImageView;

public class MemberDetailsActivity extends AppCompatActivity {

    private static final int EDIT_OK = 7;
    TextView name, phone, mail, add, profession, matrimo, dateannif;
    AvatarImageView imgcolorphoto;
    private DBHelper databaseHelper = null;
    Member extras, m;
    static int id = 0;

//    @Override
//    protected void onStart() {
//        m = (Member) getIntent().getSerializableExtra("MEMBERINFO");
//        String usernamedetail = m.getPrenom() + " " + m.getNom(),
//                memberphone = m.getTelephone(), membertel = m.getTelephone(),
//                membermail = m.getEmail(), memberadd = m.getAddresse(), memberprofe = m.getProfession(),
//                membermatri = m.getSituationmatri(), memberdateannif = m.getDateannif();
//        name.setText(usernamedetail);
//        phone.setText(memberphone);
//        mail.setText(membermail);
//        add.setText(memberadd);
//        profession.setText(memberprofe);
//        matrimo.setText(membermatri);
//        dateannif.setText(memberdateannif);
//        GradientDrawable bgShape = (GradientDrawable) imgcolorphoto.getBackground();
//        bgShape.setColor(Color.parseColor("#50707F"));
//        super.onStart();
//    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        id= getIntent().getIntExtra("MEMBERINFO", 0);
        try {
            extras = getHelper().getById(Member.class, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        try {
//             id = extras.getId();
//         }catch (Exception e) {
//             e.getMessage();
//         }

        name = (TextView) findViewById(R.id.DETAILS_name);
        phone = (TextView) findViewById(R.id.DETAILS_phone);
        mail = (TextView) findViewById(R.id.DETAILS_MAIL);
        add = (TextView) findViewById(R.id.DETAILS_ADD);
        profession = (TextView) findViewById(R.id.DETAILS_PRO);
        matrimo = (TextView) findViewById(R.id.DETAILS_MATRI);
        dateannif = (TextView) findViewById(R.id.DETAILS_DATEANNIF);
        imgcolorphoto = (AvatarImageView) findViewById(R.id.DETAILS_circle);

        if (extras!=null){
            String usernamedetail = extras.getPrenom() + " " + extras.getNom(),
                    memberphone = extras.getTelephone(), membertel = extras.getTelephone(),
            membermail = extras.getEmail(), memberadd = extras.getAddresse(), memberprofe = extras.getProfession(),
            membermatri = extras.getSituationmatri(), memberdateannif = extras.getDateannif();
            name.setText(usernamedetail);
            phone.setText(memberphone);
            mail.setText(membermail);
            add.setText(memberadd);
            profession.setText(memberprofe);
            matrimo.setText(membermatri);
            dateannif.setText(memberdateannif);

            if (extras.getPhotoprofile()==null){
//            Toast(item.getmColor());
                imgcolorphoto.setState(AvatarImageView.SHOW_INITIAL);
                GradientDrawable bgShape = (GradientDrawable) imgcolorphoto.getBackground();

//          ColorDrawable bgShape = (ColorDrawable) imgcolorphoto.getBackground();
                bgShape.setColor(Color.parseColor(extras.getmColor()));

                String fl = extras.getPrenom().substring(0,1);
                imgcolorphoto.setText(fl);
            }else {
                imgcolorphoto.setState(AvatarImageView.SHOW_IMAGE);
                byte[] imgbyte = extras.getPhotoprofile();
                Bitmap bitmap = BitmapFactory.decodeByteArray(imgbyte, 0, imgbyte.length);
//                imgcolorphoto.setImageBitmap(bitmap);
                imgcolorphoto.setImageIcon(Icon.createWithBitmap(bitmap));
            }
//            GradientDrawable bgShape = (GradientDrawable) imgcolorphoto.getBackground();
//            bgShape.setColor(Color.parseColor("#50707F"));
//            try {
//                id = getHelper().getID(Member.class, memberphone);
//                Toast(memberphone);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }
    }



//    @Override
//    protected void onStart() {
//
//        super.onStart();
//
//        try {
//            extras = getHelper().getById(Member.class, id);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
////        try {
////             id = extras.getId();
////         }catch (Exception e) {
////             e.getMessage();
////         }
//
//        name = (TextView) findViewById(R.id.DETAILS_name);
//        phone = (TextView) findViewById(R.id.DETAILS_phone);
//        mail = (TextView) findViewById(R.id.DETAILS_MAIL);
//        add = (TextView) findViewById(R.id.DETAILS_ADD);
//        profession = (TextView) findViewById(R.id.DETAILS_PRO);
//        matrimo = (TextView) findViewById(R.id.DETAILS_MATRI);
//        dateannif = (TextView) findViewById(R.id.DETAILS_DATEANNIF);
//        imgcolorphoto = (CircleImageView) findViewById(R.id.DETAILS_circle);
//
//        if (extras != null) {
//            String usernamedetail = extras.getPrenom() + " " + extras.getNom(),
//                    memberphone = extras.getTelephone(), membertel = extras.getTelephone(),
//                    membermail = extras.getEmail(), memberadd = extras.getAddresse(), memberprofe = extras.getProfession(),
//                    membermatri = extras.getSituationmatri(), memberdateannif = extras.getDateannif();
//            name.setText(usernamedetail);
//            phone.setText(memberphone);
//            mail.setText(membermail);
//            add.setText(memberadd);
//            profession.setText(memberprofe);
//            matrimo.setText(membermatri);
//            dateannif.setText(memberdateannif);
//
//            if (extras.getPhotoprofile()==null){
////            Toast(item.getmColor());
//                GradientDrawable bgShape = (GradientDrawable) imgcolorphoto.getBackground();
//
////          ColorDrawable bgShape = (ColorDrawable) imgcolorphoto.getBackground();
//                bgShape.setColor(Color.parseColor(extras.getmColor()));
//            }else {
//                byte[] imgbyte = extras.getPhotoprofile();
//                Bitmap bitmap = BitmapFactory.decodeByteArray(imgbyte, 0, imgbyte.length);
//                imgcolorphoto.setImageBitmap(bitmap);
//            }
////            GradientDrawable bgShape = (GradientDrawable) imgcolorphoto.getBackground();
////            bgShape.setColor(Color.parseColor("#50707F"));
//        }
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_member_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_del:
//                try {
//                    Toast(String.valueOf(id));
//                   m = getHelper().getById(Member.class, 1);
//                   Toast(String.valueOf(m));
//                } catch (SQLException e) {
//                    Toast("Erruer id");
//                    e.printStackTrace();
//                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Confirmer la suppression")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    if (getHelper().deleteById(Member.class, id)>0){
                                        Toast("Suppression effectue");
                                    }
                                } catch (SQLException e) {
                                    Toast("Erreur de suppression");
                                    e.printStackTrace();
                                }finally {
                                    Intent intent = new Intent(getApplicationContext(), SlideActivity.class);
                                    startActivity(intent);
                                }
//                                setResult(RESULT_OK);
                                finish();
                            }
                        });
                builder.create().show();
                break;
            case R.id.action_edit:
                Intent intent = new Intent(this, EditAddActivity.class);

                intent.putExtra("EDITINFO", id);
                startActivityForResult(intent,EDIT_OK);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), SlideActivity.class);
        startActivity(intent);

        super.onBackPressed();
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(resultCode == RESULT_OK  && requestCode == EDIT_OK){
//            Intent intent = new Intent(this, SlideActivity.class);
//            startActivity(intent);
////            finish();
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
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
                    OpenHelperManager.getHelper(getApplicationContext(), DBHelper.class);
        }
        return databaseHelper;
    }
//    public boolean deleteMember(Member member) {
//        try {
//           int iddel = getHelper().deleteById(Member.class, 1);
//            Toast(getString(R.string.sucessdel)+" "+ iddel);
//            return true;
//        } catch (SQLException e) {
//            Toast(getString(R.string.error_del));
//            e.printStackTrace();
//        }
//        return false;
//    }
    public void Toast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
