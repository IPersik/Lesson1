package com.example.lesson1.ui.users

import com.example.lesson1.domain.GithubUsersRepo
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(
    private val router: Router,
    private val usersRepository: GithubUsersRepo,
) : MvpPresenter<UsersView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() {
        val users = usersRepository.getUsers()
        viewState.updateList(users)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun onUserClicked() {
        // todo
    }


}




