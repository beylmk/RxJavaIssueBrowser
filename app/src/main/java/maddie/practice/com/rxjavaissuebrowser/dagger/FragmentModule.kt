package maddie.practice.com.rxjavaissuebrowser.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import maddie.practice.com.rxjavaissuebrowser.ui.comment.CommentsFragment
import maddie.practice.com.rxjavaissuebrowser.ui.issue.IssueListFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun contributeIssueListFragment(): IssueListFragment

    @ContributesAndroidInjector
    internal abstract fun contributeIssueCommentsFragment(): CommentsFragment
}