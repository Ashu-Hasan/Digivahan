package com.ash.digivahan.ui.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ash.digivahan.R;
import com.ash.digivahan.data.adapters.SliderAdapter;
import com.ash.digivahan.databinding.ActivityMainBinding;
import com.ash.digivahan.databinding.FragmentQRHomeBinding;
import com.ash.digivahan.other.DepthPageTransformer;
import com.google.android.material.slider.Slider;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class QRHomeFragment extends Fragment {
    String TAG = "MainActivityData";
    FragmentQRHomeBinding binding;

    private Timer swipeTimer;
    private int currentPage = 0, size = 0;
    private Handler handler;
    private Runnable Update;

    private ArrayList<Slider> sliderArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentQRHomeBinding.inflate(getLayoutInflater(), container, false);

        setHowToUseSlider();

        return binding.getRoot();
    }

    private void setHowToUseSlider() {
        // Step 1: Reset all previous data/views
        if (swipeTimer != null) {
            swipeTimer.cancel();
            swipeTimer.purge();
            swipeTimer = null;
        }

        if (handler != null && Update != null) {
            handler.removeCallbacks(Update);
        }

        sliderArrayList = new ArrayList<>();

        /*if (mMarkersLayout != null) {
            mMarkersLayout.removeAllViews(); // clear markers
        }*/

        if (binding.howToUseSliderPager != null && binding.howToUseSliderPager.getAdapter() != null) {
            binding.howToUseSliderPager.setAdapter(null); // reset pager
        }

        SliderAdapter sliderAdapter = new SliderAdapter(sliderArrayList, getActivity(), R.layout.lyt_slider, "home");
        binding.howToUseSliderPager.setAdapter(sliderAdapter);

        binding.howToUseSliderPager.setPageTransformer(true, new DepthPageTransformer());

        // Auto-scroll setup
        handler = new Handler();
        currentPage = 0;
        size = sliderArrayList.size();

        Update = () -> {
            if (currentPage == size) currentPage = 0;
            binding.howToUseSliderPager.setCurrentItem(currentPage++, true);
        };

        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        binding.howToUseSliderPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                handler.removeCallbacks(Update);
                handler.postDelayed(Update, 3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}