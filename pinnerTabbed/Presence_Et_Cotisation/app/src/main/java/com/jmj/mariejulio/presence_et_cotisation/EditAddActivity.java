package com.jmj.mariejulio.presence_et_cotisation;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.abdularis.civ.AvatarImageView;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import java.util.Random;

import Data.DBHelper;
import Model.Member;
import Utils.DatePickerFragment;
import de.hdodenhof.circleimageview.CircleImageView;

public class EditAddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private AlertDialog dialog;
    private AvatarImageView image;
    private final int GALERY=0;
    private final int APP_PHOTO=1;
    Button valider;
    private Member extras;
    private AutoCompleteTextView name, prenom, phone, mail, add, profession, matrimo, dateannif;
    private String nomget, prenomget, telget, mailget, addresseget, professionget, matriget,dateannifget, colorprofil;
    ImageButton imgbt;

    private DBHelper databaseHelper = null;

    int idupdte;

    byte[] imgdata, photoprofile;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_add);
        image=(AvatarImageView) findViewById(R.id.uploadimg);

        image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                sup();
                return true;
            }
        });

        idupdte = getIntent().getIntExtra("EDITINFO", 0);

        try {
            extras = getHelper().getById(Member.class, idupdte);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        name = (AutoCompleteTextView) findViewById(R.id.editnom);
        prenom = (AutoCompleteTextView) findViewById(R.id.editprenom);
        phone = (AutoCompleteTextView) findViewById(R.id.edittel);
        mail = (AutoCompleteTextView) findViewById(R.id.editmail);
        add = (AutoCompleteTextView) findViewById(R.id.editadd);
        profession = (AutoCompleteTextView) findViewById(R.id.editprofession);
        matrimo = (AutoCompleteTextView) findViewById(R.id.editmatri);
        dateannif = (AutoCompleteTextView) findViewById(R.id.editdateannif);

        imgbt = (ImageButton) findViewById(R.id.imgdte);

        imgbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        if (extras!=null) {
            String usernamedetail = extras.getPrenom(), nom = extras.getNom(),
                    memberphone = extras.getTelephone(), membertel = extras.getTelephone(),
                    membermail = extras.getEmail(), memberadd = extras.getAddresse(), memberprofe = extras.getProfession(),
                    membermatri = extras.getSituationmatri(), memberdateannif = String.valueOf(extras.getDateannif());
            name.setText(nom);
            prenom.setText(usernamedetail);
            phone.setText(memberphone);
            mail.setText(membermail);
            add.setText(memberadd);
            profession.setText(memberprofe);
            matrimo.setText(membermatri);
            dateannif.setText(memberdateannif);

            if (extras.getPhotoprofile()==null){
//            Toast(item.getmColor());
                image.setState(AvatarImageView.SHOW_INITIAL);
                GradientDrawable bgShape = (GradientDrawable) image.getBackground();

//          ColorDrawable bgShape = (ColorDrawable) image.getBackground();
                bgShape.setColor(Color.parseColor(extras.getmColor()));

                String fl = extras.getPrenom().substring(0,1);
                image.setText(fl);

            }else {
                image.setState(AvatarImageView.SHOW_IMAGE);
                byte[] imgbyte = extras.getPhotoprofile();
                Bitmap bitmap = BitmapFactory.decodeByteArray(imgbyte, 0, imgbyte.length);
//                image.setImageBitmap(bitmap);
                image.setImageIcon(Icon.createWithBitmap(bitmap));
//                image.setState(AvatarImageView.SHOW_IMAGE);

            }

//            try {
//                idupdte = getHelper().getID(Member.class, extras.getTelephone());
//                Toast(String.valueOf(idupdte));
//                memberupdate = getHelper().getById(Member.class, idupdte);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }
        valider = (Button) findViewById(R.id.editmembervalidate);



        valider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                initControls();
                if (!(nomget.equals("") || prenomget.equals("") || telget.equals(""))) {
                    try {
                        getHelper().createOrUpdate(extras);
                        Intent intent = new Intent(getApplicationContext(), SlideActivity.class);
                        startActivity(intent);
//                        setResult(RESULT_OK);
                        Toast("Modification effectue");
                        finish();
                    } catch (SQLException e) {
                        Toast("Erreur de modification");
                        Intent intent = new Intent(getApplicationContext(), SlideActivity.class);
                        startActivity(intent);
                        e.printStackTrace();
                    }
                }else {
                    Toast(getString(R.string.info_ask));
                    setResult(RESULT_OK);
                }
            }
        });

    }

    private void sup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmer la suppression")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        GradientDrawable bgShape = (GradientDrawable) image.getBackground();
