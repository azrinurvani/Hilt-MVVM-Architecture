package com.azrinurvani.latihanhiltmvvm.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.azrinurvani.latihanhiltmvvm.R
import com.azrinurvani.latihanhiltmvvm.data.Resource
import com.azrinurvani.latihanhiltmvvm.databinding.FragmentHomeBinding
import com.azrinurvani.latihanhiltmvvm.presentation.adapter.ParentMainAdapter
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    companion object {
        private const val TAG = "HomeFragment"
    }
}