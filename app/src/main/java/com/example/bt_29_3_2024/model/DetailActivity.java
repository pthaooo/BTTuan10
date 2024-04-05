package com.example.bt_29_3_2024.model;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.bt_29_3_2024.R;
import com.example.bt_29_3_2024.api.APIService;
import com.example.bt_29_3_2024.api.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    APIService apiService;
    ImageView imageViewDetail;
    TextView textViewPrice, textViewMeal, textViewArea, textViewCategory, textViewInstructions;
    NewMeal newMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        addNewMeal();

        imageViewDetail = findViewById(R.id.img_detail);
        textViewPrice = findViewById(R.id.tv_price);
        textViewInstructions = findViewById(R.id.tv_desc);
        textViewMeal = findViewById(R.id.tv_name);
        textViewCategory = findViewById(R.id.tv_soluong);

        MeowBottomNavigation bottomNavigation = findViewById(R.id.bar);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.infoprofile));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.shopping));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.blurcircular));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.settings));
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                //Toast.makeText(SubCategoryActivity.this, "Item click" + model.getId(), Toast.LENGTH_SHORT).show();


                return null;
            }
        });

        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                String name;
                if(model.getId() == 1){
                    name="Home";
                }else if(model.getId() == 2){
                    name="Profile";
                }else if(model.getId() == 3){
                    name="Shopping";
                }
                else if(model.getId() == 4){
                    name="Support";
                }else if(model.getId() == 5){
                    name="Settings";
                }
//                bottomNavigation.setCount(5, "9");
                return null;
            }
        });
    }




    private void addNewMeal() {
        apiService = RetrofitClient.getRetrofit()
                                   .create(APIService.class);
        apiService.getNewMeals(52765)
                  .enqueue(new Callback<ResponseBody>() {
                      @Override
                      public void onResponse(
                              @NonNull Call<ResponseBody> call,
                              @NonNull Response<ResponseBody> response
                      ) {
                          if (response.isSuccessful()) {
                              try {
                                  String res = response.body()
                                                       .string();

                                  JSONObject jsonObject =
                                          new JSONObject(res).getJSONArray("result")
                                                             .getJSONObject(0);

                                  newMeal = new NewMeal(
                                          jsonObject.getString("id"),
                                          jsonObject.getString("meal"),
                                          jsonObject.getString("area"),
                                          jsonObject.getString("category"),
                                          jsonObject.getString("instructions"),
                                          jsonObject.getString("strmealthumb"),
                                          jsonObject.getString("price")
                                  );

                                  Toast.makeText(
                                               DetailActivity.this,
                                               newMeal.getStrmealthumb(),
                                               Toast.LENGTH_SHORT
                                       )
                                       .show();

                                  Glide.with(DetailActivity.this)
                                       .load(newMeal.getStrmealthumb())
                                       .into(imageViewDetail);

                                  textViewMeal.setText(newMeal.getMeal());
                                  textViewPrice.setText(newMeal.getPrice());
                                  textViewInstructions.setText(newMeal.getInstructions());
                              } catch (IOException | JSONException e) {
                                  e.printStackTrace();
                              }
                          } else {
                              int statusCode = response.code();
                              Log.e("Error", "onResponse: " + statusCode);
                          }
                      }

                      @Override
                      public void onFailure(
                              @NonNull Call<ResponseBody> call,
                              @NonNull Throwable t
                      ) {
                          Log.e("Error", "onFailure: " + t.getMessage());
                      }
                  });
    }

}