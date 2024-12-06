package com.example.foodapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Comment_Item;
import com.example.foodapp.R;

import java.util.List;

public class CommentRecycleviewAdapter extends RecyclerView.Adapter<CommentRecycleviewAdapter.ViewHolder> {

    private List<Comment_Item> cmtList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public CommentRecycleviewAdapter(List<Comment_Item> cmtList, Context context) {
        this.cmtList = cmtList;
        this.context = context;
    }

    // Định nghĩa OnItemClickListener interface
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Phương thức để truyền OnItemClickListener từ bên ngoài vào Adapter
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout cho từng item
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view, onItemClickListener); // Truyền listener vào ViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Gán dữ liệu cho các view trong item
        Comment_Item commentItem = cmtList.get(position);
        holder.imageView.setImageResource(commentItem.getImg());
        holder.textViewName.setText(commentItem.getName());
        holder.textViewCmt.setText(commentItem.getCmt());
    }

    @Override
    public int getItemCount() {
        return cmtList.size(); // Trả về số lượng phần tử trong danh sách
    }

    // ViewHolder đại diện cho từng item trong RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewName;
        public TextView textViewCmt;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgAva);
            textViewName = itemView.findViewById(R.id.txtName);
            textViewCmt = itemView.findViewById(R.id.txtComment);

            // Xử lý sự kiện click cho từng item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position); // Gọi lại onItemClick với vị trí hiện tại
                        }
                    }
                }
            });
        }
    }
}
