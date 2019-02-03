package maddie.practice.com.rxjavaissuebrowser.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.issue_comments_list.*
import maddie.practice.com.rxjavaissuebrowser.R
import maddie.practice.com.rxjavaissuebrowser.ui.IssueListFragment.Companion.EXTRA_ISSUE_ID

class IssueCommentsFragment : Fragment() {
    companion object {
        const val TAG = "IssueCommentsFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.issue_comments_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        issue_id_text.text = (activity as IssueCommentsActivity).issueId.toString()

    }
}