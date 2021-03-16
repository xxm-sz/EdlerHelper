package com.zhangws.elderhelper

import android.content.pm.PackageInfo
import android.content.pm.ResolveInfo
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.DraggableModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhangws.elderhelper.database.AppInfo

class AppAdapter(layoutResId: Int, data: MutableList<AppInfo>) :
    BaseQuickAdapter<AppInfo, BaseViewHolder>(layoutResId, data),DraggableModule {
    override fun convert(holder: BaseViewHolder, item: AppInfo) {
        val ivApp = holder.itemView.findViewById<ImageView>(R.id.ivApp)
        val tvName = holder.itemView.findViewById<TextView>(R.id.tvName)

        ivApp.setImageDrawable(item.drawable)
        tvName.text = item.name

    }


}