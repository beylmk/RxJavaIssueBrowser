package maddie.practice.com.rxjavaissuebrowser.model.issue

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface IssueDao {
    @Query("SELECT * from issue ORDER BY updatedTimestamp desc")
    abstract fun getRxJavaIssues(): LiveData<List<Issue>>

    @Query("SELECT * from issue WHERE id = :id")
    abstract fun getIssueById(id: Long): LiveData<Issue>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(issues: List<Issue>)
}