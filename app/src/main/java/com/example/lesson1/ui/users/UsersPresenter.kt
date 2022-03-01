package com.example.lesson1.ui.users

import com.example.lesson1.App
import com.example.lesson1.domain.model.GithubUserModel
import com.example.lesson1.domain.users.IGithubUsersRepository
import com.example.lesson1.screens.AppScreens
import com.example.lesson1.screens.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject


class UsersPresenter @Inject constructor(
    private val router: Router,
    private val usersRepository: IGithubUsersRepository,
    private val screens: IScreens,
) : MvpPresenter<UsersView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        App.instance.destroyUserScope()
    }

    private fun loadData() {
        usersRepository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { users ->
                    viewState.updateList(users)
                }, {
                    viewState.showError(it.message)
                }
            )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun onUserClicked(githubUserModel: GithubUserModel) {
        router.navigateTo(screens.reposScreen(githubUserModel))
    }
}




