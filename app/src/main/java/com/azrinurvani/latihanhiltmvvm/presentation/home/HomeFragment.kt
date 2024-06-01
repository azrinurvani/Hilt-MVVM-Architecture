package com.azrinurvani.latihanhiltmvvm.presentation.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.azrinurvani.latihanhiltmvvm.R
import com.azrinurvani.latihanhiltmvvm.data.Resource
import com.azrinurvani.latihanhiltmvvm.databinding.FragmentHomeBinding
import com.azrinurvani.latihanhiltmvvm.domain.model.News
import com.azrinurvani.latihanhiltmvvm.presentation.adapter.NewsHistoryAdapter
import com.azrinurvani.latihanhiltmvvm.presentation.adapter.ParentMainAdapter
import com.azrinurvani.latihanhiltmvvm.presentation.news_detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

//TODO - Step 34
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding

    private val viewModel : HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeHeadlineNews()
        //TODO - Step 65
        actionSearchNews()
    }

    private fun subscribeHeadlineNews(){
        viewModel.getAllHeadlines.observe(viewLifecycleOwner){ result->
            when(result){
                is Resource.Error -> showError(result.message.toString())
                is Resource.Loading -> {
                    binding?.rvMain?.visibility = View.GONE
                    showLoading(true)
                }
                is Resource.Success ->{
                    showLoading(false)
                    if (!result.data.isNullOrEmpty()){
                        val mainAdapter = ParentMainAdapter(result.data ?: emptyList())
                        binding?.rvMain?.apply {
                            visibility = View.VISIBLE
                            layoutManager = LinearLayoutManager(context)
                            adapter = mainAdapter
                        }

                        mainAdapter.setActionClick(
                            actionClick = {
                                //TODO - Step 56
                                initActionClickAdapter(it)
                                Log.d(TAG, "adapter click ${it.id}: ")
                            }
                        )
                    }else{
                        showEmptyData()
                    }
                }
            }
        }
    }

    //TODO - Step 63
    private fun subscribeNewsSearch(query:String){
        viewModel.getNewsSearch(query).observe(viewLifecycleOwner){ result->
            when(result){
                is Resource.Loading ->{
                    binding?.rvMain?.visibility = View.GONE
                    showLoading(true)
                }
                is Resource.Error ->{
                    showLoading(false)
                    showError(error = result.message.toString())
                }
                is Resource.Success ->{
                    showLoading(false)
                    if (!result.data.isNullOrEmpty()){
                        val newsAdapter = NewsHistoryAdapter(result.data)
                        binding?.rvMain?.apply {
                            visibility = View.VISIBLE
                            layoutManager = LinearLayoutManager(context)
                            adapter = newsAdapter
                        }
                        newsAdapter.setActionClick {
                            initActionClickAdapter(it)
                        }
                    }else {
                        showEmptyData()
                    }
                }
            }
        }
    }

    //TODO - Step 64
    private fun actionSearchNews(){
        binding?.searchView?.apply {
            queryHint = context.getString(R.string.search_news_hint)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query.isNullOrBlank()){
                        subscribeHeadlineNews()
                    }else{
                        subscribeNewsSearch(query)
                    }
                    hideKeyboard()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText.isNullOrBlank()){
                        subscribeHeadlineNews()
                        hideKeyboard()
                    }
                    return false
                }

            })
        }
    }

    //TODO - Step 55
    private fun initActionClickAdapter(news: News) {
        viewModel.insertHistory(news)
        activity?.let { context ->
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(Intent.EXTRA_HTML_TEXT, news.url)
            context.startActivity(intent)
        }

    }

    private fun showLoading(state: Boolean){
        binding?.apply {
            scrollShimmer.isVisible = state
            shimmerLoading.apply {
                if (state){
                    startShimmer()
                }else{
                    stopShimmer()
                }
            }
            tvState.visibility = View.GONE
        }
    }

    private fun showError(error:String){
        showLoading(false)
        binding?.rvMain?.visibility = View.GONE
        binding?.tvState?.apply {
            visibility = View.VISIBLE
            text = error
        }
    }

    private fun showEmptyData() {
        binding?.rvMain?.visibility = View.GONE
        binding?.tvState?.apply {
            visibility = View.VISIBLE
            text = context.getString(R.string.state_empty)
        }
    }

    //TODO - Step 65
    private fun hideKeyboard() {
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding?.searchView?.windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        private const val TAG = "HomeFragment"
    }
}