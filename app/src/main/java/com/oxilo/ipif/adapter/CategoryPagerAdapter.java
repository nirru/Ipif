package com.oxilo.ipif.adapter;

/**
 * Created by nikk on 12/2/17.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.oxilo.ipif.modal.Category;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sonal on 7/2/2015.
 */
public class CategoryPagerAdapter extends FragmentStatePagerAdapter {

    public static int pos = 0;

    private List<Fragment> myFragments;
    private Context context;
    int oldPosition = -1;
    private ArrayList<Category> customCollectionArrayList;
    public CategoryPagerAdapter(Context context, FragmentManager fm, List<Fragment> myFrags, ArrayList<Category>customCollectionArrayList) {
        super(fm);
        this.myFragments = myFrags;
        this.context = context;
        this.customCollectionArrayList = customCollectionArrayList;
    }

    public void updateData(){
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {

        return myFragments.get(position);

    }

    @Override
    public int getItemPosition(Object object) {

        return POSITION_NONE;
    }

    @Override
    public int getCount() {

        return myFragments.size();
    }



    @Override
    public CharSequence getPageTitle(int position) {

        setPos(position);
        return customCollectionArrayList.get(position).getTitle();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }




    public static int getPos() {
        return pos;
    }

    public static void setPos(int pos) {
        CategoryPagerAdapter.pos = pos;
    }


}

