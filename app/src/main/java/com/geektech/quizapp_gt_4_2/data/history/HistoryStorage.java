package com.geektech.quizapp_gt_4_2.data.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.geektech.quizapp_gt_4_2.model.History;
import com.geektech.quizapp_gt_4_2.model.QuizResult;

import java.util.ArrayList;
import java.util.List;

public class HistoryStorage implements IHistoryStorage {

    private HistoryDao dao;

    public HistoryStorage(HistoryDao historyDao) {
        dao = historyDao;
    }

    @Override
    public QuizResult getQuizResult(int id) {
        return dao.get(id);
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return (int) dao.insert(quizResult);
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return dao.getAll();
    }

    @Override
    public LiveData<List<History>> getAllHistory() {
        return Transformations.map(getAll(), quizResults -> {
            ArrayList<History> histories = new ArrayList<>();

            //TODO: Fill histories, for()

            return histories;
        });
    }

    @Override
    public void delete(int id) {
        //TODO: Delete by id
//        dao.delete();
    }

    @Override
    public void deleteAll() {
        //TODO: Delete all
    }
}
