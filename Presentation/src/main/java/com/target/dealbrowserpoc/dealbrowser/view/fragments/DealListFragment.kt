package com.target.dealbrowserpoc.dealbrowser.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.target.businesslogic.model.Product
import com.target.dealbrowserpoc.dealbrowser.TargetApplication
import com.target.dealbrowserpoc.dealbrowser.common.view.BaseFragment
import com.target.dealbrowserpoc.dealbrowser.databinding.FragmentDealListBinding
import com.target.dealbrowserpoc.dealbrowser.view.DealViewModel
import com.target.dealbrowserpoc.dealbrowser.view.adapters.DealListItemAdapter
import com.target.framework.model.toParcelable

/**
 * A simple [Fragment] subclass.
 */
class DealListFragment : BaseFragment(), DealListItemAdapter.DealListItemListener {

    companion object {
        val TAG = DealListFragment::class.java.simpleName
    }

    var dealViewModel: DealViewModel? = null

    private lateinit var binding: FragmentDealListBinding

    private var adapter: DealListItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        initializeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDealListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpUi()
    }

    private fun initializeViewModel() {
        activity?.let { activity ->
            activity.application?.let {
                if (it is TargetApplication) {
                    dealViewModel =
                        DealViewModel(it.appContainer.productDealsUseCaseImpl)
                }
            }
        }
    }

    private fun setUpUi() {
        setUpToolBar()
        initializeRecyclerView()
        initializeDealsApiCall()
    }

    private fun setUpToolBar() {
        binding.apply {
            (activity as AppCompatActivity).setSupportActionBar(toolbar.toolBar)
            (activity as AppCompatActivity).supportActionBar?.title = "Target Deals"
            (activity as AppCompatActivity).supportActionBar?.subtitle = "Enjoy!!"
        }
    }

    private fun initializeRecyclerView() {
        adapter = DealListItemAdapter(this@DealListFragment)
        if (isTablet) {
            binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        } else {
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = adapter
        }
    }

    private fun initializeDealsApiCall() {
        dealViewModel?.loadData()?.observe(viewLifecycleOwner, Observer { list ->
            if (list.isNotEmpty()) {
                adapter?.updateList(list)
            }
        })
    }

    override fun onDealListItemClicked(product: Product) {
        logMessage(TAG, "${product.title}")
        val productModel = product.toParcelable()
        val directions =
            DealListFragmentDirections.actionDealListFragmentToDealDetailsFragment(productModel)
        findNavController().navigate(directions)
    }
}