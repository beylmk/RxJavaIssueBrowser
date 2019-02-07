package maddie.practice.com.rxjavaissuebrowser.ui.comment

import android.arch.lifecycle.*
import maddie.interviews.com.doordashlite.api.Resource
import maddie.practice.com.rxjavaissuebrowser.model.comment.Comment
import maddie.practice.com.rxjavaissuebrowser.model.comment.CommentRepository
import javax.inject.Inject
import android.arch.lifecycle.ViewModel
import maddie.practice.com.rxjavaissuebrowser.model.AbsentLiveData

class CommentsViewModel @Inject constructor(private val commentRepository: CommentRepository) : ViewModel() {
    private val issueNumber: MutableLiveData<Int> = MutableLiveData()
    private var mObservableComments: LiveData<Resource<List<Comment>>> = Transformations
        .switchMap(issueNumber) { issueNumber ->
            if (issueNumber != -1) {
                commentRepository.getCommentsByIssueNumber(issueNumber)
            } else {
                AbsentLiveData.create()
            }
        }

    fun getComments(): LiveData<Resource<List<Comment>>> {
        return mObservableComments
    }

    fun refresh() {
        if (issueNumber.value != 0) {
            issueNumber.value = issueNumber.value
        }
    }

    fun setIssueNumber(input: Int) {
        issueNumber.value = input
    }
}
