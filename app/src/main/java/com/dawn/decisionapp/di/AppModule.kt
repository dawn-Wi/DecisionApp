package com.dawn.decisionapp.di

import android.content.Context
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import com.dawn.decisionapp.remote.DecisionApi
import com.dawn.decisionapp.repository.DecisionRepository
import com.dawn.decisionapp.service.SnackbarService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNavController(@ApplicationContext context: Context)=
        NavHostController(context).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
            navigatorProvider.addNavigator(DialogNavigator())
        }


    @Singleton
    @Provides
    fun provideSnackbarService() = SnackbarService()

    @Singleton
    @Provides
    fun provideDecisionRepository(decisionApi: DecisionApi) = DecisionRepository(decisionApi)

    @Singleton
    @Provides
    fun provideTestSpring():DecisionApi{
        val baseUrl="http://10.0.2.2:8080"

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DecisionApi::class.java)
    }
}