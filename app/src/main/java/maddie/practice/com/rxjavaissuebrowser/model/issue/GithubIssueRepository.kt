package maddie.practice.com.rxjavaissuebrowser.model.issue

import android.arch.lifecycle.LiveData
import maddie.interviews.com.doordashlite.api.AppExecutors
import maddie.interviews.com.doordashlite.api.NetworkBoundResource
import maddie.interviews.com.doordashlite.api.Resource
import maddie.practice.com.rxjavaissuebrowser.model.issue.GithubIssue
import maddie.practice.com.rxjavaissuebrowser.model.issue.GithubIssueDao
import maddie.practice.com.rxjavaissuebrowser.network.GithubWebService
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubIssueRepository  @Inject constructor(val webService: GithubWebService, val githubIssueDao: GithubIssueDao, val executor:
AppExecutors) {

    fun getIssues(): LiveData<Resource<List<GithubIssue>>> {
        return object : NetworkBoundResource<List<GithubIssue>>(executor) {
            override fun saveNetworkCallResult(data: List<GithubIssue>?) {
                if (data != null && !data.isEmpty()) {
                    githubIssueDao.insertAll(data)
                }
            }

            override fun shouldLoadFromNetwork(data: List<GithubIssue>?): Boolean = true

            override fun loadFromDatabase(): LiveData<List<GithubIssue>> = githubIssueDao.getRxJavaIssues()

            override fun createNetworkCall(): Call<List<GithubIssue>> = webService.getRepoIssues()
        }.asLiveData()
    }
}