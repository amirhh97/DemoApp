package com.example.ahosseini.ivademo;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyApp extends Application {
   @Override
   public void onCreate() {
      super.onCreate();
      CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
              .setDefaultFontPath("fonts/iransans.ttf")
              .setFontAttrId(R.attr.fontPath)
              .build()
      );
   }

}
