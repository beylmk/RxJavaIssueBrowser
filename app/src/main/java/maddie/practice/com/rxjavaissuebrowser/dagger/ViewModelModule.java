package maddie.practice.com.rxjavaissuebrowser.dagger;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import maddie.practice.com.rxjavaissuebrowser.ui.GithubIssueListViewModel;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GithubIssueListViewModel.class)
    abstract ViewModel bindGithubIssueListViewModel(GithubIssueListViewModel githubIssueListViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}