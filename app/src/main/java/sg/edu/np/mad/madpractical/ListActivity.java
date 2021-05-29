package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    static ArrayList<User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DBHandler db = new DBHandler(this);


        //userList = new ArrayList<>();

        //To populate the database (only use for the first time)
        for(int i=0; i<20; i++)
        {
            User u = new User();
            u.name = "Name" + new Random().nextInt();
            u.description = "Description " + new Random().nextInt();
            u.followed = new Random().nextInt()%2 == 0;
            //userList.add(u);
            db.addUser(u);
        }
        userList = db.getUser();

        RecyclerView rv = findViewById(R.id.rv);
        ItemsAdapter itemsAdapter = new ItemsAdapter(userList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(itemsAdapter);
    }
}