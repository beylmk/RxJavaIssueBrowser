package maddie.practice.com.rxjavaissuebrowser.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import maddie.practice.com.rxjavaissuebrowser.model.issue.GithubIssue
import maddie.practice.com.rxjavaissuebrowser.model.issue.GithubIssueDao

@Database(entities = [GithubIssue::class], version = 1, exportSchema = false)
abstract class GithubIssueDatabase : RoomDatabase() {
    abstract fun githubIssueDao() : GithubIssueDao

}
