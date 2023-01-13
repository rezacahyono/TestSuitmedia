package com.rchyn.testsuitmedia.ui.third_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.rchyn.testsuitmedia.R
import com.rchyn.testsuitmedia.adapter.ListUserAdapter
import com.rchyn.testsuitmedia.databinding.FragmentThirdScreenBinding
import com.rchyn.testsuitmedia.ui.viewmodel.ViewModelFactory


class ThirdScreenFragment : Fragment() {

    private lateinit var listUserAdapter: ListUserAdapter

    private var _binding: FragmentThirdScreenBinding? = null
    private val binding get() = _binding as FragmentThirdScreenBinding

    private val thirdScreenViewModel: ThirdScreenViewModel by viewModels {
        ViewModelFactory.getInstance()
    }

    private val args: ThirdScreenFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listUserAdapter = ListUserAdapter { user ->
            navigateToSecondScreenWithUser(args.name,user.name)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdScreenBinding.inflate(layoutInflater, container, false)
        binding.toolbar.title = getString(R.string.third_screen)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { navigateToBack() }

        thirdScreenViewModel.users.observe(viewLifecycleOwner) { data ->
            listUserAdapter.submitData(lifecycle, data)
        }

        setupRecyclerUser()
    }

    private fun setupRecyclerUser() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerUser.apply {
            layoutManager = linearLayoutManager
            adapter = listUserAdapter
        }

        listUserAdapter.addLoadStateListener { loadState ->
            if (loadState.source.refresh is LoadState.NotLoading &&
                loadState.append.endOfPaginationReached && listUserAdapter.itemCount < 1
                || loadState.source.refresh is LoadState.Error
            ) {
                stateUsersState(false)
            } else {
                stateUsersState(true)
            }
        }
    }

    private fun stateUsersState(show: Boolean) {
        if (show) {
            binding.apply {
                recyclerUser.isVisible = true
                ivIconEmpty.isVisible = false
                tvMessageEmpty.isVisible = false
            }
        } else {
            binding.apply {
                recyclerUser.isVisible = false
                ivIconEmpty.isVisible = true
                tvMessageEmpty.isVisible = true
            }
        }
    }

    private fun navigateToSecondScreenWithUser(name: String?,username: String) {
        val direction = ThirdScreenFragmentDirections.actionThirdScreenNavToSecondScreenNav(
            name = name,
            username = username
        )
        findNavController().navigate(direction)
    }

    private fun navigateToBack() {
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}