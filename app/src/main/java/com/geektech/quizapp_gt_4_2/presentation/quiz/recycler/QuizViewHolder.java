package com.geektech.quizapp_gt_4_2.presentation.quiz.recycler;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.model.EType;
import com.geektech.quizapp_gt_4_2.model.Question;

public class QuizViewHolder extends RecyclerView.ViewHolder {

    private Listener listener;

    QuizViewHolder(
            @NonNull View itemView,
            Listener listener
    ) {
        super(itemView);
        this.listener = listener;

        listener.onAnswerClick(getAdapterPosition(), 0);
    }

    void onBind(Question question) {
        if (question.getType() == EType.MULTIPLE) {
            //TODO: R.id.item_question_multiple = visible
        } else {
            //TODO: R.id.item_question_double = visible
        }
    }

    public interface Listener {
        void onAnswerClick(int position, int selectedAnswerPosition);
    }

}
