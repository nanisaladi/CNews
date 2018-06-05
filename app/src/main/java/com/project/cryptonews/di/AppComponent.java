package com.project.cryptonews.di;

import android.app.Application;

import com.project.cryptonews.CryptoNewsApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by sakethramk on 2/15/18.
 */

@Singleton
@Component(modules = {AppModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class,
        FragmentBuildersModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(CryptoNewsApp cryptoNewsApp);
}
