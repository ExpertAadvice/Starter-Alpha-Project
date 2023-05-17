package com.learn.alpha.ui.fragments.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learn.alpha.R
import com.learn.alpha.databinding.FragmentSettingBinding
import com.learn.alpha.databinding.UiRecyclerRowBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {

	private lateinit var binding: FragmentSettingBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		val view = inflater.inflate(R.layout.fragment_setting, container, false)
		binding = FragmentSettingBinding.bind(view)

		return binding.root
	}


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)


		val list = mutableListOf<String>()
		list.add("First")
		list.add("Second")

		val uiAdapter = UiRvAdapter(
			OnRecyclerItemClick {
				findNavController().popBackStack()
			},
		)
		uiAdapter.itemList = list

		binding.recyclerView.apply {
			layoutManager = LinearLayoutManager(context)
			adapter = uiAdapter
		}

	}
}

class OnRecyclerItemClick(val block: (String) -> Unit) {
	fun onClick(title: String) = block(title)
}


class UiRvAdapter(private val callback: OnRecyclerItemClick) : RecyclerView.Adapter<UiViewHolder>() {

	private lateinit var rvBinding: UiRecyclerRowBinding
	var itemList: List<String> = emptyList()
		set(value) {
			field = value
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UiViewHolder {

		val view = LayoutInflater.from(parent.context).inflate(UiViewHolder.LAYOUT, parent, false)
		rvBinding = UiRecyclerRowBinding.bind(view)
		return UiViewHolder(rvBinding)
	}

	override fun getItemCount() = itemList.size

	override fun onBindViewHolder(holder: UiViewHolder, position: Int) {
		holder.rvBinding.also {
			it.titleString = itemList[position]
			it.callback = callback
		}
	}
}

class UiViewHolder(val rvBinding: UiRecyclerRowBinding) :
	RecyclerView.ViewHolder(rvBinding.root) {

	companion object {
		@LayoutRes
		val LAYOUT = R.layout.ui_recycler_row
	}
}