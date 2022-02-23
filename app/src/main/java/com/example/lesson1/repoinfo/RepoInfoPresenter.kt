package com.example.lesson1.repoinfo

import com.example.lesson1.interfaces.RepoInfoView
import com.example.lesson1.model.Repository
import com.example.lesson1.model.RetrofitGitHubUserRepo
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class RepoInfoPresenter (
    val repositoryUrl: String? = null,
    private val githubUsersRepo: RetrofitGitHubUserRepo,
    val router: Router
) : MvpPresenter<RepoInfoView>() {
    val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        if (repositoryUrl != null) {
            viewState.init()
            githubUsersRepo
                .getRepositoryByUrl(repositoryUrl)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Repository> {
                    override fun onSubscribe(d: Disposable?) {
                        disposables.add(d)
                    }

                    override fun onSuccess(repoInfo: Repository?) {
                        if (repoInfo != null) {
                            repoInfo.let {
                                viewState.showLogin(it.owner.login)
                                viewState.showNameRepository(it.name)
                                viewState.showDescriptionRepository(
                                    it.description
                                            + " \nЗвездный рейтинг: " + it.stargazersCount
                                            + " \nКоличество наблюдателей: " + it.watchersCount
                                )
                            }
                        }
                    }

                    override fun onError(e: Throwable?) {
                    }
                })
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        disposables.clear()
    }
}