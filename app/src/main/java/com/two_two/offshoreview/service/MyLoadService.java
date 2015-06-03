package com.two_two.offshoreview.service;

import android.widget.Toast;

import com.two_two.offshoreview.data.Article;
import com.two_two.offshoreview.task.TaskLoadArticlesOffshore;

import java.util.ArrayList;
import com.two_two.offshoreview.callbacks.ArticleLoadListenerOffshore;

import me.tatarka.support.job.JobParameters;
import me.tatarka.support.job.JobService;


public class MyLoadService extends JobService implements ArticleLoadListenerOffshore {
    private JobParameters jobParameters;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Toast.makeText(this, "onStartJobService", Toast.LENGTH_SHORT).show();
        this.jobParameters = jobParameters;
        new TaskLoadArticlesOffshore(this).execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    @Override
    public void onArticleLoadListenerOffshore(ArrayList<Article> listArticles) {
        jobFinished(jobParameters, false);
    }


}
