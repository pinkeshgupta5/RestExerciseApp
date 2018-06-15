package com.poc.pinkesh.androidexercisepoc;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import com.poc.pinkesh.androidexercisepoc.Fragments.HomeFragment;
import com.poc.pinkesh.androidexercisepoc.Rest.ApiClient;
import com.poc.pinkesh.androidexercisepoc.Rest.ApiInterface;
import com.poc.pinkesh.androidexercisepoc.Usermodels.UserResponses;
import retrofit2.Call;
import retrofit2.Callback;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponses> call = apiService.getUsersContent();
        call.enqueue(new Callback<UserResponses>() {
            @Override
            public void onResponse(Call<UserResponses> call, retrofit2.Response<UserResponses> response) {
                int statusCode = response.code();
                String Title = response.body().getTitle();
                Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
                mActionBarToolbar.setTitle(Title);
                setSupportActionBar(mActionBarToolbar);


            }

            @Override
            public void onFailure(Call<UserResponses> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });





        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        HomeFragment homeFragment = new HomeFragment();

        fragmentTransaction.add(R.id.top_layout,homeFragment,"top");
        fragmentTransaction.addToBackStack("null");
        fragmentTransaction.commit();

    }
}
