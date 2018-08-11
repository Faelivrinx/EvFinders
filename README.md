# EvFinders
It's android application which main goal is to find events from local area.

## Requirements:
- Server which handle requests from application.
- Database used to store data about events and users.
- Android SDK between 19-26

## Info
It's just code example, but tutorial walk you through this if you want to try it by yourself.


## Tutorial:
1. Firstly we need to expose to our application, server which can handle requests.
  1. Fortunetly it's really simple, we just need to clone some other repository [Server Repo](https://github.com/Faelivrinx/evfinder-backend).
  2. Now in cloned project directory, we can fire `./mvnw clean package` command that will create jar file.
  3. Last step is to execute jar file. To do that we will use `java -jar <path-to-jar-file>`
  
2. If our server working fine, we just need to change url in [Network Module](https://github.com/Faelivrinx/EvFinders/blob/master/app/src/main/java/com/example/dominik/evfinders/di/NetworkModule.java).

```java
    @Provides
    @Named("non-auth")
    static Retrofit provideRetrofitNonAuth(@Named("non-auth") OkHttpClient client, GsonConverterFactory gsonConverterFactory, RxJava2CallAdapterFactory adapterFactory){
        return new Retrofit.Builder()
                .baseUrl("http://192.168.0.18:8080/")
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .build();
    }
    
    @Provides
    @Named("auth")
    static Retrofit provideRetrofitAuth(@Named("auth") OkHttpClient client, GsonConverterFactory gsonConverterFactory, RxJava2CallAdapterFactory adapterFactory){
        return new Retrofit.Builder()
                .baseUrl("http://192.168.0.18:8080/")
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .build();
    }
```

Retrofit builder preparing for us everything, but unfortunately baseurl probably doesn't be the same so change it for yours.

3. Have fun! Play with that simple application and modify it by yourself ;)


