package com.example.bt_29_3_2024.model;

import static com.example.bt_29_3_2024.MainActivity.NEXT_SCREEN;
import static com.example.bt_29_3_2024.MainActivity.cnt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.bt_29_3_2024.R;
import com.example.bt_29_3_2024.helpers.SubCategoryAdapter;

import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class SubCategoryActivity extends AppCompatActivity {
    RecyclerView rcvCategory;
    SubCategoryAdapter subCategoryAdapter;
    ImageButton imageButton;
    ArrayList<SubCategory> temp = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        setUpUI();
        imageButton.setOnClickListener(v -> getData());


        MeowBottomNavigation bottomNavigation = findViewById(R.id.me);
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

    @SuppressLint("NotifyDataSetChanged")
    public void getData() {
        Intent intent = getIntent();
        int idCategory = intent.getIntExtra(NEXT_SCREEN, 1);
        new AllSubCategories(idCategory).getAllSubCategories();
        temp.clear();
        temp.addAll(AllSubCategories.ans);

        if (cnt != 0) {
            subCategoryAdapter.notifyDataSetChanged();
            
            subCategoryAdapter.setOnClickListener((position, model) -> {
                startActivity(new Intent(
                        SubCategoryActivity.this,
                        DetailActivity.class
                ));
            });
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setUpUI() {
        rcvCategory = findViewById(R.id.rcv_category);
        imageButton = findViewById(R.id.imgBtn_syc);

        temp.add(new SubCategory(
                "1",
                "SubCategory 1",
                "https://phukienpanda.com/wp-content/uploads/2022/11/phim-du-phuong-hanh.jpg",
                "1",
                "1"
        ));
        temp.add(new SubCategory(
                "1",
                "SubCategory 1",
                "https://i.pinimg.com/originals/f6/5c/a1/f65ca179444200f380144b3ad432c800.jpg",
                "1",
                "1"
        ));

        subCategoryAdapter = new SubCategoryAdapter(this, temp);
        rcvCategory.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager =
                new GridLayoutManager(getApplicationContext(), 2);

        rcvCategory.setLayoutManager(layoutManager);
        rcvCategory.setAdapter(subCategoryAdapter);

        subCategoryAdapter.notifyDataSetChanged();

    }
}