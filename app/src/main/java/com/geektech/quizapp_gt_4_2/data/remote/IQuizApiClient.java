package com.geektech.quizapp_gt_4_2.data.remote;

import com.geektech.core.IBaseCallback;
import com.geektech.quizapp_gt_4_2.model.Question;

import java.util.List;

public interface IQuizApiClient {

    void getQuestions(QuestionsCallback callback);

    interface QuestionsCallback extends IBaseCallback<List<Question>> { }
}
