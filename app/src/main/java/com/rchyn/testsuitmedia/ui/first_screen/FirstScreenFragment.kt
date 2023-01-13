package com.rchyn.testsuitmedia.ui.first_screen

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import com.rchyn.testsuitmedia.R
import com.rchyn.testsuitmedia.databinding.FragmentFirstScreenBinding
import com.rchyn.testsuitmedia.ui.MainActivity
import com.rchyn.testsuitmedia.utils.setWindowFullBackground


class FirstScreenFragment : Fragment() {

    private var _binding: FragmentFirstScreenBinding? = null
    private val binding get() = _binding as FragmentFirstScreenBinding

    private lateinit var act: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        act = activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstScreenBinding.inflate(layoutInflater, container, false)
        act.window.setWindowFullBackground(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            setupName()
        }

        binding.btnCheck.setOnClickListener {
            setupPalindrome()
        }

    }

    private fun setupName() {
        val name = binding.edtName.text.toString().trim()
        val nameCorrect = name.isNotEmpty()
        showErrorTextField(
            !nameCorrect,
            getString(R.string.message_text_field_empty, getString(R.string.hint_name)),
            textField = binding.layoutEdtName
        )

        if (nameCorrect) {
            navigateToSecondScreen(name)
            binding.edtName.text?.clear()
        }
    }

    private fun setupPalindrome() {
        val palindrome = binding.edtPalindrome.text.toString().trim()
        val palindromeCorrect = palindrome.isNotEmpty()
        showErrorTextField(
            isError = !palindromeCorrect,
            getString(R.string.message_text_field_empty, getString(R.string.hint_palindrome)),
            textField = binding.layoutEdtPalindrome
        )
        if (palindromeCorrect) {
            showDialogCheckPalindrome(palindrome, checkPalindrome(palindrome))
            binding.edtPalindrome.text?.clear()
        }
    }

    private fun showDialogCheckPalindrome(word: String, isPalindrome: Boolean) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.result)
            .setMessage(
                if (isPalindrome) getString(
                    R.string.message_is_palindrome,
                    word
                ) else getString(R.string.message_is_not_palindrome, word)
            )
            .setPositiveButton(R.string.back) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun checkPalindrome(word: String): Boolean {
        val text = word.replace("\\s".toRegex(), "")
        val wordReversed = text.reversed()
        return text.equals(wordReversed, ignoreCase = true)
    }


    private fun showErrorTextField(
        isError: Boolean = true,
        message: String? = null,
        textField: TextInputLayout
    ) {
        textField.apply {
            error = message
            isErrorEnabled = isError
        }
    }

    private fun navigateToSecondScreen(name: String) {
        val direction = FirstScreenFragmentDirections.actionFirstScreenNavToSecondScreenNav(
            name = name,
            username = null
        )
        findNavController().navigate(direction)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}