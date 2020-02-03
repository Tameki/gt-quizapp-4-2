package com.geektech.quizapp_gt_4_2.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.model.Question;
import com.geektech.quizapp_gt_4_2.quiz.recycler.QuizAdapter;
import com.geektech.quizapp_gt_4_2.quiz.recycler.QuizViewHolder;

import java.util.List;

public class QuizActivity extends AppCompatActivity
        implements QuizViewHolder.Listener {

    //region Static

    private static String EXTRA_AMOUNT = "amount";
    private static String EXTRA_CATEGORY = "category";
    private static String EXTRA_DIFFICULTY = "difficulty";

    public static void start(
            Context context,
            Integer amount,
            Integer category,
            String difficulty
    ) {
        Intent intent = new Intent(context, QuizActivity.class);

        intent.putExtra(EXTRA_AMOUNT, amount);
        intent.putExtra(EXTRA_CATEGORY, category);
        intent.putExtra(EXTRA_DIFFICULTY, difficulty);

        context.startActivity(intent);
    }

    //endregion

    private QuizViewModel viewModel;
    private RecyclerView recyclerView;
    private QuizAdapter adapter;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        adapter = new QuizAdapter(this);
        recyclerView = findViewById(R.id.quiz_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                this,
                RecyclerView.HORIZONTAL,
                false
        ));
        recyclerView.setAdapter(adapter);
        recyclerView.setOnTouchListener((v, event) -> true);

        viewModel = ViewModelProviders.of(this)
                .get(QuizViewModel.class);

        viewModel.questions.observe(this, questions -> {
            adapter.setQuestions(questions);
        });

        viewModel.currentQuestionPosition.observe(this, currentQuestion -> {
            String hint = currentQuestion + 1 + "/" + adapter.getItemCount();

            recyclerView.smoothScrollToPosition(currentQuestion);
        });

        viewModel.init(10, 0, "");
    }

    @Override
    public void onAnswerClick(int position, int selectedAnswerPosition) {
        viewModel.onAnswerClick(position, selectedAnswerPosition);
    }
}
