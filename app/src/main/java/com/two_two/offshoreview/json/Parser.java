package com.two_two.offshoreview.json;

import com.two_two.offshoreview.data.Article;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Parser {
    public static ArrayList<Article> parseJSONResponse(JSONObject response) {

        ArrayList<Article> listArticle = new ArrayList<>();

        if(response != null && response.length() > 0){
            try {
                JSONArray arrayArticle = response.getJSONArray("posts");
                for (int i = 0; i < arrayArticle.length(); i++) {
                    JSONObject currentArticle = arrayArticle.getJSONObject(i);
                    long id = currentArticle.getInt("id");
                    String title = currentArticle.getString("title");
                    String thumbnail = currentArticle.getString("thumbnail");
                    String date = currentArticle.getString("date");
                    String content = currentArticle.getString("content");
                    String urlArticle = currentArticle.getString("url");
                    JSONArray categoryArray = currentArticle.getJSONArray("categories");
                    Article article = new Article();
                    for (int j = 0; j < categoryArray.length(); j++) {
                        JSONObject currentCategory = categoryArray.getJSONObject(j);
                        String category = currentCategory.getString("title");
                        article.setCategory(category);
                    }
                    article.setArticleId(id);
                    article.setTitle(title);
                    article.setThumbnailUrl(thumbnail);
                    article.setDate(date);
                    article.setContent(content);
                    article.setUrlArticle(urlArticle);

                    listArticle.add(article);

                }
            } catch (JSONException e) {

            }

        }
        return listArticle;

    }

}
