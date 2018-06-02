package com.ujjwal.jsonparsing;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder> {
    Context context;
    List<MenuModel> menuModels;

    public RecyclerviewAdapter(Context context, List<MenuModel> menuModels) {
        this.context = context;
        this.menuModels = menuModels;
    }

    @NonNull
    @Override
    public RecyclerviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final MenuModel menuModel =menuModels.get(position);
        holder.txt_item_price.setText(menuModel.getItem_Price());
        holder.txt_item_desc.setText(menuModel.getItem_Desc());
        holder.txt_item_name.setText(menuModel.getItem_Name());
        /// loading image in image view
        Glide.with(context)
                .load("http://vedisapp.berlin-webdesign-agentur.de/Image/"+menuModel.getImage())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.item_image);

        //click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context ,+menuModel.getItem_Name());
                Toast.makeText(context, "you clicked"+menuModel.getItem_Name(),Toast.LENGTH_SHORT).show();
                System.out.println("item name"+menuModel.getItem_Name());
                Intent intent= new Intent(context,SecondActivity.class);
                intent.putExtra("item name ", menuModel.getItem_Name());
                intent.putExtra("item price ",menuModel.getItem_Price());
              context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return  menuModels.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
       TextView txt_item_name , txt_item_desc, txt_item_price;
       ImageView item_image;

        public MyViewHolder(View itemView) {

            super(itemView);
            txt_item_desc=itemView.findViewById(R.id.item_desc);
            txt_item_price=itemView.findViewById(R.id.item_price);
            txt_item_name=itemView.findViewById(R.id.item_name);
            item_image=itemView.findViewById(R.id.item_image);

        }
    }
}
