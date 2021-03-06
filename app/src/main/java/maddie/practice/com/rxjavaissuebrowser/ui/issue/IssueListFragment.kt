package maddie.practice.com.rxjavaissuebrowser.ui.issue

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.issue_list.*
import maddie.interviews.com.doordashlite.api.ResourceObserver
import maddie.practice.com.rxjavaissuebrowser.R
import maddie.practice.com.rxjavaissuebrowser.model.issue.Issue
import maddie.practice.com.rxjavaissuebrowser.ui.comment.CommentsActivity
import javax.inject.Inject

class IssueListFragment : Fragment() {

    companion object {
        private const val TAG = "IssueListFragment"
        const val EXTRA_ISSUE_ID = "issue_id"
    }

    @Inject
    lateinit var githubIssueListViewModelFactory: ViewModelProvider.Factory
    private lateinit var issueListViewModel: IssueListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.issue_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidSupportInjection.inject(this)

        issueListViewModel = ViewModelProviders.of(this, githubIssueListViewModelFactory).get(IssueListViewModel::class.java)
        issueListViewModel.getRxJavaIssues().observe(
            this, ResourceObserver(
            TAG,
            hideLoading = ::hideLoading,
            showLoading = ::showLoading,
            onSuccess = ::setUpIssueList,
            onError = ::showErrorMessage
        ))
        swipe_container.setOnRefreshListener {
            issueListViewModel.refresh()
        }
    }

    private fun setUpIssueList(issues: List<Issue>) {
        issues_recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = IssueAdapter(issues) { issue ->
                val commentsIntent = Intent(activity, CommentsActivity::class.java)
                commentsIntent.putExtra(EXTRA_ISSUE_ID, issue.number)
                activity?.startActivity(commentsIntent)
            }
            addItemDecoration(
                DividerItemDecoration(
                    activity,
                    DividerItemDecoration.VERTICAL
                )
            )
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
        issues_recycler_view.visibility = if (shouldShowList) View.VISIBLE else View.GONE
    }

    private fun showErrorMessage(error: String) {
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
    }
}