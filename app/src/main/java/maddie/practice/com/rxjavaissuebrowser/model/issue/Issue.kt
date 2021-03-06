package maddie.practice.com.rxjavaissuebrowser.model.issue

import android.arch.persistence.room.*
import com.google.gson.annotations.SerializedName

@Entity
data class Issue (
    @PrimaryKey
    @ColumnInfo
    val id: Long,
    @ColumnInfo
    val number: Int,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val body: String,
    @ColumnInfo
    @SerializedName("updated_at")
    val updatedTimestamp: String
)