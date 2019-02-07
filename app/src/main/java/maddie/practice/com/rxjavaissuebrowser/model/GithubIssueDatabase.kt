package maddie.practice.com.rxjavaissuebrowser.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import maddie.practice.com.rxjavaissuebrowser.model.comment.Comment
import maddie.practice.com.rxjavaissuebrowser.model.comment.CommentDao
import maddie.practice.com.rxjavaissuebrowser.model.issue.Issue
import maddie.practice.com.rxjavaissuebrowser.model.issue.IssueDao

@Database(entities = [Issue::class, Comment::class], version = 1, exportSchema = false)
abstract class GithubIssueDatabase : RoomDatabase() {
    abstract fun githubIssueDao() : IssueDao
    abstract fun commentDao() : CommentDao
}