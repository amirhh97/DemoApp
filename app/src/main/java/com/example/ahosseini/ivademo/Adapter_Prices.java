package com.example.ahosseini.ivademo;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class Adapter_Prices extends RecyclerView.Adapter<Adapter_Prices.Holder> {
   ArrayList<String>items;
   Context c;
   String mobNumber;
   public Adapter_Prices(ArrayList<String> prices,String number, Activity context){
      items=prices;
      c=context;
      mobNumber=number;
   }

   @NonNull
   @Override
   public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      View view= LayoutInflater.from(c).inflate(R.layout.rv_items,viewGroup,false);
      return new Holder(view);
   }

   @Override
   public void onBindViewHolder(@NonNull Holder holder, int position) {
   holder.txt.setText(items.get(position));
   }

   @Override
   public int getItemCount() {
      return items.size();
   }

   public class Holder extends RecyclerView.ViewHolder{
      Button btn;
      TextView txt;

      public Holder(@NonNull View itemView) {
         super(itemView);
         btn=itemView.findViewById(R.id.btn_price);
         txt=itemView.findViewById(R.id.txt_price);
         btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ((MainActivity)c).addFragment(FragmentCardInfo.newInstance(txt.getText().toString(),mobNumber));
            }
         });
      }
   }

   public void setItems(ArrayList<String> items) {
      this.items = items;
   }
}
