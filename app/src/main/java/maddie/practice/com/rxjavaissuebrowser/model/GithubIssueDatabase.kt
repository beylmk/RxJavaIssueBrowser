package maddie.practice.com.rxjavaissuebrowser.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [GithubIssue::class], version = 1)
abstract class GithubIssueDatabase : RoomDatabase() {
    abstract fun githubIssueDao() : GithubIssueDao

}
