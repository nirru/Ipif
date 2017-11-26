package com.oxilo.ipif;

import android.Manifest;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.marchinram.rxgallery.RxGallery;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.functions.Consumer;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    CircleImageView profile_pic;
    Dialog dialog;
    TextView gallery, camera;
    Uri image_uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile_pic = (CircleImageView) findViewById(R.id.profile_pic);

        profile_pic.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_pic:
                dialog = new Dialog(ProfileActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialog.setContentView(R.layout.bottom_sheet_layout);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;  //Add animation

                gallery = (TextView) dialog.findViewById(R.id.photos);
                camera = (TextView) dialog.findViewById(R.id.camera);

                gallery.setOnClickListener(this);
                camera.setOnClickListener(this);

                dialog.show();

                break;

            case R.id.camera:
                dialog.dismiss();
                RxPermissions rxPermissions2 = new RxPermissions(ProfileActivity.this);
                rxPermissions2
                        .request(Manifest.permission.CAMERA)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean granted) throws Exception {
                                if (granted) {
                                RxGallery.photoCapture(ProfileActivity.this).subscribe(new Consumer<Uri>() {
                                    @Override
                                    public void accept(Uri uri) throws Exception {
                                        image_uri = uri;
                                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(ProfileActivity.this.getContentResolver(), uri);
                                    }
                                }, new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                        throwable.printStackTrace();
                                    }
                                });

                                } else {
                                    Log.e("TAG", "Permission Denied! ");
                                }

                            }
                        });

                break;
            case R.id.photos:
                dialog.dismiss();
                RxPermissions rxPermissions = new RxPermissions(ProfileActivity.this);
                rxPermissions
                        .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean granted) throws Exception {
                                if (granted) {
                                    RxGallery.gallery(ProfileActivity.this, false, RxGallery.MimeType.IMAGE).subscribe(new Consumer<List<Uri>>() {
                                        @Override
                                        public void accept(List<Uri> uris) throws Exception {
                                            image_uri = uris.get(0);
                                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uris.get(0));

                                        }
                                    }, new Consumer<Throwable>() {
                                        @Override
                                        public void accept(Throwable throwable) throws Exception {
                                            throwable.printStackTrace();
                                        }
                                    });

                                } else {
                                    Log.e("TAG", "Permission Denied! ");
                                }

                            }
                        });
                break;
        }
    }
}
