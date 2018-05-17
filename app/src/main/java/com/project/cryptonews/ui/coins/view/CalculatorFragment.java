package com.project.cryptonews.ui.coins.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.cryptonews.R;

/**
 * Calculator Fragment
 */

public class CalculatorFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.calulator_fragment, container, false);
        ((TextView) view.findViewById(R.id.title)).setText("Calculator");
        return view;
    }

    @Override
    public String toString() {
        return "Calculator";
    }
}
