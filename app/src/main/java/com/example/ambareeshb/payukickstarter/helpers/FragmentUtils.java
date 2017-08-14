package com.example.ambareeshb.payukickstarter.helpers;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;

/**
 * Created by ambareeshb on 13/08/17.
 * Utility class for fragment management based on builder pattern.
 */

public class FragmentUtils {
    private FragmentTransaction fragmentTransaction;

    public FragmentUtils(FragmentManager fragmentManager){
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    /**
     * Add a given fragment to the given container.
     * @param containerId of fragment
     * @param fragment we are interested.
     * @return this class
     */
    public FragmentUtils add(int containerId, Fragment fragment){
        fragmentTransaction.add(containerId,fragment);
        return this;
    }

    /**
     * Commit a fragment transaction.
     * @return
     */
    public int commit(){
        return this.fragmentTransaction.commit();
    }

    /**
     * Set custom transition for fragment.
     * @param start transition resource id.
     * @param end transition resource id.
     */
    public FragmentUtils setTransition(int start, int end){
        fragmentTransaction.setCustomAnimations(start,end);
        return this;
    }
}
