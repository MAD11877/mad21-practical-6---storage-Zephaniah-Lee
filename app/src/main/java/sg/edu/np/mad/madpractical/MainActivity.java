package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    User u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Debug", "create");

        Intent rec = getIntent();
        int value = rec.getIntExtra("id",0);
        u = ListActivity.userList.get(value);
//        u.name = "MAD";
//        u.description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua";
//        u.id = 1;
//        u.followed = false;


        TextView name = findViewById(R.id.txtName);
        name.setText(u.name);
        TextView description = findViewById(R.id.txtDescription);
        description.setText(u.description);
        setFollowBtn();
    }

    private void setFollowBtn() {
        Button b = findViewById(R.id.btnFollow);
        if(u.followed) {
            b.setText("Unfollow");
        }
        else {
            b.setText("Follow");
        }
    }
    public void onFollowClick(View v) {
        u.followed = !u.followed;
        if(u.followed)
            Toast.makeText(this, "Followed", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Unfollowed", Toast.LENGTH_SHORT).show();

        setFollowBtn();
        DBHandler db = new DBHandler(this);
        db.updateUser(u);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Debug", "start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Debug", "stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Debug", "destroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Debug", "pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Debug", "resume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Debug", "restart");
    }
}