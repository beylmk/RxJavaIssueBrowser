package maddie.practice.com.rxjavaissuebrowser.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import maddie.practice.com.rxjavaissuebrowser.R

class IssueListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.issue_list, container, false)
    }
}