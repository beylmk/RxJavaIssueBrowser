package maddie.practice.com.rxjavaissuebrowser.dagger

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.v4.app.Fragment

import javax.inject.Inject

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector

class App : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<Fragment>

    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        this.initDagger()
        context = applicationContext
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    private fun initDagger() {
        DaggerAppComponent.builder().application(this).build().inject(this)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return dispatchingFragmentInjector
    }
}
