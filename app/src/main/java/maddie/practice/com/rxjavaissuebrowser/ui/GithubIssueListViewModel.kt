package maddie.practice.com.rxjavaissuebrowser.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import maddie.interviews.com.doordashlite.api.Resource
import maddie.practice.com.rxjavaissuebrowser.model.issue.GithubIssue
import maddie.practice.com.rxjavaissuebrowser.model.issue.GithubIssueRepository
import javax.inject.Inject

class GithubIssueListViewModel @Inject constructor(private val githubIssueRepository: GithubIssueRepository) : ViewModel() {

    private val mObservableIssues: MediatorLiveData<Resource<List<GithubIssue>>> = MediatorLiveData()
    private var mLiveData: LiveData<Resource<List<GithubIssue>>> = githubIssueRepository.getIssues()

    fun getRxJavaIssues(): LiveData<Resource<List<GithubIssue>>> {
        return mObservableIssues
    }

    init {
        mObservableIssues.value = null
        mObservableIssues.addSource(mLiveData) { value ->
            mObservableIssues.value = value
        }
    }

    fun refresh() {
        mObservableIssues.removeSource(mLiveData)
        mLiveData = githubIssueRepository.getIssues()
        mObservableIssues.addSource(mLiveData) { value ->
            mObservableIssues.value = value
        }
    }
}
