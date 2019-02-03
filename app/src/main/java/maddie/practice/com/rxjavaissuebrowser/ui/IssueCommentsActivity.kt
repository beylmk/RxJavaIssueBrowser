package maddie.practice.com.rxjavaissuebrowser.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import maddie.practice.com.rxjavaissuebrowser.R
import maddie.practice.com.rxjavaissuebrowser.ui.IssueListFragment.Companion.EXTRA_ISSUE_ID

class IssueCommentsActivity : AppCompatActivity() {

    var issueId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.issue_comments)

        if (intent.hasExtra(EXTRA_ISSUE_ID)) {
            issueId = intent.getLongExtra(EXTRA_ISSUE_ID, 0L)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}