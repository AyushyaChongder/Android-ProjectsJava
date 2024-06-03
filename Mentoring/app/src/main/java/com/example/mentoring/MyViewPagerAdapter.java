package com.example.mentoring;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mentoring.fragments.PreviousFragment;
import com.example.mentoring.fragments.UpcomingFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new UpcomingFragment();

            case 1:
                return new PreviousFragment();

            default:
                return new UpcomingFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
