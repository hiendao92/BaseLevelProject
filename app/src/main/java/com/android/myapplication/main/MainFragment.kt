package com.android.myapplication.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.myapplication.base.BaseFragment
import com.android.myapplication.data.model.DefaultError
import com.android.myapplication.data.model.NetworkError
import com.android.myapplication.databinding.FragmentMainBinding
import com.android.myapplication.extensions.onSuccess
import com.android.myapplication.main.cites.CitiesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn

/**
 * @author at-hien.dao
 */
@AndroidEntryPoint
class MainFragment : BaseFragment() {
    private var binding: FragmentMainBinding? = null

    private val viewModel: MainViewModel by viewModels()
    private val adapter: CitiesAdapter by lazy {
        CitiesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.loadingState().collect {
                // bind loading state
            }
            viewModel.errorState().collect {
                // bind error state
                when (it) {
                    is NetworkError -> {

                    }
                    is DefaultError -> {

                    }
                    else -> {

                    }
                }
            }
        }
        initViews()
        initListeners()
    }

    private fun initViews() {
        binding?.rvCities?.apply {
            adapter = this@MainFragment.adapter
            layoutManager =
                LinearLayoutManager(requireContext())
        }
        viewModel.getCities().onSuccess {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)
    }

    private fun initListeners() {

    }

    override fun onBindViewModel() {

    }
}
