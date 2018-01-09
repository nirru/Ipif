package com.oxilo.ipif;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.marchinram.rxgallery.RxGallery;
import com.oxilo.ipif.activity.MyAccountActivity;
import com.oxilo.ipif.modal.login.UserData;
import com.oxilo.ipif.network.api.ServiceFactory;
import com.oxilo.ipif.network.api.WebService;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.json.JSONObject;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    CircleImageView profile_pic;
    Dialog dialog;
    TextView gallery, camera;
    Uri image_uri;
    @BindView(R.id.name)
    EditText name;
    private UserData userData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            userData = getIntent().getParcelableExtra("USER");
        }
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

    private void sendData(Uri uri) throws Exception {
        WebService service = ServiceFactory.createRetrofitService(WebService.class);
        File imageFile = null;
        MultipartBody.Part imageBody = null;
        MultipartBody.Part videoBody = null;
        RequestBody requestImageFile = null;
        Observable<Response<ResponseBody>> uploadProfile;

        if (uri != null) {
            imageFile = new File(uri.getPath());
            requestImageFile =
                    RequestBody.create(MediaType.parse("image/*"), imageFile);
            imageBody = MultipartBody.Part.createFormData("image", imageFile.getName(), requestImageFile);
        } else {


        }

        // add another part within the multipart request
//        Log.e("SUBTYPE:", "" + EventTracker.getInstance().getSub_type());
        String _name = name.getText().toString();
        RequestBody id = RequestBody.create(MediaType.parse("multipart/form-data"), userData.getUserId());
        RequestBody title = RequestBody.create(MediaType.parse("multipart/form-data"), _name);
        if (uri != null) {
            uploadProfile = service.uploadProfile(imageBody, title, id);
        } else {
            uploadProfile = service.uploadProfile(null, title, id);
        }

        uploadProfile.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<ResponseBody>>() {
                               @Override
                               public void accept(@NonNull Response<ResponseBody> responseBodyResponse) throws Exception {
                                   try {
                                       String sd = new String(responseBodyResponse.body().bytes());
                                       JSONObject mapping = new JSONObject(sd);
//                                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                                       if (mapping.getString("status").equals("1")) {

                                           Intent i = new Intent(ProfileActivity.this, MyAccountActivity.class);
                                           i.putExtra("A", 1);
                                           i.putExtra("USER",userData);
                                           startActivity(i);
                                       } else {
                                           Toast.makeText(ProfileActivity.this, "Updation fail", Toast.LENGTH_SHORT).show();
                                       }
                                   } catch (Exception ex) {
                                       ex.printStackTrace();
                                   }
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                throwable.printStackTrace();
                            }
                        });
    }

    @OnClick(R.id.done)
    public void onViewClicked() {
        try {
            sendData(image_uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
