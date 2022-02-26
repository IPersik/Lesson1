package com.example.lesson1.userInfo

import com.example.lesson1.interfaces.IReposListPresenter
import com.example.lesson1.interfaces.RepoItemView
import com.example.lesson1.interfaces.UserInfoView
import com.example.lesson1.model.GithubUserAdvanced
import com.example.lesson1.model.Repository
import com.example.lesson1.model.RetrofitGitHubUserRepo
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UserInfoPresenter(
    private val userLogin: String? = null,
    private val githubUsersRepo: RetrofitGitHubUserRepo,
    private val router: Router
) : MvpPresenter<UserInfoView>() {
    private val disposables = CompositeDisposable()

    class ReposListPresenter : IReposListPresenter {
        val repositories = mutableListOf<Repository>()
        override var itemClickListener: ((RepoItemView) -> Unit)? = null
        override fun getCount() = repositories.size

        override fun bindView(view: RepoItemView) {
            val repos = repositories[view.pos]
            repos.name.let {
                view.setName(repos.name)
                repos.description.let { view.setDescription((repos.description)) }
            }
        }
    }

    val reposListPresenter = ReposListPresenter()

    override fun onFirstViewAttach() {
        if (userLogin != null) {
            viewState.init()
            viewState.hideErrorBar()
            githubUsersRepo
                .getUserByLogin(userLogin)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<GithubUserAdvanced> {
                    override fun onSubscribe(d: Disposable?) {
                        disposables.add(d)
                    }

                    override fun onSuccess(userInfo: GithubUserAdvanced?) {
                        userInfo?.let {
                            onGetUserByLoginSuccess(it)
                        }
                    }

                    override fun onError(e: Throwable?) {
                        onGetUserByLoginError(e)
                    }
                })
        }
    }

    fun onGetUserByLoginSuccess(userInfo: GithubUserAdvanced) {
        viewState.showLogin(userInfo.login)
        viewState.setImageAvatar(userInfo.avatarUrl)
        viewState.showTopString("Заглушка верхей строки")
        githubUsersRepo
            .getUserRepos(userInfo.login, null, null, null, 99, 1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Repository>> {
                override fun onSubscribe(d: Disposable?) {
                    disposables.add(d)
                }

                override fun onSuccess(t: List<Repository>) {
                    onGetUserReposSuccess(t, userInfo)
                }

                override fun onError(e: Throwable?) {
                    onGetUserReposError(e)
                }
            })
        viewState.showBottomString("Заглушка нижней строки")
    }

    fun onGetUserByLoginError(e: Throwable?) {
        viewState.showErrorBar()
    }

    fun onGetUserReposSuccess(userRepositories: List<Repository>, userInfo: GithubUserAdvanced) {

        reposListPresenter.repositories.addAll(userRepositories)
        reposListPresenter.itemClickListener = { itemView ->
            router.navigateTo(AndroidScreens().repoInfo(userRepositories[itemView.pos].url))
        }
        viewState.updateList()
    }

    fun onGetUserReposError(e: Throwable?) {
        viewState.showTopString("Ошибка при попытке чтения спискаа репозиториев")
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        disposables.clear()
    }
}