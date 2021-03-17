package com.example.scanball.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.scanball.NavItemSelectedListener;
import com.example.scanball.R;
import com.google.android.material.navigation.NavigationView;
//боковое меню
public class MenuFragmentList extends Fragment {
    private NavItemSelectedListener navItemSelectedListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container,
                false);
        NavigationView vNavigation = view.findViewById(R.id.vNavigation);
        vNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                navItemSelectedListener.OnNavItemSelectedListener(menuItem);
                return false;
            }
        }) ;

        return  view ;
    }

    public void setNavItemSelectedListener(NavItemSelectedListener navItemSelectedListener) {
        this.navItemSelectedListener = navItemSelectedListener;
    }
}