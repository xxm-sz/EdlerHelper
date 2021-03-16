package com.zhangws.elderhelper.adapter

import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.DraggableModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhangws.elderhelper.R
import com.zhangws.elderhelper.database.AppInfo

class AddedAdapter(layoutResId: Int) : BaseQuickAdapter<AppInfo, BaseViewHolder>(layoutResId),
    DraggableModule {
    override fun convert(holder: BaseViewHolder, item: AppInfo) {
        holder.itemView.findViewById<ImageView>(R.id.icon).apply {
            setImageDrawable(item.drawable)
        }

        holder.itemView.findViewById<TextView>(R.id.tvName).apply {
            text = item.name
        }

        holder.itemView.findViewById<TextView>(R.id.tvPackage).apply {
            text = item.package_name + " " + item.appVersion
        }

        holder.itemView.findViewById<TextView>(R.id.tvType).apply {
            text = if (item.system == 1) "系统应用" else if(item.package_name=="com.zhangws.elderhelper") "本应用" else "第三方应用"

        }
    }


}