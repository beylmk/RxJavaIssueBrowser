package maddie.practice.com.rxjavaissuebrowser.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import maddie.interviews.com.doordashlite.api.Resource
import javax.inject.Inject

class GithubIssueListViewModel @Inject constructor(private val githubIssueRepository: GithubIssueRepository) : ViewModel() {

    private val mObservableIssues: MediatorLiveData<Resource<List<GithubIssue>>> = MediatorLiveData()

    fun getRxJavaIssues(): LiveData<Resource<List<GithubIssue>>> {
        return mObservableIssues
    }

}
