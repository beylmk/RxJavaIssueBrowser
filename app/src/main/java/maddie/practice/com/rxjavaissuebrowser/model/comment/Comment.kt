package maddie.practice.com.rxjavaissuebrowser.model.comment

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Comment(
    @PrimaryKey
    @ColumnInfo
    val id: Int,
    @ColumnInfo
    var issueNumber: Int,
    @Embedded
    val user: User,
    @ColumnInfo
    val body: String
)

data class User(
    @SerializedName("login")
    val name: String
)