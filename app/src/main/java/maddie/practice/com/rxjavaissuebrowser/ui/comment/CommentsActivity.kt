package maddie.practice.com.rxjavaissuebrowser.ui.comment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import maddie.practice.com.rxjavaissuebrowser.R
import maddie.practice.com.rxjavaissuebrowser.ui.issue.IssueListFragment.Companion.EXTRA_ISSUE_ID

class CommentsActivity : AppCompatActivity() {

    var issueNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.issue_comments)

        if (intent.hasExtra(EXTRA_ISSUE_ID)) {
            issueNumber = intent.getIntExtra(EXTRA_ISSUE_ID, 0)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}