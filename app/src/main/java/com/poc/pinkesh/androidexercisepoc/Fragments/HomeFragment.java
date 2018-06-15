package com.poc.pinkesh.androidexercisepoc.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import com.poc.pinkesh.androidexercisepoc.Adapter.UserAdapter;
import com.poc.pinkesh.androidexercisepoc.R;
import com.poc.pinkesh.androidexercisepoc.Rest.ApiClient;
import com.poc.pinkesh.androidexercisepoc.Rest.ApiInterface;
import com.poc.pinkesh.androidexercisepoc.Usermodels.Rows;
import com.poc.pinkesh.androidexercisepoc.Usermodels.UserResponses;
import com.poc.pinkesh.androidexercisepoc.Utilities.Network;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;


import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class HomeFragment extends Fragment  {

    View view;
    private ArrayList<Rows> userlist;
    private RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(Network.checkNetworkAvailability(getContext())) {

            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            Call<UserResponses> call = apiService.getUsersContent();
            call.enqueue(new Callback<UserResponses>() {
                @Override
                public void onResponse(Call<UserResponses> call, retrofit2.Response<UserResponses> response) {
                    int statusCode = response.code();
                    ArrayList<Rows> rows = response.body().getRows();
                    UserAdapter userAdapter = new UserAdapter(getContext(), rows);

                    recyclerView = (RecyclerView) view.findViewById(R.id.ItemListView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(userAdapter);


                }

                @Override
                public void onFailure(Call<UserResponses> call, Throwable t) {
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                }
            });

            mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    RefreshData();
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });
        }
        else{
            Toast.makeText(getActivity(), "No Network Connectivity, please check and try again", Toast.LENGTH_SHORT).show();
        }
    }


    public void RefreshData(){

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponses> call = apiService.getUsersContent();
        call.enqueue(new Callback<UserResponses>() {
            @Override
            public void onResponse(Call<UserResponses> call, retrofit2.Response<UserResponses> response) {
                int statusCode = response.code();
                ArrayList<Rows> rows = response.body().getRows();
                UserAdapter userAdapter = new UserAdapter(getContext(), rows);

                recyclerView = (RecyclerView) view.findViewById(R.id.ItemListView);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(userAdapter);


            }

            @Override
            public void onFailure(Call<UserResponses> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        view = null;

    }




}
