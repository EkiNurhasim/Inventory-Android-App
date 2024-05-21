package com.example.perawatanrupbasan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perawatanrupbasan.api.ApiClient;
import com.example.perawatanrupbasan.api.ApiInterface;
import com.example.perawatanrupbasan.model.bendasitaan.BendaSitaan;
import com.example.perawatanrupbasan.model.bendasitaan.BendaSitaanData;
import com.example.perawatanrupbasan.model.login.Login;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<BendaSitaanData> listData = new ArrayList<>();
    private SwipeRefreshLayout srlData;
    private ProgressBar pbData;
    private FloatingActionButton fabTambah;

    String user_id, user, mail;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(MainActivity.this);
        if(!sessionManager.isLoggedIn()){
            moveToLogin();
        }

        rvData = findViewById(R.id.rv_data);
        srlData = findViewById(R.id.srl_data);
        pbData = findViewById(R.id.pb_data);
        fabTambah = findViewById(R.id.fab_tambah);
        user_id = sessionManager.getUserDetail().get(SessionManager.USER_ID);
        user = sessionManager.getUserDetail().get(SessionManager.USERNAME);
        mail = sessionManager.getUserDetail().get(SessionManager.EMAIL);

        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);


        retrieveData();
        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                retrieveData();
                srlData.setRefreshing(false);
            }
        });

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateBendaSitaanAcitivity.class);
                intent.putExtra("USER_ID", user_id);
                startActivity(intent);

            }
        });
    }

    public void retrieveData() {
        try {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<BendaSitaan> bendaSitaanCall = apiInterface.bendaSitaanResponse(user_id);

            bendaSitaanCall.enqueue(new Callback<BendaSitaan>() {
                @Override
                public void onResponse(Call<BendaSitaan> call, Response<BendaSitaan> response) {
                    if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                        listData = response.body().getData();
                        adData = new AdapterDataBendaSitaan(MainActivity.this, listData);
                        rvData.setAdapter(adData);
                        adData.notifyDataSetChanged();
                        pbData.setVisibility(View.INVISIBLE);
                    }else{
                        Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BendaSitaan> call, Throwable t) {
                    pbData.setVisibility(View.INVISIBLE);
                }
            });

        }catch (Exception e){
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }


    private void moveToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionLogout:
                sessionManager.logoutSession();
                moveToLogin();
                break;
            case R.id.actionProfile:
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("USER", user);
                intent.putExtra("MAIL", mail);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