//
////          ColorDrawable bgShape = (ColorDrawable) holder.memberimage.getBackground();
//                        bgShape.setColor(Color.parseColor(generiteColor()));
                        Toast("Aucune image");
                        image.setImageIcon(null);
                        image.setState(AvatarImageView.SHOW_INITIAL);
//                        image.setBackgroundColor(Color.parseColor("#e4dbdb"));
                        bgShape.setColor(Color.parseColor("#e4dbdb"));
                        image.setText("?");

                    }
                });
        builder.create().show();
    }


    public void initControls() {

        nomget = name.getText().toString().trim();
        prenomget = prenom.getText().toString().trim();
        telget = phone.getText().toString().trim();
        mailget = mail.getText().toString().trim();
        addresseget = add.getText().toString().trim();
        professionget = profession.getText().toString().trim();
        matriget = matrimo.getText().toString().trim();
        dateannifget = dateannif.getText().toString().trim();

        try {
            Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            imgdata = baos.toByteArray();
        }catch (Exception e) {
            imgdata = null;
        }

        //image save
        if (imgdata==null) {
            photoprofile = null;
            colorprofil = generiteColor();
        }else{
            photoprofile  = imgdata;
            colorprofil = null;
        }

         extras = new Member(idupdte,nomget, prenomget, telget,mailget,addresseget,professionget,matriget,dateannifget, photoprofile, colorprofil);
    }

    private String generiteColor() {
        String[] colors = {"green","yellow", "red", "#78909C", "#90A4AE", "#0000FF", "#FF00FF", "#00FFFF"};
        Random r = new Random();
        int randomColor = r.nextInt(colors.length);
        return colors[randomColor];
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
                    OpenHelperManager.getHelper(getApplicationContext(), DBHelper.class);
        }
        return databaseHelper;
    }

    public void opendialogtakepic(View v) {
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        View view=getLayoutInflater().inflate(R.layout.dialogtakepic,null);
        LinearLayout camera=(LinearLayout) view.findViewById(R.id.cam);
        LinearLayout galerie=(LinearLayout) view.findViewById(R.id.gal);
        alert.setView(view);
        dialog = alert.create();
        dialog.show();

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,APP_PHOTO);
                dialog.dismiss();
            }
        });
        galerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent,GALERY);
                dialog.dismiss();

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //recuperation des external component

        if (requestCode==GALERY && data!=null){

            try {
                Uri contentUri=data.getData();
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),contentUri);
//                image.setImageBitmap(bitmap);
                image.setState(AvatarImageView.SHOW_IMAGE);
                image.setImageIcon(Icon.createWithBitmap(bitmap));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (requestCode==APP_PHOTO && data!=null){

            try {
                Bitmap bitmap= (Bitmap)data.getExtras().get("data");
//                image.setImageBitmap(bitmap);
                image.setState(AvatarImageView.SHOW_IMAGE);
                image.setImageIcon(Icon.createWithBitmap(bitmap));

            }catch (Exception e) {
                Toast("Aucune image prise");
            }


        }
    }

    public void saveedit(View v) {
        Toast(getString(R.string.successadd));
    }

    public void Toast(String msg){
        Toast.makeText(getBaseContext(),msg,Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");

        dateannif.setText(String.valueOf(s.format(c.getTime())));
    }

//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(getApplicationContext(), MemberDetailsActivity.class);
//        startActivity(intent);
//
//        super.onBackPressed();
//    }
}
