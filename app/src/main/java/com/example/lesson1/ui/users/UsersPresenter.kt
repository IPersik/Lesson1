package com.example.lesson1.ui.users

import com.example.lesson1.AndroidScreens
import com.example.lesson1.domain.GithubUsersRepo
import com.example.lesson1.model.GithubUser
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UsersPresenter(
    private val router: Router,
    private val usersRepository: GithubUsersRepo,
) : MvpPresenter<UsersView>() {

    class UsersListPresenter  {
        val users = mutableListOf<GithubUser>()
        var itemClickListener: ((UserItemView) -> Unit)? = null

         fun getCount() = users.size

         fun bindView(view: UserItemView) {
            val user = users[view.pos]
            }
        }


    val usersListPresenter = UsersPresenter.UsersListPresenter()
    val disposables = CompositeDisposable()

    fun goToImageConverter() {
        router.navigateTo(AndroidScreens().imageConverter())
    }


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

    override fun onDestroy() {
        disposables.clear()
    }

    fun onUserClicked() {
        // todo
    }


}




