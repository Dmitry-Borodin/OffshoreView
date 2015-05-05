package com.two_two.offshoreview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.two_two.offshoreview.example.Article;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements MainListAdapter.IOnListItemClick {
 //   private List<Article> list;
 //   private ListView titleArticlesList;
    public static final String JSON_URL = "http://46.28.202.221/get_article.php";
    private RecyclerView recyclerView;
    private TextView tv;
//    final String jsonStr = "{\"article\":[{\"pid\":\"6\",\"title\":\"Во что вкладывать деньги, кроме акций?\",\"date_public\":\"2015-03-25 14:55:30\",\"img\":\"http:\\/\\/ventureview.eu\\/wp-content\\/uploads\\/2015\\/03\\/479101t81h10cd-720x340.jpg\"},{\"pid\":\"10\",\"title\":\"Во что вкладывать деньги, кроме акций?\",\"date_public\":\"2015-03-25 14:55:30\",\"img\":\"http:\\/\\/ventureview.eu\\/wp-content\\/uploads\\/2015\\/03\\/479101t81h10cd-720x340.jpg\"},{\"pid\":\"5\",\"title\":\"Expara запустила новый фонд стоимостью в 21,6 млн долларов\",\"date_public\":\"2015-03-24 08:34:33\",\"img\":\"http:\\/\\/ventureview.eu\\/wp-content\\/uploads\\/2015\\/03\\/151650.640x320-720x340.jpg\"},{\"pid\":\"9\",\"title\":\"Kickstarter определил лучшие инновационные проекты\",\"date_public\":\"2015-03-23 23:00:02\",\"img\":\"http:\\/\\/ventureview.eu\\/wp-content\\/uploads\\/2015\\/03\\/0856f28917d099b7d443230cb453c630-720x340.jpg\"},{\"pid\":\"8\",\"title\":\"Kickstarter определил лучшие инновационные проекты\",\"date_public\":\"2015-03-23 23:00:02\",\"img\":\"http:\\/\\/ventureview.eu\\/wp-content\\/uploads\\/2015\\/03\\/0856f28917d099b7d443230cb453c630-720x340.jpg\"},{\"pid\":\"7\",\"title\":\"Kickstarter определил лучшие инновационные проекты\",\"date_public\":\"2015-03-23 23:00:02\",\"img\":\"http:\\/\\/ventureview.eu\\/wp-content\\/uploads\\/2015\\/03\\/0856f28917d099b7d443230cb453c630-720x340.jpg\"},{\"pid\":\"11\",\"title\":\"Kickstarter определил лучшие инновационные проекты\",\"date_public\":\"2015-03-23 23:00:02\",\"img\":\"http:\\/\\/ventureview.eu\\/wp-content\\/uploads\\/2015\\/03\\/0856f28917d099b7d443230cb453c630-720x340.jpg\"},{\"pid\":\"4\",\"title\":\"Kickstarter определил лучшие инновационные проекты\",\"date_public\":\"2015-03-23 23:00:02\",\"img\":\"http:\\/\\/ventureview.eu\\/wp-content\\/uploads\\/2015\\/03\\/0856f28917d099b7d443230cb453c630-720x340.jpg\"},{\"pid\":\"3\",\"title\":\"Как привлечь инвесторов во время войны?\",\"date_public\":\"2015-03-23 22:47:23\",\"img\":\"http:\\/\\/ventureview.eu\\/wp-content\\/uploads\\/2015\\/03\\/21540bbe37582143c20de6e6d7cc2f44-720x340.jpeg\"},{\"pid\":\"2\",\"title\":\"Почему вашему стартапу нужен бизнес-аналитик?\",\"date_public\":\"2015-03-23 21:59:06\",\"img\":\"http:\\/\\/ventureview.eu\\/wp-content\\/uploads\\/2015\\/03\\/analitik-3-720x340.jpg\"},{\"pid\":\"1\",\"title\":\"Стартап Maple Дэвида Чанга получил 22 млн долларов инвестиций\",\"date_public\":\"2015-03-23 21:59:06\",\"img\":\"http:\\/\\/ventureview.eu\\/wp-content\\/uploads\\/2015\\/03\\/m859_0_ru-720x340.jpg\"}]}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new getJSONTask().execute(JSON_URL);
        recyclerView = (RecyclerView) findViewById(R.id.articletitles);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new CardsAdapter(parseJSON(jsonStr), this));
        recyclerView.setAdapter(new CardsAdapter(new ArrayList<Article>(), this));
        tv = (TextView) findViewById(R.id.txt);
//        tv.setText(jsonStr);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Article> parseJSON (String json){
        ArrayList<Article> array = new ArrayList<>();
        JSONObject dataJsonObj;
        try {
            dataJsonObj = new JSONObject(json);
            JSONArray allArticles = dataJsonObj.getJSONArray("article");
            for (int i = 0; i < allArticles.length(); i++) {
                JSONObject jsonArticle = allArticles.getJSONObject(i);
                Article articleItem = new Article(jsonArticle);
                array.add(articleItem);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }

    @Override
    public void onClick(int pos, int pid) {
        Toast.makeText(MainActivity.this, "clicked on position = " + pos + " with pid = " + pid, Toast.LENGTH_SHORT).show();
    }


    public class getJSONTask extends AsyncTask<String, Void, String> {
        private HttpURLConnection urlConnection;
        private BufferedReader reader;
        private String resultJson;

        @Override
        protected String doInBackground(String... params) {
//            if (params.length != 1) return null;
            String url = params[0];
            try {
                URL urlline = new URL(url);
                urlConnection = (HttpURLConnection) urlline.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                resultJson = buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
            return resultJson;
        }

        @Override
        protected void onPostExecute(String jsonString) {
            super.onPostExecute(jsonString);
            if (jsonString != null) {
                Log.d("MyLOG", jsonString);
                recyclerView.swapAdapter(new CardsAdapter(parseJSON(jsonString), MainActivity.this), true);
            }        }
    }


    //tris os for change test


    //try commit

}
