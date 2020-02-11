package com.geektech.quizapp_gt_4_2.presentation.quiz;

import android.util.Log;

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
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();

    private int getCorrectAnswersAmount() {
        int correctAnswers = 0;

        for (Question question : mQuestions) {
            Integer selectedAnswerPosition = question.getSelectedAnswerPosition();

            if (selectedAnswerPosition != null &&
                    selectedAnswerPosition >= 0 &&
                    question.getAnswers().get(selectedAnswerPosition)
                            .equals(question.getCorrectAnswer())) {
                correctAnswers++;
            }
        }

        return correctAnswers;
    }

    private void finishQuiz() {
        QuizResult quizResult = new QuizResult(
                0,
                "",
                "",
                mQuestions,
                getCorrectAnswersAmount(),
                new Date()
        );

        int resultId = App.historyStorage.saveQuizResult(quizResult);

        finishEvent.call();
        openResultEvent.setValue(resultId);
    }

    private void moveToQuestionOrFinish(int position) {
        if (position == mQuestions.size()) {
            finishQuiz();
        } else {
            currentQuestionPosition.setValue(position);
        }
    }

    void init(Integer amount, Integer categoryId, String difficulty) {
        currentQuestionPosition.setValue(0);
        isLoading.setValue(true);

        quizApiClient.getQuestions(new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> result) {
                isLoading.setValue(false);
                mQuestions = result;
                questions.setValue(mQuestions);
            }

            @Override
            public void onFailure(Exception e) {
                isLoading.setValue(false);
                Log.d("ololo", "Error " + e.getMessage());
            }
        });
    }

    void onAnswerClick(int questionPosition, int answerPosition) {
        if (currentQuestionPosition.getValue() == null || mQuestions == null) {
            return;
        }

        Question question = mQuestions.get(questionPosition);

        question.setSelectedAnswerPosition(answerPosition);

        mQuestions.set(questionPosition, question);

        questions.setValue(mQuestions);

        moveToQuestionOrFinish(questionPosition + 1);
    }

    void onBackPressed() {
        Integer currentPosition = currentQuestionPosition.getValue();
        if (currentPosition != null) {
            if (currentPosition == 0) {
                finishEvent.call();
            } else {
                currentQuestionPosition.setValue(currentPosition - 1);
            }
        }
    }

    void onSkipClick() {
        finishQuiz();
//        Integer currentPosition = currentQuestionPosition.getValue();
//        if (currentPosition != null) {
//            onAnswerClick(currentPosition, -1);
//        }
    }
}
