package com.bondidos.rsschoolcurrency.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bondidos.rsschoolcurrency.databinding.FragmentHomeBinding
import com.bondidos.rsschoolcurrency.di.appComponent
import com.bondidos.rsschoolcurrency.presentation.fragments.vm.HomeViewModel
import com.bondidos.rsschoolcurrency.presentation.fragments.vm.HomeViewModelFactory
import com.bondidos.rsschoolcurrency.presentation.adapter.CurrencyAdapter
import com.bondidos.rsschoolcurrency.presentation.uiState.UiState
import javax.inject.Inject

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.injectMain(this)
    }

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory
    private val viewModel: HomeViewModel by viewModels { viewModelFactory }
    private val currencyAdapter: CurrencyAdapter by lazy {
        CurrencyAdapter(
            viewModel::onItemTapCallback, viewModel::onTextChanged, viewModel::resumeJob
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initUiFlows()
    }

    private fun initUiFlows() {
        lifecycleScope.launchWhenCreated {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is UiState.Loading -> binding.progressBar.isVisible = true
                    is UiState.Loaded -> {
                        binding.progressBar.isVisible = false
                        currencyAdapter.submitList(uiState.data)
                    }
                    else -> Unit
                }
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.uiEvent.collect { event ->
                Toast.makeText(context, event, Toast.LENGTH_SHORT).show()
                binding.progressBar.isVisible = false
            }
        }
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = currencyAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}