package sg.edu.np.mad.madpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper{
    public DBHandler(@Nullable Context context) {
        super(context, "Users", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE User (name TEXT, description TEXT, id INTEGER, followed INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        onCreate(db);
    }

    public void addUser(User user)
    {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("description", user.getDescription());
        values.put("id", user.getId());
        if (user.isFollowed() == false){
            values.put("followed", 0);
        }
        else{
            values.put("followed", 1);
        }

        SQLiteDatabase db = getWritableDatabase();
        db.insert("User", null, values);
        db.close();
    }

    public void updateUser(User user){
        int isFollowed;
        int id = user.getId();
        String name = user.getName();
        String description = user.getDescription();

        if (user.isFollowed() == false){
            isFollowed = 0;
        }
        else{
            isFollowed = 1;
        }

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE user SET name = '" + name + "', description = '" + description + "', followed = " + isFollowed + " WHERE id = " + id);
        db.close();

    }

    public ArrayList<User> getUser()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user", null);
        User u = null;
        ArrayList<User> list = new ArrayList<>();

        while(cursor.moveToNext())
        {
            u = new User();
            u.setName(cursor.getString(0));
            u.setDescription(cursor.getString(1));
            u.setId(cursor.getInt(2));
            if ((cursor.getInt(3)) == 0){
                u.setFollowed(false);
            }
            else if ((cursor.getInt(3)) == 1){
                u.setFollowed(true);
            }

            list.add(u);
        }

        cursor.close();
        db.close();

        return list;
    }
}
