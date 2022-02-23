package com.example.lesson1.ui.users

import com.example.lesson1.screens.AndroidScreens
import com.example.lesson1.interfaces.*
import com.example.lesson1.model.GithubUser
import com.example.lesson1.model.RetrofitGitHubUserRepo
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepo: IGithubUsersRepository,
    private val router: Router,
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login.let {
                view.setLogin(user.login)
            }
        }
    }

    val usersListPresenter = UsersListPresenter()
    val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        usersRepo.getUsers()
            .subscribe(object : SingleObserver<List<GithubUser>> {
                override fun onSubscribe(d: Disposable?) {
                    disposables.add(d)
                }

                override fun onSuccess(t: List<GithubUser>?) {
                    if (t == null) {
                        if (t != null) {
                            usersListPresenter.users.addAll(t)
                        }
                        usersListPresenter.itemClickListener = { itemView ->
                            router.navigateTo(AndroidScreens().userInfo(t?.get(itemView.pos)!!.login))
                        }
                        viewState.updateList()
                    }
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

    fun goToImageConverter() {
        router.navigateTo(AndroidScreens().imageConverter())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        disposables.clear()
    }
}




