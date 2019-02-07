package maddie.practice.com.rxjavaissuebrowser.model.comment

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface CommentDao {
    @Query("SELECT * from comment WHERE issueNumber = :issueNumber")
    abstract fun getCommentsForIssue(issueNumber: Int): LiveData<List<Comment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(comments: List<Comment>)
}