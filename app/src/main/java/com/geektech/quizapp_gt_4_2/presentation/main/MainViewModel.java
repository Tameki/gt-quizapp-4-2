package com.geektech.quizapp_gt_4_2.presentation.main;

import androidx.lifecycle.ViewModel;

import com.geektech.core.SingleLiveEvent;


public class MainViewModel extends ViewModel {

    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();
    SingleLiveEvent<String> messageEvent = new SingleLiveEvent<>();

    void callFinish() {
        finishEvent.call();
    }

    void onShowMessageClick() {
        messageEvent.setValue("Hello!");
    }

}
