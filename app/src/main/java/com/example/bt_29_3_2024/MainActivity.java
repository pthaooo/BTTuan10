package com.example.bt_29_3_2024;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt_29_3_2024.api.APIService;
import com.example.bt_29_3_2024.api.RetrofitClient;
import com.example.bt_29_3_2024.helpers.CategoryAdapter;
import com.example.bt_29_3_2024.model.Category;
import com.example.bt_29_3_2024.model.SubCategoryActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String NEXT_SCREEN = "details_screen";
    public static int ok = -1;
    public static int cnt = 0;
    RecyclerView rcCate;
    CategoryAdapter categoryAdapter;
    APIService apiService;
    List<Category> categoryList;
    Button btnNewMeal;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();
        getCategory();

        mHandler = new Handler();

        mHandler.postDelayed(() -> cnt++, 500);
        btnNewMeal = findViewById(R.id.button);
//        btnNewMeal.setOnClickListener(v -> addNewMeal());
    }

    private void getCategory() {
        apiService = RetrofitClient.getRetrofit()
                                   .create(APIService.class);
        apiService.getCategoryAll()
                  .enqueue(new Callback<List<Category>>() {
                      @Override
                      public void onResponse(
                              @NonNull Call<List<Category>> call,
                              @NonNull Response<List<Category>> response
                      ) {
                          if (response.isSuccessful()) {
                              categoryList = response.body();
                              categoryAdapter = new CategoryAdapter(
                                      MainActivity.this,
                                      categoryList
                              );
                              rcCate.setHasFixedSize(true);

                              RecyclerView.LayoutManager layoutManager =
                                      new LinearLayoutManager(
                                              getApplicationContext(),
                                              LinearLayoutManager.VERTICAL,
                                              false
                                      );

                              rcCate.setLayoutManager(layoutManager);
                              rcCate.setAdapter(categoryAdapter);

                              categoryAdapter.notifyDataSetChanged();
                              
                              categoryAdapter.setOnClickListener((position, model) -> {
                                  ok = model.getId();
                                  Log.e("TAG", "onResponse: " + ok);
                                  Intent intent = new Intent(
                                          MainActivity.this,
                                          SubCategoryActivity.class
                                  );
                                  // Passing the data to the
                                  // EmployeeDetails Activity
                                  intent.putExtra(NEXT_SCREEN, ok);
                                  startActivity(intent);
                              });
                          } else {
                              int statusCode = response.code();
                              Log.e("Error", "onResponse: " + statusCode);
                          }
                      }

                      @Override
                      public void onFailure(
                              @NonNull Call<List<Category>> call,
                              @NonNull Throwable t
                      ) {
                          Log.e("Error", "onFailure: " + t.getMessage());
                      }
                  });
    }

    private void setUpUI() {
        rcCate = findViewById(R.id.rc_category);
//        bottomNavigationView = findViewById(R.id.btn_navigation);
    }

}