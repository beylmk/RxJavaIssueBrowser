package maddie.practice.com.rxjavaissuebrowser.model.issue

import android.arch.lifecycle.LiveData
import maddie.interviews.com.doordashlite.api.AppExecutors
import maddie.interviews.com.doordashlite.api.NetworkBoundResource
import maddie.interviews.com.doordashlite.api.Resource
import maddie.practice.com.rxjavaissuebrowser.network.GithubWebService
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IssueRepository @Inject constructor(
    val webService: GithubWebService,
    val issueDao: IssueDao,
    val executor: AppExecutors
) {

    fun getIssues(): LiveData<Resource<List<Issue>>> {
        return object : NetworkBoundResource<List<Issue>>(executor) {
            override fun saveNetworkCallResult(data: List<Issue>?) {
                if (data != null && !data.isEmpty()) {
                    issueDao.insertAll(data)
                }
            }

            override fun shouldLoadFromNetwork(data: List<Issue>?): Boolean = true

            override fun loadFromDatabase(): LiveData<List<Issue>> = issueDao.getRxJavaIssues()

            override fun createNetworkCall(): Call<List<Issue>> = webService.getRepoIssues()
        }.asLiveData()
    }
}