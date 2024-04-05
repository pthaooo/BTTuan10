package com.example.bt_29_3_2024.helpers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bt_29_3_2024.R;
import com.example.bt_29_3_2024.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private final List<Category> categoryList;
    private final Context context;
    public Category ans;
    private OnClickListener onClickListener;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.item_category, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            MyViewHolder holder,
            @SuppressLint("RecyclerView") int position
    ) {
        ans = categoryList.get(position);
        holder.categoryName.setText(ans.getName());
        Glide.with(context)
             .load(ans.getImages())
             .into(holder.categoryImage);

        holder.itemView.setOnClickListener(v -> {
            if (onClickListener != null) {
                onClickListener.onClick(position, ans);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, Category model);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public final ImageView categoryImage;
        private final TextView categoryName;

        public MyViewHolder(View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.image_cate);
            categoryName = itemView.findViewById(R.id.tvNameCategory);

//            categoryImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Do something
//                    Toast.makeText(
//                                 context,
//                                 "You clicked on " + ans.getId(),
//                                 Toast.LENGTH_SHORT
//                         )
//                         .show();
//                }
//            });

        }
    }
}
