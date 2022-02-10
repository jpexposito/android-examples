package es.system.jpexposito;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import es.system.jpexposito.activity.MainActivity;
import es.system.jpexposito.activity.UserActivity;
import es.system.jpexposito.adapter.UserAdapter;
import es.system.jpexposito.model.User;
import es.system.jpexposito.networking.connection.APIUtils;
import es.system.jpexposito.networking.connection.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    UserService userService;

    @Before
    public void before(){
        if (userService == null) {
            userService = APIUtils.getUserService();
        }
    }


    @Test
    public void getAllElements() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Call<List<User>> call = userService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    System.out.println("Hay elementos:"+response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

    }
}