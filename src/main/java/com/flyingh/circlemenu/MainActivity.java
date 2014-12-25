package com.flyingh.circlemenu;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;


public class MainActivity extends ActionBarActivity {

    private RelativeLayout innerRelativeLayout;
    private RelativeLayout middleRelativeLayout;
    private RelativeLayout outerRelativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        innerRelativeLayout = (RelativeLayout) findViewById(R.id.inner);
        middleRelativeLayout = (RelativeLayout) findViewById(R.id.middle);
        outerRelativeLayout = (RelativeLayout) findViewById(R.id.outer);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (isVisible(innerRelativeLayout)) {
                rotateOut(innerRelativeLayout, 0);
                if (isVisible(middleRelativeLayout)) {
                    rotateOut(middleRelativeLayout, 10);
                }
                if (isVisible(outerRelativeLayout)) {
                    rotateOut(outerRelativeLayout, 20);
                }
            } else {
                rotateIn(innerRelativeLayout, 0);
                rotateIn(middleRelativeLayout, 10);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onHomeClick(View view) {
        if (isVisible(middleRelativeLayout)) {
            rotateOut(middleRelativeLayout, 0);
            if (isVisible(outerRelativeLayout)) {
                rotateOut(outerRelativeLayout, 10);
            }
        } else {
            rotateIn(middleRelativeLayout, 0);
        }
    }

    private void rotateOut(View view, int startOffset) {
        rotate(view, 0, 180, startOffset);
    }

    private void rotateIn(View view, int startOffset) {
        rotate(view, 180, 360, startOffset);
    }

    private void rotate(View view, int fromDegrees, int toDegrees, int startOffset) {
        RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees, view.getWidth() / 2, view.getHeight());
        animation.setDuration(100);
        animation.setFillAfter(true);
        animation.setStartOffset(startOffset);
        view.startAnimation(animation);
        view.setVisibility(isVisible(view) ? View.INVISIBLE : View.VISIBLE);
    }


    private boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    public void onMenuClick(View view) {
        if (isVisible(outerRelativeLayout)) {
            rotateOut(outerRelativeLayout, 0);
        } else {
            rotateIn(outerRelativeLayout, 0);
        }
    }
}
