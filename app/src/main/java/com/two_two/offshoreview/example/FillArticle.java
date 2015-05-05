package com.two_two.offshoreview.example;

/**
 * Created by Stealps on 01.05.2015. it will not be used, but here is good example of DB work!
 *
 */
/*
public class FillArticle {
    public static List<Article> list;

    public static List<Article> getArticleList(Context context){
        list = new ArrayList<Article>();
        list.add(new Article(1, "blog1", "first","first content"));
        list.add(new Article(2, "blog1", "second","second content"));
        list.add(new Article(3, "blog1", "third","third content"));
        list.add(new Article(4, "blog1", "fourth","fourth content"));



        // Инициализируем наш класс-обёртку
        LocalDataBaseHelper sqlHelper = new LocalDataBaseHelper(context);

        // База нам нужна для записи и чтения
        SQLiteDatabase sdb = sqlHelper.getWritableDatabase();
        sqlHelper.onUpgrade(sdb,1,2);   //TODO we are cleaning database now!!!
        //заполняем базу
        ContentValues cv = new ContentValues();
        for (Article x:list) {

            cv.put(LocalDataBaseHelper.ID, x.getId());
            cv.put(LocalDataBaseHelper.ARTICLETITLE,x.getTitle());
            cv.put(LocalDataBaseHelper.ARTICLECONTENT,x.getContent());
            sdb.insert(LocalDataBaseHelper.OFFSHOREBLOG_TABLENAME,LocalDataBaseHelper.ARTICLETITLE,cv);
        }
        // закрываем соединения с базой данных
        sdb.close();
        sqlHelper.close();


        return list;
    }


}
*/