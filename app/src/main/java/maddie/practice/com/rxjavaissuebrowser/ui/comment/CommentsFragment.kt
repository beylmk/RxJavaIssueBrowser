package maddie.practice.com.rxjavaissuebrowser.ui.comment

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.comments_list.*
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
        return inflater.inflate(R.layout.comments_list, container, false)
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

    private fun setUpComments(comments: List<Comment>) {
        comments_text.text = comments.joinToString(
            separator = getString(R.string.comments_separator)
        ) { comment ->
            comment.user.name + ": " + comment.body
        }
    }

    private fun hideLoading() {
        toggleListVisibility(true)
    }

    private fun showLoading() {
        toggleListVisibility(false)
    }

    private fun toggleListVisibility(shouldShowList: Boolean) {
        swipe_container.isRefreshing = !shouldShowList
        comments_text.visibility = if (shouldShowList) View.VISIBLE else View.GONE
    }

    private fun showErrorMessage(error: String) {
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
    }
}