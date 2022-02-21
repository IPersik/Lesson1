package com.example.lesson1.ui.users

import com.example.lesson1.screens.AndroidScreens
import com.example.lesson1.domain.GithubUsersRepo
import com.example.lesson1.model.GithubUser
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UsersPresenter(
    private val router: Router,
    private val usersRepository: GithubUsersRepo,
) : MvpPresenter<UsersView>() {


    fun goToImageConverter() {
        router.navigateTo(AndroidScreens().imageConverter())
    }


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
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


    fun onUserClicked() {
        // todo
    }


}




