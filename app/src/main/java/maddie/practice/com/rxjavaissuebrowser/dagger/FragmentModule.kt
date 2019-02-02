package maddie.practice.com.rxjavaissuebrowser.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import maddie.practice.com.rxjavaissuebrowser.ui.IssueListFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun contributeIssueListFragment(): IssueListFragment
}