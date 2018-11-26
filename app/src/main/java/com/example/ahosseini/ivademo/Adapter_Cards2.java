package com.example.ahosseini.ivademo;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class Adapter_Cards2 extends BaseAdapter {

   ArrayList<String> items;
   Context context;

   public Adapter_Cards2(ArrayList<String> items, Context context) {
      this.items = new ArrayList<>(items);
      //Collections.sort(this.items);
      this.items.add(0, "انتخاب کارت...");
      this.context = context;
   }

   @Override
   public int getCount() {
      return items.size();
   }

   @Override
   public String getItem(int position) {
      return items.get(position);
   }

   @Override
   public long getItemId(int position) {
      return position;
   }

   @Override
   public View getView(int position, View convertView, ViewGroup parent) {

      ImageView imageView;
      TextView textView;
      LinearLayout root;

      if (convertView == null) {
         convertView = LayoutInflater.from(context)
                 .inflate(R.layout.item_spinner_card2, parent, false);
         imageView = (ImageView) convertView.findViewById(R.id.iv_itemSpinnerCard);
         textView = (TextView) convertView.findViewById(R.id.tv_itemSpinnerCard);
         root = (LinearLayout) convertView.findViewById(R.id.root);
         convertView.setTag(new ViewHolder(imageView, textView));
      } else {
         ViewHolder viewHolder = (ViewHolder) convertView.getTag();
         imageView = viewHolder.imageView;
         textView = viewHolder.textView;
      }

      if (position != 0) {
         textView.setText(getItem(position));
         textView.setGravity(Gravity.LEFT);
         imageView.setVisibility(View.VISIBLE);
         imageView.setImageResource(getBankIconResId(getItem(position).replace("-",""), context));
         textView.setTextColor(context.getResources().getColor(R.color.transparent_white_hex_12));

      } else {
         textView.setText(getItem(position));
         textView.setGravity(Gravity.RIGHT);
         imageView.setVisibility(View.GONE);
         textView.setTextColor(context.getResources().getColor(R.color.sadad_logo));
      }


//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.setMargins(0, 0, 0, 0);
//        root.setLayoutParams(params);

      return convertView;
   }

   @Override
   public boolean isEnabled(int position) {
      return position != 0;
   }

   private static class ViewHolder {
      public ImageView imageView;
      public TextView textView;

      public ViewHolder(ImageView imageView, TextView textView) {
         this.textView = textView;
         this.imageView = imageView;
      }
   }

   public static int getBankIconResId(String cardNo, Context context) {
      if (cardNo.length() >= 6) {
         String cardBin = cardNo.substring(0, 6);
         switch (cardBin) {
            case "603799":
               return R.drawable.ic_img_bank_melli;
           
            case "589210":
               return R.drawable.ic_img_bank_sapah;
           
            case "627648":
            case "207177":
               return R.drawable.ic_img_bank_toseesaderat;
           
            case "627961":
               return R.drawable.ic_img_bank_sanaatomadan;
           
            case "603770":
            case "639217":
               return R.drawable.ic_img_bank_keshavarzi;
           
            case "628023":
               return R.drawable.ic_img_bank_maskan;
           
            case "502908":
               return R.drawable.ic_img_bank_tosee;
           
            case "627760":
               return R.drawable.ic_img_bank_post;
           
            case "627412":
               return R.drawable.ic_img_bank_en;
           
            case "622106":
            case "639194":
            case "627884":
               return R.drawable.ic_img_bank_parsian;
           
            case "502229":
            case "639347":
               return R.drawable.ic_img_bank_pasargad;
           
            case "627488":
            case "502910":
               return R.drawable.ic_img_bank_karafarin;
           
            case "621986":
               return R.drawable.ic_img_bank_saman;
           
            case "639346":
               return R.drawable.ic_img_bank_sina;
           
            case "639607":
               return R.drawable.ic_img_bank_sarmayeh;
           
            case "502806":
            case "504706":
               return R.drawable.ic_img_bank_shahr;
           
            case "502938":
               return R.drawable.ic_img_bank_dey;
           
            case "603769":
               return R.drawable.ic_img_bank_saderat;
           
            case "610433":
            case "991975":
               return R.drawable.ic_img_bank_mellat;
           
            case "627353":
            case "585983":
               return R.drawable.ic_img_bank_tejarat;
           
            case "589463":
               return R.drawable.ic_img_bank_refah;
           
            case "627381":
            case "637381":
               return R.drawable.ic_img_bank_ansaar;
           
            case "636214":
               return R.drawable.ic_img_bank_ayandeh;
           
            case "639370":
               return R.drawable.ic_img_bank_mehreghtesad;
           
            case "505785":
               return R.drawable.ic_img_bank_iranzamin;
           
            case "504172":
               return R.drawable.ic_img_bank_resalat;
           
            case "505416":
               return R.drawable.ic_img_bank_gardeshgari;
           
            case "636949":
               return R.drawable.ic_img_bank_hekmatiranian;
           
            case "606373":
               return R.drawable.ic_img_bank_mehriran;
           
            case "639599":
               return R.drawable.ic_img_bank_ghavamin;
           

            case "606256":

               return R.drawable.ic_melal;
           
            default:
               return R.drawable.ic_cards;
           
         }
      } else {
         return R.drawable.ic_cards;
      }
   }
}
