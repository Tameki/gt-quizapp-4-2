package com.geektech.quizapp_gt_4_2.presentation.history;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.core.CoreFragment;

public class HistoryFragment extends CoreFragment {

    private HistoryViewModel mViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_history;
    }

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        // TODO: Use the ViewModel
    }

}
