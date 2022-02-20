package com.example.lesson1.ui.users

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson1.App
import com.example.lesson1.databinding.FragmentUserBinding
import com.example.lesson1.domain.GithubUsersRepo
import com.example.lesson1.model.GithubUser
import com.example.lesson1.ui.base.BackButtonListener
import com.example.lesson1.ui.users.adapter.UsersAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private val presenter by moxyPresenter {
        UsersPresenter(
            App.instance.router,
            GithubUsersRepo()
        )
    }

    private var _binding: FragmentUserBinding? = null
    private val binding
        get() = _binding!!


    private val adapter by lazy {
        UsersAdapter { presenter.onUserClicked() }
    }

    private var vb: FragmentUserBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

     fun init() {
        vb?.usersRecycler?.layoutManager = LinearLayoutManager(context)
        vb?.usersRecycler?.adapter = adapter
        vb?.btnGoToImgConverter?.setOnClickListener { presenter.goToImageConverter() }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.usersRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.usersRecycler.adapter = adapter
    }

    override fun updateList(users: List<GithubUser>) {
        adapter.submitList(users)
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }

}
