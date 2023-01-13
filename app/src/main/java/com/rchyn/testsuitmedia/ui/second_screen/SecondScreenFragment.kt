package com.rchyn.testsuitmedia.ui.second_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rchyn.testsuitmedia.R
import com.rchyn.testsuitmedia.databinding.FragmentSecondScreenBinding
import com.rchyn.testsuitmedia.ui.MainActivity
import com.rchyn.testsuitmedia.utils.setWindowFullBackground


class SecondScreenFragment : Fragment() {

    private lateinit var act: MainActivity

    private var _binding: FragmentSecondScreenBinding? = null
    private val binding get() = _binding as FragmentSecondScreenBinding

    private val args: SecondScreenFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        act = activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondScreenBinding.inflate(layoutInflater, container, false)
        binding.toolbar.title = getString(R.string.second_screen)
        act.window.setWindowFullBackground(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { navigateToBack() }

        val name = args.name
        if (!name.isNullOrEmpty()){
            binding.tvName.text = name
        }

        val username = args.username
        if (!username.isNullOrEmpty()){
            binding.tvSelectUserName.text = username
        }

        binding.btnChooseUser.setOnClickListener {
            navigateToThirdScreen(name)
        }
    }

    private fun navigateToThirdScreen(name: String?) {
        val direction = SecondScreenFragmentDirections.actionSecondScreenNavToThirdScreenNav(name)
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