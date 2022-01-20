package es.system.jpexposito.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import es.system.jpexposito.R;
import es.system.jpexposito.adapter.UserAdapter;
import es.system.jpexposito.model.User;
import es.system.jpexposito.networking.connection.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnAddUser;
    Button btnGetUsersList;
    ListView listView;
    UserService userService;
    List<User> list = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getUsersList(){
        Call<List<User>> call = userService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(
                            new UserAdapter(MainActivity.this,
                                    R.layout.list_user, list));
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}