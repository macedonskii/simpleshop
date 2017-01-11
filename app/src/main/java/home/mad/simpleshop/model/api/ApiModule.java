package home.mad.simpleshop.model.api;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class ApiModule {

    private ApiModule(){}

    public static ApiInterface getApiInterface(String host){

        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.httpUrl();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", "l6pdqjuf7hdf97h1yvzadfce")
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);


            }
        });
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.interceptors().add(interceptor);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(host)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                ;
        return builder.build().create(ApiInterface.class);
    }
}
