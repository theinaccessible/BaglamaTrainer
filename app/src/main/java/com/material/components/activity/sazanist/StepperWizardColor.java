package com.material.components.activity.sazanist;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.material.components.R;
import com.material.components.utils.Tools;

public class StepperWizardColor extends AppCompatActivity {

    String filename = "wizardFile";

    private static final int MAX_STEP = 4;

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private Button btn_got_it;

    private String[] title_array = {
            "Ready to Begin",
            "Learn How to Read Music Sheets",
            "Practice Every Kind of Bağlama",
            "Enjoy Learning New Songs"
    };

    private String[] description_array = {
            "Welcome to the Bağlama Trainer v1.0 , using the instructions you can learn how play bağlama" +
                    "if you are not a newbie than you can choose your level and improve your skills",
            "First you need to learn how to read music sheets , after that you are going to understand" +
                    "exercises easily and practice them as much as you would like to.",
            "Bağlama Trainer v1.0 also enables you to learn different kind of bağlama playing styles like Şelpe",
            "In the end of the exercises you are going to be ready for learning songs.",
    };

    private int[] about_images_array = {
            R.drawable.img_wizard_1,
            R.drawable.img_wizard_2,
            R.drawable.img_wizard_3,
            R.drawable.img_wizard_4
    };
    private int[] color_array = {
            R.color.red_600,
            R.color.blue_grey_600,
            R.color.purple_600,
            R.color.deep_orange_600
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stepper_wizard_color);

        initComponent();

        Tools.setSystemBarTransparent(this);
    }

    private void initComponent() {
        viewPager = findViewById(R.id.view_pager);
        btn_got_it = findViewById(R.id.btn_got_it);

        // adding bottom dots
        bottomProgressDots(0);

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btn_got_it.setVisibility(View.GONE);
        btn_got_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.btn_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void bottomProgressDots(int current_index) {
        LinearLayout dotsLayout = findViewById(R.id.layoutDots);
        ImageView[] dots = new ImageView[MAX_STEP];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(getResources().getColor(R.color.overlay_dark_30), PorterDuff.Mode.SRC_IN);
            dotsLayout.addView(dots[i]);
        }

        dots[current_index].setImageResource(R.drawable.shape_circle);
        dots[current_index].setColorFilter(getResources().getColor(R.color.grey_10), PorterDuff.Mode.SRC_IN);
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(final int position) {
            bottomProgressDots(position);
            if (position == title_array.length - 1) {
                btn_got_it.setVisibility(View.VISIBLE);
            } else {
                btn_got_it.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.item_stepper_wizard_color, container, false);
            ((TextView) view.findViewById(R.id.title)).setText(title_array[position]);
            ((TextView) view.findViewById(R.id.description)).setText(description_array[position]);
            ((ImageView) view.findViewById(R.id.image)).setImageResource(about_images_array[position]);
            view.findViewById(R.id.lyt_parent).setBackgroundColor(getResources().getColor(color_array[position]));
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return title_array.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    private void saveFile(){
        SharedPreferences sharedPref = getSharedPreferences(filename,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("isShown",true);
        editor.apply();
        Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // write to the file that wizard stepper is shown
        saveFile();
    }
}