package maddie.practice.com.rxjavaissuebrowser.model.comment

import android.arch.lifecycle.LiveData
import android.util.Log
import maddie.interviews.com.doordashlite.api.AppExecutors
import maddie.interviews.com.doordashlite.api.NetworkBoundResource
import maddie.interviews.com.doordashlite.api.Resource
import maddie.practice.com.rxjavaissuebrowser.network.GithubWebService
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentRepository @Inject constructor(
    val webService: GithubWebService, val commentDao: CommentDao, val executor:
    AppExecutors
) {
    fun getCommentsByIssueNumber(issueNumber: Int): LiveData<Resource<List<Comment>>> {
        return object : NetworkBoundResource<List<Comment>>(executor) {
            override fun saveNetworkCallResult(data: List<Comment>?) {
                if (data != null && !data.isEmpty()) {
                    data.forEach { comment ->
                        comment.issueNumber = issueNumber
                    }
                    commentDao.insertAll(data)
                }
            }

            override fun shouldLoadFromNetwork(data: List<Comment>?): Boolean = true

            override fun loadFromDatabase(): LiveData<List<Comment>> = commentDao.getCommentsForIssue(issueNumber)

            override fun createNetworkCall(): Call<List<Comment>> = webService.getIssueComments(issueNumber = issueNumber)
        }.asLiveData()
    }
}