package mafken.dotpict;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelperDrawings extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "AndroidDotpict.db";
    public static final String SUBREDDITS_TABLE_NAME = "Pixels";
    public static final String SUBREDDITS_COLUMN_ID = "id";
    public static final String SUBREDDITS_COLUMN_NAME = "color";


    public DBHelperDrawings(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table Pixels " +
                        "(id integer primary key, color integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Pixels");
        onCreate(db);
    }

    public void clearDatabase(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS Pixels");
        onCreate(db);
    }

    public boolean insertPerson (String name, String title, String author, long created,
                                 String selftext, String thumbnail, int num_comments, String url, int ups) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);

        return true;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, SUBREDDITS_TABLE_NAME);
        return numRows;
    }


    public Integer deleteSubreddits (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("SUBREDDITS",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getSubreddits() {
        ArrayList<String> subreddits = new ArrayList<>();

        
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from SUBREDDITS", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            /*Subreddit sr = new Subreddit();

            sr.setName(res.getString(res.getColumnIndex(SUBREDDITS_COLUMN_NAME)));
            sr.setTitle(res.getString(res.getColumnIndex(SUBREDDITS_COLUMN_TITLE)));
            sr.setAuthor(res.getString(res.getColumnIndex(SUBREDDITS_COLUMN_AUTHOR)));
            sr.setCreated(Integer.parseInt(res.getString(res.getColumnIndex(SUBREDDITS_COLUMN_CREATED))));
            sr.setSelftext(res.getString(res.getColumnIndex(SUBREDDITS_COLUMN_SELFTEXT)));
            sr.setThumbnail(res.getString(res.getColumnIndex(SUBREDDITS_COLUMN_THUMBNAIL)));
            sr.setNum_comments(Integer.parseInt(res.getString(res.getColumnIndex(SUBREDDITS_COLUMN_NUMCOMMENT))));
            sr.setUrl(res.getString(res.getColumnIndex(SUBREDDITS_COLUMN_URL)));
            sr.setUps(Integer.parseInt(res.getString(res.getColumnIndex(SUBREDDITS_COLUMN_UPS))));

            subreddits.add(sr);*/
            res.moveToNext();
        }

        res.close();
        return subreddits;
    }
}