package com.project.cryptonews.ui;

import android.view.View;

/**
 * Data binding custom adapter to show or hide view.
 */

public class BindingAdapter {
    @android.databinding.BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }
}
