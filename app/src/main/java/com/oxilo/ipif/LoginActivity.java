package com.oxilo.ipif;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hbb20.CountryCodePicker;
import com.oxilo.ipif.modal.login.UserData;
import com.oxilo.ipif.network.api.ServiceFactory;
import com.oxilo.ipif.network.api.WebService;

import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    CountryCodePicker ccp;
    EditText editTextCarrierNumber;
    boolean isValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        editTextCarrierNumber = (EditText) findViewById(R.id.editText_carrierNumber);

        ccp.registerCarrierNumberEditText(editTextCarrierNumber);

        ccp.setPhoneNumberValidityChangeListener(new CountryCodePicker.PhoneNumberValidityChangeListener() {
            @Override
            public void onValidityChanged(boolean isValidNumber) {
                // your code
                isValid = isValidNumber;
            }
        });
    }

    private void login() {
        try {
            WebService webService = ServiceFactory.createRetrofitService(WebService.class);
            webService.login(ccp.getFormattedFullNumber(), "Android")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Response<ResponseBody>>() {
                        @Override
                        public void accept(Response<ResponseBody> responseBodyResponse) throws Exception {
                            try {
                                String sd = new String(responseBodyResponse.body().bytes());
                                JSONObject mapping = new JSONObject(sd);
                                ObjectMapper mapper = new ObjectMapper();
                                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                                UserData userData = mapper.readValue(mapping.getString("user_data"), new TypeReference<UserData>() {
                                });
                                ApplicationController.getInstance().getAppPrefs().putObject("LOGIN",userData);
                                ApplicationController.getInstance().getAppPrefs().commit();
//
                                if (userData.getIsVerified().equals("0")) {
                                    Intent i = new Intent(LoginActivity.this, VerificationActivity.class);
                                    i.putExtra("USER", userData);
                                    i.putExtra("N",ccp.getFormattedFullNumber());
                                    startActivity(i);
                                } else {
                                    Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
                                    i.putExtra("USER", userData);
                                    startActivity(i);
                                }

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                            throwable.printStackTrace();
                        }
                    });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @OnClick(R.id.sign_in)
    public void onViewClicked() {
        if (isValid)
            login();
        else
            Toast.makeText(LoginActivity.this,"Not a valid number",Toast.LENGTH_SHORT).show();
    }
}
