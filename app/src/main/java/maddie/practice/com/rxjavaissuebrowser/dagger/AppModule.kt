package maddie.practice.com.rxjavaissuebrowser.dagger

import android.app.Application
import android.arch.persistence.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import maddie.interviews.com.doordashlite.api.AppExecutors
import maddie.practice.com.rxjavaissuebrowser.model.issue.IssueDao
import maddie.practice.com.rxjavaissuebrowser.model.GithubIssueDatabase
import maddie.practice.com.rxjavaissuebrowser.model.comment.CommentDao
import maddie.practice.com.rxjavaissuebrowser.model.comment.CommentRepository
import maddie.practice.com.rxjavaissuebrowser.model.issue.IssueRepository
import maddie.practice.com.rxjavaissuebrowser.network.GithubWebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): GithubIssueDatabase {
        return Room.databaseBuilder(
            application,
            GithubIssueDatabase::class.java!!, "githubissue.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideGithubIssueDao(database: GithubIssueDatabase): IssueDao {
        return database.githubIssueDao()
    }

    @Provides
    @Singleton
    fun provideGithubIssueRepository(webservice: GithubWebService, issueDao: IssueDao, executor: AppExecutors):
        IssueRepository {
        return IssueRepository(webservice, issueDao, executor)
    }

    @Provides
    @Singleton
    fun provideCommentDao(database: GithubIssueDatabase): CommentDao {
        return database.commentDao()
    }

    @Provides
    @Singleton
    fun provideGithubCommentRepository(webservice: GithubWebService, commentDao: CommentDao, executor: AppExecutors): CommentRepository {
        return CommentRepository(webservice, commentDao, executor)
    }

    @Provides
    fun provideExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideRetrofit(a: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(a))
            .baseUrl("https://api.github.com/")
            .build()
    }

    @Provides
    @Singleton
    fun provideGithubWebservice(restAdapter: Retrofit): GithubWebService {
        return restAdapter.create<GithubWebService>(GithubWebService::class.java)
    }
}
