package com.jmj.mariejulio.presence_et_cotisation.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.abdularis.civ.AvatarImageView;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.jmj.mariejulio.presence_et_cotisation.MainActivity;
import com.jmj.mariejulio.presence_et_cotisation.MemberDetailsActivity;
import com.jmj.mariejulio.presence_et_cotisation.R;
import com.jmj.mariejulio.presence_et_cotisation.SlideActivity;
import com.jmj.mariejulio.presence_et_cotisation.fragment.MemberFragment;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Data.DBHelper;
import Model.Member;
import de.hdodenhof.circleimageview.CircleImageView;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {
    public static final int DELETE_OK =4 ;
    Context context;
    ArrayList<Member> listmembers;
    private DBHelper databaseHelper = null;


    public MemberAdapter(Context context, ArrayList<Member> listmembers) {
        this.context = context;
        this.listmembers = listmembers;
    }
    @NonNull
    @Override
    public MemberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_members_delegate, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MemberAdapter.ViewHolder holder, int position) {
        Member item = listmembers.get(position);
        holder.prenom.setText(item.getPrenom());
        holder.tel.setText(item.getTelephone());

        if (item.getPhotoprofile()==null){
//            Toast(item.getmColor());
            holder.memberimage.setState(AvatarImageView.SHOW_INITIAL);
            String fl = item.getPrenom().substring(0,1);
            holder.memberimage.setText(fl);
            GradientDrawable bgShape = (GradientDrawable) holder.memberimage.getBackground();

//          ColorDrawable bgShape = (ColorDrawable) holder.memberimage.getBackground();
        bgShape.setColor(Color.parseColor(item.getmColor()));

        }else {
            holder.memberimage.setState(AvatarImageView.SHOW_IMAGE);
            byte[] imgbyte = item.getPhotoprofile();
            Bitmap bitmap = BitmapFactory.decodeByteArray(imgbyte, 0, imgbyte.length);
            holder.memberimage.setImageIcon(Icon.createWithBitmap(bitmap));
        }
    }

    @Override
    public int getItemCount() {
        return listmembers.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        AvatarImageView memberimage;
        TextView prenom;
        TextView tel;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            memberimage = (AvatarImageView) itemView.findViewById(R.id.MEMBER_circle);
            prenom = (TextView) itemView.findViewById(R.id.MEMBER_name);
            tel = (TextView) itemView.findViewById(R.id.MEMBER_phone);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), MemberDetailsActivity.class);
            int position = getAdapterPosition();
            List<Member> memberList = listmembers;
            Member member = new Member(memberList.get(position).getId(),memberList.get(position).getNom(),memberList.get(position).getPrenom(),
                    memberList.get(position).getTelephone(), memberList.get(position).getEmail(),
                    memberList.get(position).getAddresse(), memberList.get(position).getProfession(),
                    memberList.get(position).getSituationmatri(), memberList.get(position).getDateannif());

            intent.putExtra("MEMBERINFO", member.getId());

            ActivityOptionsCompat options =  ActivityOptionsCompat.makeSceneTransitionAnimation( (Activity) v.getContext(),
                    new Pair<View, String>(v.findViewById(R.id.MEMBER_circle), context.getString(R.string.transition_name_circle)),
                    new Pair<View, String>(v.findViewById(R.id.MEMBER_name), context.getString(R.string.transition_name_name)),
                    new Pair<View, String>(v.findViewById(R.id.MEMBER_phone), context.getString(R.string.transition_name_phone))
                    );

            ActivityCompat.startActivity(v.getContext(), intent, options.toBundle());
//            ActivityCompat.startActivityForResult((Activity) v.getContext(), intent, DELETE_OK, options.toBundle());
//            ((Activity) v.getContext()).finish();

//            Toast.makeText(v.getContext().getApplicationContext(),listmembers.get(position).getPrenom(), Toast.LENGTH_SHORT).show();
        }




        @Override
        public boolean onLongClick(View v) {
            supmember();
//            Toast.makeText(v.getContext().getApplicationContext(),listmembers.get(position).getPrenom()+ " " + listmembers.get(position).getNom(), Toast.LENGTH_SHORT).show();
            return true;
        }

        private void supmember() {
//            int position = getAdapterPosition();
//            final List<Member> memberList = listmembers;
//            final Member item = listmembers.get(position);
//            AlertDialog.Builder builder = new AlertDialog.Builder(getContext().getApplicationContext());
//            builder.setTitle("Confirmer la suppression")
//                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            try {
//                                if (getHelper().deleteById(Member.class, item.getId())>0){
//                                    Toast("Suppression effectue");
//                                }
//                            } catch (SQLException e) {
//                                Toast("Erreur de suppression");
//                                e.printStackTrace();
//                            }finally {
//                                try {
//                                    listmembers.addAll(getHelper().getAllOrdered(Member.class, "prenom", true));
//                                } catch (SQLException e) {
//                                    e.printStackTrace();
//                                }
//
////                                MemberAdapter adapter = new MemberAdapter(context, listmembers);
////                                adapter.notifyDataSetChanged();
////
////                                MemberFragment.recyclerView.setAdapter(adapter);
//                                MemberFragment.memberList = listmembers;
//                                MemberFragment.adapter.notifyDataSetChanged();
//                            }
//                        }
//                    });
//            builder.create().show();
        }
    }



    private DBHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper =
                    OpenHelperManager.getHelper(getContext().getApplicationContext(), DBHelper.class);
        }
        return databaseHelper;
    }

    public Context getContext() {
        return context;
    }

    public void Toast(String msg){
        Toast.makeText(getContext().getApplicationContext(),msg,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);

        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}
