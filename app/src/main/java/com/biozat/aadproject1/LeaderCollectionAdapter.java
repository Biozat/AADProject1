package com.biozat.aadproject1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LeaderCollectionAdapter extends FragmentPagerAdapter {

    public LeaderCollectionAdapter(@NonNull FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                return new LearningLeaderFragment();
            case 1:
                return new SkillLeaderFragment();

        }
        return null;
    }


    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position){switch (position){
        case 0:
            return "Learning Leaders";
        case 1:
            return "Skill IQ Leaders";

    }
        return null;
    }
}
