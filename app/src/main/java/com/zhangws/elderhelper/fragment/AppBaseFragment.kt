package com.zhangws.elderhelper.fragment

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhangws.elderhelper.*
import com.zhangws.elderhelper.adapter.AddedAdapter
import com.zhangws.elderhelper.database.AppInfo

abstract class AppBaseFragment : BaseFragment() {

    abstract val type: Int

    override val layoutIds: Int = R.layout.fragment_app

    val model by lazy {
        ViewModelProvider(this).get(AppModel::class.java)
    }

    private lateinit var recyclerView: RecyclerView

    private lateinit var loadingBar: ProgressBar

    private lateinit var adapter: BaseQuickAdapter<AppInfo, BaseViewHolder>

    override fun initData() {

    }

    override fun onResume() {
        super.onResume()
        Log.d("app", "onResume")
        model.getList(type) {
            adapter.setNewInstance(it)
            loadingBar.visibility = View.GONE
            if (it.isEmpty()) {
                adapter.emptyLayout?.findViewById<TextView>(R.id.tvEmpty)?.text = "空空如也"
            }
        }
    }

    override fun initListener(view: View) {

    }

    override fun initView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        loadingBar = view.findViewById(R.id.progressbar)
        adapter = createAdapter()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.onAttachedToRecyclerView(recyclerView)
        adapter.setEmptyView(R.layout.layout_list_empty)
    }

    abstract fun createAdapter(): BaseQuickAdapter<AppInfo, BaseViewHolder>
}