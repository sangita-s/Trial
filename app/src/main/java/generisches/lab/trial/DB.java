package generisches.lab.trial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DB extends SQLiteOpenHelper {

    public DB(Context context) {
        super(context, "reg.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL = "Create table CLASSDETAIL (REG_NO TEXT PRIMARY KEY, " +
                "NAME TEXT NOT NULL, " +
                "GPA TEXT NOT NULL);";
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Boolean insert(String r, String n, String g) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put("REG_NO", r);
        v.put("NAME", n);
        v.put("GPA", g);
        long result = db.insert("CLASSDETAIL", null, v);
        return result != 1;
    }

    public Boolean update(String r, String n, String g) {
        long result = check_no(r);
        if(result == -1){
            return false;
        }
        else
        {
            SQLiteDatabase db = getReadableDatabase();
            ContentValues v = new ContentValues();
            v.put("REG_NO", r);
            v.put("NAME", n);
            v.put("GPA", g);
            db.update("CLASSDETAIL", v, "REG_NO = ?",new String[]{r});
            return true;
        }
    }

    private long check_no(String r) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("select * from CLASSDETAIL where REG_NO = '" + r + "';",null);
        if(res.moveToNext()){
            res.close();
            return 1;
        }
        else
        {
            res.close();
            return -1;
        }
    }

    public Boolean delete(String r) {
        long result = check_no(r);
        if(result == -1){
            Log.d("delete int is ",Long.toString(result));
            return false;
        }
        else{
            SQLiteDatabase db = getReadableDatabase();
            int deleteresult = db.delete("CLASSDETAIL", "REG_NO = ?",new String[]{r});
            Log.d("delete int is ",Integer.toString(deleteresult));
            return true;
        }
    }

    public Cursor viewall() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("Select * from CLASSDETAIL;", null);
        return res;
    }

    public Cursor view1(String r) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("select * from CLASSDETAIL where REG_NO = '" + r + "';",null);
        return res;
    }
}
