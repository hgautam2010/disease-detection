package komalagarwal.online_exam;

/**
 * Created by komalagarwal on 3/1/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class DBregister extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String REGISTER_TABLE_NAME = "register";
    public static final String REGISTER_COLUMN_USERNAME = "username";
    public static final String REGISTER_COLUMN_PASSWORD = "password";
    public static final String REGISTER_COLUMN_PHONE = "phone";
    private HashMap hp;

    public DBregister(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        try {

            db.execSQL("create table register (username text primary key,password text,phone text)");
        } catch (Exception ev) {
            System.out.println(ev.getMessage());
        }
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS register");
        //     db.execSQL("DROP TABLE IF EXISTS trans");
        onCreate(db);
    }
    public boolean insertContact (String name, String password, String phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", name);
        contentValues.put("password", password);
        contentValues.put("phone", phone);
        db.insert("register", null, contentValues);
        System.out.println("VALUES INSERTED IN TABLE");
        return true;
    }
    public Cursor getpass(String uname)
    {

        Cursor res=null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            res = db.rawQuery("select password from register where username='" + uname + "'", null);

        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return res;
    }
}
