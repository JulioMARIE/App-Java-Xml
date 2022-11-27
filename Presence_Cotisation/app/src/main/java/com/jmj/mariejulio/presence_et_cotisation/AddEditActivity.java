package com.jmj.mariejulio.presence_et_cotisation;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.abdularis.civ.AvatarImageView;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import Data.DBHelper;
import Model.Member;
import Utils.DatePickerFragment;
public class AddEditActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private AlertDialog dialog;
    private AvatarImageView image;
    private final int GALERY=0;
    private final int APP_PHOTO=1;
    Button valider;
    private DBHelper databaseHelper = null;

    private AutoCompleteTextView nom, prenom, tel, mail, addresse, profession, matri, dateannif;
    private Member member;
    private String nomget, prenomget, telget, mailget, addresseget, professionget, matriget, dateannifget, colorprofil;
    private byte[] photoprofile;
    private ImageButton imgbt;

    byte[] imgdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
//        image=(ImageView) findViewById(R.id.uploadimg);

        initControls();
        GradientDrawable bgShape = (GradientDrawable) image.getBackground();
        bgShape.setColor(Color.parseColor("#e4dbdb"));
        image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                supadd();
                return true;
            }
        });

        imgbt = (ImageButton) findViewById(R.id.imgdte);

        imgbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }

    private void supadd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmer la suppression")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GradientDrawable bgShape = (GradientDrawable) image.getBackground();
//
////          ColorDrawable bgShape = (ColorDrawable) image.getBackground();
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
        nom = (AutoCompleteTextView) findViewById(R.id.editnom);
        prenom = (AutoCompleteTextView) findViewById(R.id.editprenom);
        tel = (AutoCompleteTextView) findViewById(R.id.edittel);
        mail = (AutoCompleteTextView) findViewById(R.id.editmail);
        addresse = (AutoCompleteTextView) findViewById(R.id.editadd);
        profession = (AutoCompleteTextView) findViewById(R.id.editprofession);
        matri = (AutoCompleteTextView) findViewById(R.id.editmatri);
        dateannif = (AutoCompleteTextView) findViewById(R.id.editdateannif);
        image = (AvatarImageView) findViewById(R.id.uploadimgadd);
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
//                image.setState(AvatarImageView.SHOW_IMAGE);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (requestCode==APP_PHOTO && data!=null){

//
            try {
                Bitmap bitmap= (Bitmap)data.getExtras().get("data");
                image.setState(AvatarImageView.SHOW_IMAGE);
                image.setImageIcon(Icon.createWithBitmap(bitmap));
            }catch (Exception e) {
                Toast("Aucune image prise");
            }

        }
    }

    public void saveadd(View v) {
        nomget = nom.getText().toString().trim();
        prenomget = prenom.getText().toString().trim();
        telget = tel.getText().toString().trim();
        mailget = mail.getText().toString().trim();
        addresseget = addresse.getText().toString().trim();
        professionget = profession.getText().toString().trim();
        matriget = matri.getText().toString().trim();
        dateannifget = dateannif.getText().toString().trim();


        //save image
//        image.setDrawingCacheEnabled(true);
//
//        image.buildDrawingCache();


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

        member = new Member(nomget, prenomget, telget,mailget,addresseget,professionget,
                matriget,dateannifget, photoprofile,colorprofil);

        if (!(nomget.equals("") || prenomget.equals("") || telget.equals(""))) {
            try {
                getHelper().createOrUpdate(member);
                Toast(getString(R.string.successadd));
                setResult(RESULT_OK);
                finish();
            } catch (SQLException e) {
                Toast(getString(R.string.fail_add_member));
                e.printStackTrace();
                setResult(RESULT_OK);
                finish();
            }
        }else {
            Toast(getString(R.string.info_ask));
            setResult(RESULT_OK);
        }

    }

    public String generiteColor() {
        String[] colors = {"green","yellow", "red", "#78909C", "#90A4AE", "#0000FF", "#FF00FF", "#00FFFF"};
        Random r = new Random();
        int randomColor = r.nextInt(colors.length);
        return colors[randomColor];
    }

    public void Toast(String msg){
        Toast.makeText(getBaseContext(),msg,Toast.LENGTH_SHORT).show();

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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");

        dateannif.setText(String.valueOf(s.format(c.getTime())));
    }
}
