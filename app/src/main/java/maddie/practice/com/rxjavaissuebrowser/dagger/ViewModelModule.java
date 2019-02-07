package maddie.practice.com.rxjavaissuebrowser.dagger;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import maddie.practice.com.rxjavaissuebrowser.ui.comment.CommentsViewModel;
import maddie.practice.com.rxjavaissuebrowser.ui.issue.IssueListViewModel;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(IssueListViewModel.class)
    abstract ViewModel bindGithubIssueListViewModel(IssueListViewModel issueListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CommentsViewModel.class)
    abstract ViewModel bindCommentsViewModel(CommentsViewModel commentsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}