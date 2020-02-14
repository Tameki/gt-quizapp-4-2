package com.geektech.quizapp_gt_4_2.presentation.quiz.recycler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.model.EType;
import com.geektech.quizapp_gt_4_2.model.Question;

public class QuizViewHolder extends RecyclerView.ViewHolder {

    private Listener listener;
    private TextView multipleAnswer1;
    private TextView multipleAnswer2;
    private TextView multipleAnswer3;
    private TextView multipleAnswer4;
    private TextView booleanAnswerTrue;
    private TextView booleanAnswerFalse;

    QuizViewHolder(
            @NonNull View itemView,
            Listener listener
    ) {
        super(itemView);
        this.listener = listener;

        listener.onAnswerClick(getAdapterPosition(), 0);
        listener.onAnswerClick(getAdapterPosition(), 1);
        listener.onAnswerClick(getAdapterPosition(), 2);
        listener.onAnswerClick(getAdapterPosition(), 3);

        listener.onAnswerClick(getAdapterPosition(), 0);
        listener.onAnswerClick(getAdapterPosition(), 1);
    }

    void onBind(Question question) {
        reset();

        if (question.getType() == EType.MULTIPLE) {
            //TODO: R.id.item_question_multiple = visible
        } else {
            //TODO: R.id.item_question_double = visible
        }

        setSelected(question);
    }

    private void reset() {
        resetAnswerViews(
                multipleAnswer1,
                multipleAnswer2,
                multipleAnswer3,
                multipleAnswer4,
                booleanAnswerTrue,
                booleanAnswerFalse
        );
    }

    private void resetAnswerViews(TextView... textViews) {
        for (TextView textView : textViews) {
            //TODO: Reset background and textColor to default
        }
    }

    private void setSelected(Question question) {
        //TODO: Display selected answer
    }

    public interface Listener {
        void onAnswerClick(int position, int selectedAnswerPosition);
    }

}
