package maddie.practice.com.rxjavaissuebrowser.ui.issue

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.issue_row.view.*
import maddie.practice.com.rxjavaissuebrowser.R
import maddie.practice.com.rxjavaissuebrowser.model.issue.Issue

class IssueAdapter(
    val issues: List<Issue>,
    private val issueClickCallback: ((Issue) -> Unit)?
) : RecyclerView.Adapter<IssueAdapter.IssueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        return IssueViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.issue_row, parent, false)
        )
    }

    override fun getItemCount(): Int = issues.size

    override fun onBindViewHolder(viewHolder: IssueViewHolder, position: Int) {
        val issue = issues[position]

        with(viewHolder.itemView) {
            issue_title.text = issue.title
            issue_body.text = issue.body.take(context.resources.getInteger(R.integer.max_length_issue_body))
            setOnClickListener{
                issueClickCallback?.invoke(issue)
            }
        }
    }

    inner class IssueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}