package com.learn.alpha.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.learn.alpha.R
import com.learn.alpha.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

	private lateinit var binding: FragmentHomeBinding
	private val viewModel: HomeViewModel by viewModels()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		val view = inflater.inflate(R.layout.fragment_home, container, false)
		binding = FragmentHomeBinding.bind(view)

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		lifecycleScope.launch {
			viewModel.homeEvent.collectLatest {
				when (it) {
					true -> {
						Toast.makeText(requireContext(), "Done", Toast.LENGTH_SHORT).show()
					}

					false -> {
						Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
						findNavController().navigate(R.id.action_homeFragment_to_settingFragment)
					}
				}
			}
		}

		binding.tvHome.setOnClickListener {
			viewModel.doSomething()
		}

	}
}