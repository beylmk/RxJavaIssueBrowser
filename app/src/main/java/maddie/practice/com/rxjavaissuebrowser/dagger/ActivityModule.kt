package maddie.practice.com.rxjavaissuebrowser.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import maddie.practice.com.rxjavaissuebrowser.ui.comment.CommentsActivity
import maddie.practice.com.rxjavaissuebrowser.ui.issue.IssuesActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeIssuesActivity(): IssuesActivity

    @ContributesAndroidInjector
    abstract fun contributeIssueCommentsActivity(): CommentsActivity
}