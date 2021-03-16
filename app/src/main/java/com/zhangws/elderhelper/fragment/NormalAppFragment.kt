package com.zhangws.elderhelper.fragment

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhangws.elderhelper.R
import com.zhangws.elderhelper.adapter.NormalAppAdapter
import com.zhangws.elderhelper.database.AppDao
import com.zhangws.elderhelper.database.AppInfo
import com.zhangws.elderhelper.database.DataBase

class NormalAppFragment : AppBaseFragment() {
    override val type: Int = 1

    override val layoutIds: Int = R.layout.fragment_app

    lateinit var appDao: AppDao

    override fun createAdapter(): BaseQuickAdapter<AppInfo, BaseViewHolder> {
        appDao = DataBase.getInstance().appDao()

        return NormalAppAdapter(R.layout.item_app_add).apply {
            setOnItemChildClickListener { adapter, view, position ->
                val item = (adapter.data[position] as AppInfo)
                if (item.add == 1) {
                    item.add = 0
                    appDao.removeApp(item)
                } else {
                    item.add = 1
                    appDao.saveApp(item)
                }
                notifyItemChanged(position)
            }
            addChildClickViewIds(R.id.tvAdd)
        }
    }
}