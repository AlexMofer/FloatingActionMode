package com.am.widget.ui;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.am.appcompat.app.AppCompatActivity;
import com.am.widget.floatingactionmode.FloatingActionMode;
import com.am.widget.floatingactionmode.FloatingMenu;
import com.am.widget.floatingactionmode.FloatingMenuItem;
import com.am.widget.floatingactionmode.FloatingSubMenu;

public class MainActivity extends AppCompatActivity implements PressView.OnPressListener,
        FloatingActionMode.Callback {

    private final Rect mBound = new Rect();
    private FloatingActionMode mMode;

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final PressView bound = findViewById(R.id.floating_dv_bound);
        bound.setOnPressListener(this);
    }

    // Listener
    @Override
    public void onPressed(View view, int left, int top, int right, int bottom) {
        if (mMode != null)
            mMode.finish();
        mBound.set(left, top, right, bottom);
        mMode = new FloatingActionMode(view, this, R.style.FloatingActionMode);
        mMode.start();
    }

    // Callback
    @Override
    public boolean onPrepareActionMode(FloatingActionMode mode, FloatingMenu menu) {
        menu.add(R.string.floating_menu_1);
        menu.add(R.string.floating_menu_2);
        menu.add(R.string.floating_menu_3);
        menu.add(R.string.floating_menu_4);
        menu.add(R.string.floating_menu_5);
        menu.add(R.string.floating_menu_6);
        menu.add(R.string.floating_menu_7);
        menu.add(R.string.floating_menu_8);
        final View custom = View.inflate(this,
                R.layout.layout_menu_floatingactionmode, null);
        menu.add(R.string.floating_menu_9).setSubMenu(custom);
        menu.add(R.string.floating_menu_10);
        menu.add(R.string.floating_menu_11);
        menu.add(R.string.floating_menu_12);
        final FloatingSubMenu sub = menu.add(R.string.floating_menu_13).setSubMenu();
        sub.add(R.string.floating_menu_15);
        sub.add(R.string.floating_menu_16);
        sub.add(R.string.floating_menu_17);
        sub.add(R.string.floating_menu_18);
        menu.add(R.string.floating_menu_14);
        return false;
    }

    @Override
    public void onGetContentRect(FloatingActionMode mode, View view, Rect outRect) {
        outRect.set(mBound);
        mode.setInMultiWindowMode(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N &&
                isInMultiWindowMode());
    }

    @Override
    public boolean onActionItemClicked(FloatingActionMode mode, FloatingMenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onDestroyActionMode(FloatingActionMode mode) {
        mMode = null;
    }
}