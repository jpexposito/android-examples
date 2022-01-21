package es.system.jpexposito;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import es.system.jpexposito.model.User;
import es.system.jpexposito.networking.connection.APIUtils;
import es.system.jpexposito.networking.connection.UserService;
import retrofit2.Call;

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
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }
}