package com.example.cat3;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cat3.fragments.FriendsFragment;
import com.example.cat3.fragments.TopicFragment;


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
                return new TopicFragment();

            case 1:
                return new FriendsFragment();

            default:
                return new TopicFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
