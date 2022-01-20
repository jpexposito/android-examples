package es.system.jpexposito.networking.connection;

import es.system.jpexposito.networking.connection.retrofit.RetrofitClient;

public class APIUtils {

    private APIUtils(){
    };

    public static final String API_URL = "url del servicio";

    public static UserService getUserService(){
        return RetrofitClient.getClient(API_URL).create(UserService.class);
    }

}
