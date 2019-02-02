package maddie.practice.com.rxjavaissuebrowser.dagger

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = arrayOf(ViewModelModule::class))
class AppModule {

//    @Provides
//    @Singleton
//    fun provideAppDatabase(application: Application): RestaurantDatabase {
//        return Room.databaseBuilder(
//            application,
//            RestaurantDatabase::class.java!!, "restaurant.db"
//        )
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideRestaurantDao(database: RestaurantDatabase): RestaurantDao {
//        return database.restaurantDao()
//    }
//
//    @Provides
//    @Singleton
//    fun provideRestaurantRepository(webservice: RestaurantService, restaurantDao: RestaurantDao, executor: AppExecutors): RestaurantRepository {
//        return RestaurantRepository(webservice, restaurantDao, executor)
//    }
//
//    @Provides
//    fun provideExecutor(): Executor {
//        return Executors.newSingleThreadExecutor()
//    }
//
//    @Provides
//    fun provideGson(): Gson {
//        return GsonBuilder().create()
//    }
//
    @Provides
    fun provideRetrofit(a: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(a))
            .baseUrl("https://api.github.com/")
            .build()
    }
//
//    @Provides
//    @Singleton
//    fun provideGithubWebservice(restAdapter: Retrofit): GithubService {
//        return restAdapter.create<GithubService>(GithubService::class.java!!)
//    }
}
