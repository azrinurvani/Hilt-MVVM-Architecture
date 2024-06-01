package com.azrinurvani.latihanhiltmvvm.presentation.history

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.azrinurvani.latihanhiltmvvm.R
import com.azrinurvani.latihanhiltmvvm.databinding.FragmentHistoryBinding
import com.azrinurvani.latihanhiltmvvm.presentation.adapter.NewsHistoryAdapter
import com.azrinurvani.latihanhiltmvvm.presentation.news_detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

//TODO - Step 49
@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private var _binding : FragmentHistoryBinding? = null
    private val binding get() = _binding

    private val viewModel : HistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHistoryBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    //TODO - Step 54
    private fun observeViewModel(){
        viewModel.getAllHistory.observe(viewLifecycleOwner){
            if (!it.isNullOrEmpty()){
                val historyAdapter = NewsHistoryAdapter(it)
                binding?.tvState?.visibility = View.GONE
                binding?.rvHistory?.apply {
                    visibility = View.VISIBLE
                    adapter = historyAdapter
                    layoutManager = LinearLayoutManager(context)
                }
                historyAdapter.setActionClick(
                    actionClick = {
                        activity?.let { context ->
                            val intent = Intent(context,DetailActivity::class.java)
                            intent.putExtra(Intent.EXTRA_HTML_TEXT,it.url)
                            context.startActivity(intent)
                        }
                    }
                )
            }else{
                binding?.rvHistory?.visibility = View.GONE
                binding?.tvState?.apply {
                    visibility = View.VISIBLE
                    text = context.getString(R.string.state_empty_history)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "HistoryFragment"
    }
}