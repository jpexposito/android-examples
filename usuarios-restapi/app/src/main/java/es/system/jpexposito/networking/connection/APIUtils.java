package es.system.jpexposito.networking.connection;

import es.system.jpexposito.networking.connection.retrofit.RetrofitClient;

public class APIUtils {

    private APIUtils(){
    };

    public static final String API_URL = "http://localhost:8080/api/v1/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(API_URL).create(UserService.class);
    }

}
