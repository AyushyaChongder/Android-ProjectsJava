package com.example.wolfys;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.wolfys.fragments.FruitsFragment;
import com.example.wolfys.fragments.GroceryFragment;
import com.example.wolfys.fragments.VeggiesFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {

    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new VeggiesFragment();

            case 1:
                return new FruitsFragment();

            case 2:
                return new GroceryFragment();

            default:
                return new VeggiesFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
