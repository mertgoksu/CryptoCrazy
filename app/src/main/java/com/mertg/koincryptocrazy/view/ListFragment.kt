package com.mertg.koincryptocrazy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mertg.koincryptocrazy.databinding.FragmentListBinding
import com.mertg.koincryptocrazy.model.CryptoModel
import com.mertg.koincryptocrazy.service.CryptoAPI
import com.mertg.koincryptocrazy.viewmodel.CryptoViewModel
import kotlinx.coroutines.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.zip.ZipEntry


class ListFragment() : Fragment(), RecyclerViewAdapter.Listener, AndroidScopeComponent {

    private lateinit var binding: FragmentListBinding

    private var cryptoAdapter = RecyclerViewAdapter(arrayListOf(),this)

    private val viewModel by viewModel<CryptoViewModel>()

    override val scope: Scope by fragmentScope()
    private val hello by inject<String>()

    /*
    private val api = get<CryptoAPI>()

    private val apilazy by inject<CryptoAPI>()
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager

//        viewModel = ViewModelProvider(this).get(CryptoViewModel::class.java)
        viewModel.getDataFromAPI()

        observeLiveData()


    }

    private fun observeLiveData() {
        viewModel.cryptoList.observe(viewLifecycleOwner, Observer { cryptos ->
            cryptos?.let{
                binding.recyclerView.visibility = View.VISIBLE
                cryptoAdapter = RecyclerViewAdapter(ArrayList(cryptos.data ?: arrayListOf()),this@ListFragment)
                binding.recyclerView.adapter = cryptoAdapter
            }
        })

        viewModel.cryptoError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if(it.data == true){
                    binding.cryptoErrorText.visibility = View.VISIBLE
                } else{
                    binding.cryptoErrorText.visibility = View.GONE
                }
            }
        })

        viewModel.cryptoLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let{
                if(it.data == true){
                    binding.cryptoProgressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.cryptoErrorText.visibility = View.GONE
                }else{
                    binding.cryptoProgressBar.visibility = View.GONE
                }
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(requireContext(), "Clicked on: ${cryptoModel.name}", Toast.LENGTH_SHORT).show()
    }

}