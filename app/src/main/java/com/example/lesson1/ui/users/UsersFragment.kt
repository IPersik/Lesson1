package com.example.lesson1.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson1.App
import com.example.lesson1.databinding.FragmentUsersBinding
import com.example.lesson1.domain.model.GithubUserModel
import com.example.lesson1.domain.users.GithubUsersRepository
import com.example.lesson1.network.ApiHolder
import com.example.lesson1.network.NetworkStatus
import com.example.lesson1.ui.base.BackButtonListener
import com.example.lesson1.ui.base.GlideImageLoader
import com.example.lesson1.ui.users.adapter.UsersAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private val presenter by moxyPresenter {
        UsersPresenter(
            App.instance.router,
            GithubUsersRepository(
                ApiHolder.githubApiService,
                App.instance.database.userDao,
                NetworkStatus(requireContext())
            )
        )
    }

    private var _binding: FragmentUsersBinding? = null
    private val binding
        get() = _binding!!

    private val adapter by lazy {
        UsersAdapter(GlideImageLoader(), presenter::onUserClicked)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
    _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.usersRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.usersRecycler.adapter = adapter
    }

    override fun updateList(users: List<GithubUserModel>) {
        adapter.submitList(users)
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }

    override fun showError(message: String?) {
        Toast.makeText(requireContext(), message.orEmpty(), Toast.LENGTH_SHORT)
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        fun newInstance(): UsersFragment {
            return UsersFragment()
        }
    }
}
