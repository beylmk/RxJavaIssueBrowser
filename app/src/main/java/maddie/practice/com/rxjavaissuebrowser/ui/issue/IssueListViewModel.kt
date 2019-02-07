package maddie.practice.com.rxjavaissuebrowser.ui.issue

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import maddie.interviews.com.doordashlite.api.Resource
import maddie.practice.com.rxjavaissuebrowser.model.issue.Issue
import maddie.practice.com.rxjavaissuebrowser.model.issue.IssueRepository
import javax.inject.Inject

class IssueListViewModel @Inject constructor(private val issueRepository: IssueRepository) : ViewModel() {

    private val mObservableIssues: MediatorLiveData<Resource<List<Issue>>> = MediatorLiveData()
    private var mLiveData: LiveData<Resource<List<Issue>>> = issueRepository.getIssues()

    fun getRxJavaIssues(): LiveData<Resource<List<Issue>>> {
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
        mLiveData = issueRepository.getIssues()
        mObservableIssues.addSource(mLiveData) { value ->
            mObservableIssues.value = value
        }
    }
}
