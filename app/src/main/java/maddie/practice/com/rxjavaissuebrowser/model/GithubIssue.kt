package maddie.practice.com.rxjavaissuebrowser.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class GithubIssue (
    @PrimaryKey
    @ColumnInfo
    val id: Long,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val body: String
)