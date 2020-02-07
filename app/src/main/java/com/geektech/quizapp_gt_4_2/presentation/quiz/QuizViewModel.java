package com.geektech.quizapp_gt_4_2.presentation.quiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.core.SingleLiveEvent;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.model.Question;
import com.geektech.quizapp_gt_4_2.model.QuizResult;

import java.util.Date;
import java.util.List;

public class QuizViewModel extends ViewModel {

    private IQuizApiClient quizApiClient = App.quizApiClient;
    private List<Question> mQuestions;

    MutableLiveData<List<Question>> questions = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();

    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();


    void init(int amount, Integer category, String difficulty) {
        quizApiClient.getQuestions(new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> result) {
                mQuestions = result;
                questions.setValue(mQuestions);
                currentQuestionPosition.setValue(0);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

        finishEvent.call();
    }

    private int getCorrectAnswersAmount() {
        //TODO:
        return 0;
    }

    void finishQuiz() {
        QuizResult result = new QuizResult(
                0,
                "",
                "",
                mQuestions,
                getCorrectAnswersAmount(),
                new Date()
        );

        int resultId = App.historyStorage.saveQuizResult(result);

        //TODO: Start Result activity
        finishEvent.call();
        openResultEvent.setValue(resultId);
    }

    void onBackPressed() {
        //TODO:
    }

    void onSkipClick() {
        //TODO:
    }

    void onAnswerClick(int position, int selectedAnswerPosition) {
        // 20, 19
        // 20, 20
        // 20, 21
        // 20, -1

        if (mQuestions.size() > position && position >= 0) {
            mQuestions.get(position)
                    .setSelectedAnswerPosition(selectedAnswerPosition);

            questions.setValue(mQuestions);

            // 20, 17 -> 18
            // 20, 18 -> 19
            // 20, 19 -> 20
            // 20, 20

            if (position + 1 == mQuestions.size()) {
                //TODO: Finish quiz
            } else {
                currentQuestionPosition.setValue(position + 1);
            }
        }
    }
}
