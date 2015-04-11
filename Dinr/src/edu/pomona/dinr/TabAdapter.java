package edu.pomona.dinr;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Meals", "Meet", "Match" };

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
    	switch(position){
    	case 0:
    		return new MealChooserFragment();
    	case 1:
    		return new MeetFragment();
    	case 2:
    		return new MatchesFragment();
    	default:
    		return new MealChooserFragment();
    	}
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
