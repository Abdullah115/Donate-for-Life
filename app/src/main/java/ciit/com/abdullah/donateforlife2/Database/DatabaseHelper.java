package ciit.com.abdullah.donateforlife2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.facebook.stetho.Stetho;

/**
 * Created by Abdullah on 2/19/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="donateforlife";
    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists NGOs (NGOId INTEGER NOT NULL primary key autoincrement, NGOName varchar(300) not null, Category varchar(300),Address varchar(300), ContactNo varchar(50),Email varchar(100))" );
        sqLiteDatabase.execSQL("create table if not exists Users (UserId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,FirstName varchar(100) NOT NULL,LastName varchar(100) NOT NULL,Email varchar(100) NOT NULL,Password varchar(100) NOT NULL,ContactNo varchar(100) NOT NULL,Address varchar(400) NOT NULL)");
    }
    public String searchpassword(String uemail){
        db=this.getReadableDatabase();
        String query="select Email,Password from Users";
        Cursor cursor=db.rawQuery(query,null);
        String a,b;
        b="Not Found";
        if(cursor.moveToFirst()){
            do{
                a=cursor.getString(0);
                if (a.equals(uemail)){
                    b=cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //sqLiteDatabase.execSQL("drop table if exists Users");
        //sqLiteDatabase.execSQL("drop table if exists NGOs");
        //onCreate(sqLiteDatabase);
    }
    public boolean insertUser(String firstName,String lastName,String email,String password,String contactNo,String address){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("FirstName",firstName);
        contentValues.put("LastName",lastName);
        contentValues.put("Email",email);
        contentValues.put("Password",password);
        contentValues.put("ContactNo",contactNo);
        contentValues.put("Address",address);
        long insert=db.insert("Users",null,contentValues);
        if(insert==-1){
            return false;
        }else {
            return true;
        }
    }
    public boolean chkemail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "select * from Users where Email=?";
        Cursor cursor = db.rawQuery(Query, new String[]{email});
        if (cursor.getCount() > 0) return false;
        else return true;
    }
    public void insertNGOs(String NGOName, String Category ,String Address ,String ContactNo ,String Email){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("NGOName",NGOName);
        contentValues.put("Category",Category);
        contentValues.put("Address",Address);
        contentValues.put("ContactNo",ContactNo);
        contentValues.put("Email",Email);
        db.insert("NGOs",null,contentValues);
    }
    public Cursor NGodata(){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "select NGOName from NGOs";
        Cursor ngos= db.rawQuery(Query,null);
        return ngos;
    }
}
