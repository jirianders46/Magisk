package com.topjohnwu.magisk.ui.module

import android.os.Bundle
import android.view.View
import com.topjohnwu.magisk.MainDirections
import com.topjohnwu.magisk.R
import com.topjohnwu.magisk.arch.BaseFragment
import com.topjohnwu.magisk.arch.viewModel
import com.topjohnwu.magisk.core.Const
import com.topjohnwu.magisk.databinding.FragmentModuleMd2Binding
import com.topjohnwu.magisk.databinding.RvItem
import com.topjohnwu.magisk.databinding.adapterOf
import rikka.recyclerview.addEdgeSpacing
import rikka.recyclerview.addInvalidateItemDecorationsObserver
import rikka.recyclerview.addItemSpacing
import rikka.recyclerview.fixEdgeEffect

class ModuleFragment : BaseFragment<FragmentModuleMd2Binding>() {

    override val layoutRes = R.layout.fragment_module_md2
    override val viewModel by viewModel<ModuleViewModel>()

    override fun onStart() {
        super.onStart()
        activity?.title = resources.getString(R.string.modules)
        viewModel.data.observe(this) {
            it ?: return@observe
            MainDirections.actionFlashFragment(Const.Value.FLASH_ZIP, it).navigate()
            viewModel.data.value = null
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.moduleList.apply {
            adapter = adapterOf<RvItem>()
            addEdgeSpacing(top = R.dimen.l_50, bottom = R.dimen.l1)
            addItemSpacing(R.dimen.l1, R.dimen.l_50, R.dimen.l1)
            fixEdgeEffect()
            post { addInvalidateItemDecorationsObserver() }
        }
    }

    override fun onPreBind(binding: FragmentModuleMd2Binding) = Unit

}
