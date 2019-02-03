package maddie.practice.com.rxjavaissuebrowser.model.issue

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface GithubIssueDao {
    @Query("SELECT * from githubissue ORDER BY updatedTimestamp desc")
    abstract fun getRxJavaIssues(): LiveData<List<GithubIssue>>

    @Query("SELECT * from githubissue WHERE id = :id")
    abstract fun getIssueById(id: Long): LiveData<GithubIssue>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(issues: List<GithubIssue>)
}