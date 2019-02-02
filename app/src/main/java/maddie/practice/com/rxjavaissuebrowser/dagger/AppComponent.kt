package maddie.practice.com.rxjavaissuebrowser.dagger

import android.app.Application

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component

@Singleton
@Component(modules = arrayOf(ActivityModule::class, FragmentModule::class, AppModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}
