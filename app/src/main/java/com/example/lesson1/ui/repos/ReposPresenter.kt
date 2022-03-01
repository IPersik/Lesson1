package com.example.lesson1.ui.repos
import android.util.Log
import com.example.lesson1.App
import com.example.lesson1.domain.model.GithubRepoModel
import com.example.lesson1.domain.model.GithubUserModel
import com.example.lesson1.domain.repos.IGithubReposRepository
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class ReposPresenter @AssistedInject constructor(
    @Assisted private val userModel: GithubUserModel,
    private val reposRepository: IGithubReposRepository,
    private val router: Router,
) : MvpPresenter<ReposView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        reposRepository.getRepos(userModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { repos ->
                    viewState.showRepos(repos)
                },
                {
                    Log.e("Repos", "Error getting repos", it)
                }
            )

    }

    override fun onDestroy() {
        super.onDestroy()
        App.instance.destroyRepoScope()
    }

    fun onItemClicked(repo: GithubRepoModel) {
        // todo
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
@AssistedFactory
interface ReposPresenterFactory {

    fun presenter(userModel: GithubUserModel): ReposPresenter
}