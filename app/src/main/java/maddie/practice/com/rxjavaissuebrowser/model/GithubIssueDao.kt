package maddie.practice.com.rxjavaissuebrowser.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface GithubIssueDao {
    @Query("SELECT * from githubissue")
    abstract fun getRxJavaIssues(): LiveData<List<GithubIssue>>
}