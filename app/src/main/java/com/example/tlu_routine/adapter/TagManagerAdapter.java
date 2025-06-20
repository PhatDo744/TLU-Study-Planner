package com.example.tlu_routine.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlu_routine.R;
import com.example.tlu_routine.model.Tag;

import java.util.List;

public class TagManagerAdapter extends RecyclerView.Adapter<TagManagerAdapter.TagViewHolder> {

    private final List<Tag> tagList;
    private final Context context;

    public TagManagerAdapter(Context context, List<Tag> tagList) {
        this.context = context;
        this.tagList = tagList;
    }

    @NonNull
    @Override
    public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag_management, parent, false);
        return new TagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TagViewHolder holder, int position) {
        Tag tag = tagList.get(position);
        holder.bind(tag);
    }

    @Override
    public int getItemCount() {
        return tagList.size();
    }

    class TagViewHolder extends RecyclerView.ViewHolder {
        private final View tagColorDot;
        private final ImageView tagIcon;
        private final TextView tagName;
        private final ImageButton editButton;
        private final ImageButton deleteButton;

        public TagViewHolder(@NonNull View itemView) {
            super(itemView);
            tagColorDot = itemView.findViewById(R.id.tag_color_dot);
            tagIcon = itemView.findViewById(R.id.tag_icon);
            tagName = itemView.findViewById(R.id.tag_name);
            editButton = itemView.findViewById(R.id.btn_edit_tag);
            deleteButton = itemView.findViewById(R.id.btn_delete_tag);
        }

        public void bind(final Tag tag) {
            tagName.setText(tag.getName());
            tagIcon.setImageResource(tag.getIconResId());

            // Đặt màu cho chấm màu
            Drawable unwrappedDrawable = tagColorDot.getBackground();
            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            DrawableCompat.setTint(wrappedDrawable, Color.parseColor(tag.getColorHex()));


            // Thiết lập sự kiện click
            editButton.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putParcelable("tag_to_edit", tag);
                Navigation.findNavController(v).navigate(R.id.action_tagManagerFragment_to_addEditTagDialogFragment, bundle);
            });
            deleteButton.setOnClickListener(v -> Toast.makeText(context, "Xóa: " + tag.getName(), Toast.LENGTH_SHORT).show());
        }
    }
}
