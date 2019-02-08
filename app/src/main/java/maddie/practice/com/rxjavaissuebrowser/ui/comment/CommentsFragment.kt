package maddie.practice.com.rxjavaissuebrowser.ui.comment

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.issue_list.*
import maddie.interviews.com.doordashlite.api.ResourceObserver
import maddie.practice.com.rxjavaissuebrowser.R
import maddie.practice.com.rxjavaissuebrowser.model.comment.Comment
import javax.inject.Inject

class CommentsFragment : Fragment() {
    companion object {
        const val TAG = "CommentsFragment"
    }

    @Inject
    lateinit var githubCommentViewModelFactory: ViewModelProvider.Factory
    private lateinit var commentViewModel: CommentsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.issue_comments_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidSupportInjection.inject(this)

        commentViewModel = ViewModelProviders.of(this, githubCommentViewModelFactory).get(CommentsViewModel::class.java)
        commentViewModel.setIssueNumber((activity as CommentsActivity).issueNumber)
        commentViewModel.getComments().observe(
            this, ResourceObserver(
            TAG,
            hideLoading = ::hideLoading,
            showLoading = ::showLoading,
            onSuccess = ::setUpComments,
            onError = ::showErrorMessage
        ))

        swipe_container.setOnRefreshListener {
            commentViewModel.refresh()
        }

    }

    //TODO have issuelist and comments fragment extend from BaseSwipeToRefreshFragment

    private fun setUpComments(comments: List<Comment>) {
        Log.e(TAG, comments.toString())
    }

    private fun hideLoading() {
        toggleListVisibility(true)
    }

    private fun showLoading() {
        toggleListVisibility(false)
    }

    private fun toggleListVisibility(shouldShowList: Boolean) {
        swipe_container.isRefreshing = !shouldShowList
        comments_list.text = comments.joinToString { comment ->
            comment.body + comment.user.name
        }
    }

    private fun showErrorMessage(error: String) {
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
    }
}