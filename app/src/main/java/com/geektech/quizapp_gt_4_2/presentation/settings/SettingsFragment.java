package com.geektech.quizapp_gt_4_2.presentation.settings;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.core.CoreFragment;

public class SettingsFragment extends CoreFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_settings;
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
