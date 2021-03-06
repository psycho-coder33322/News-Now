package com.akshatsahijpal.newsnow.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshatsahijpal.newsnow.adapter.NewsAdapter
import com.akshatsahijpal.newsnow.databinding.FragmentSearchBinding
import com.akshatsahijpal.newsnow.ui.viewmodel.RefinedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var adapter = NewsAdapter()
    private lateinit var _binding: FragmentSearchBinding
    private val model by viewModels<RefinedViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.apply {
            recyclerViewA.adapter = adapter
            recyclerViewA.layoutManager = LinearLayoutManager(requireContext())
            SearchFieldT.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    Toast.makeText(requireContext(), "text-> ${s.toString()}", Toast.LENGTH_SHORT)
                        .show()
                    model.getRefinedData(s?.trim().toString()).observe(viewLifecycleOwner) {
                        GlobalScope.launch { adapter.submitData(it) }
                    }
                }
            })
        }
    }
}