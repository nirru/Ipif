/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.oxilo.ipif;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;


import com.oxilo.ipif.activity.MyAccountActivity;
import com.oxilo.ipif.fragment.ContactsFragment;
import com.oxilo.ipif.fragment.ItemFragment;
import com.oxilo.ipif.fragment.MenuFragment;
import com.oxilo.ipif.fragment.MyAccountFragment;
import com.oxilo.ipif.fragment.ProductInfo;
import com.oxilo.ipif.modal.login.UserData;


/**
 * A utility class that handles navigation in {@link MainActivity}.
 */
public class NavigationController {
    private final int containerId;
    private final FragmentManager fragmentManager;

    public NavigationController(AppCompatActivity mainActivity) {
        this.containerId = R.id.main_content;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }

    public NavigationController(BaseDrawerActivity mainActivity) {
        this.containerId = R.id.main_content;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }

    public void navigateToMyAccount() {
        String tag = "repo" + "/"  + "/" + "account";
        MyAccountFragment searchFragment =  MyAccountFragment.newInstance("","");
        fragmentManager.beginTransaction()
                .replace(containerId, searchFragment,tag)
                .commitAllowingStateLoss();
    }

    public void navigateToItems() {
        ItemFragment fragment = ItemFragment.newInstance("", "");
        String tag = "repo" + "/"  + "/" + "items";
//        String hide = "repo" + "/"  + "/" + "main";
        fragmentManager.beginTransaction()
//                .hide(fragmentManager.findFragmentByTag(hide))
                .replace(containerId, fragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigateToMenu(String brand_id) {
        MenuFragment fragment = MenuFragment.newInstance(brand_id, "");
        String tag = "repo" + "/"  + "/" + "menu";
        String hide = "repo" + "/"  + "/" + "items";
        fragmentManager.beginTransaction()
                .hide(fragmentManager.findFragmentByTag(hide))
                .add(containerId, fragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigateToProductInfo(String product_id) {
        String tag = "repo" + "/"  + "/" + "info";
        String hide = "repo" + "/"  + "/" + "menu";
        ProductInfo fragment = ProductInfo.newInstance(product_id,"");
        fragmentManager.beginTransaction()
                .hide(fragmentManager.findFragmentByTag(hide))
                .add(containerId, fragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigateToContact() {
        String tag = "repo" + "/"  + "/" + "contact";
        String hide = "repo" + "/"  + "/" + "info";
        ContactsFragment fragment = ContactsFragment.newInstance("","");
        fragmentManager.beginTransaction()
                .hide(fragmentManager.findFragmentByTag(hide))
                .add(containerId, fragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigateToContact(UserData userData) {
        String tag = "repo" + "/"  + "/" + "contact";
        ContactsFragment fragment =  ContactsFragment.newInstance("","");
        fragment.setUserData(userData);
        fragmentManager.beginTransaction()
                .replace(containerId, fragment,tag)
                .commitAllowingStateLoss();
    }
}
