package com.zhangws.elderhelper.adapter

import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhangws.elderhelper.R
import com.zhangws.elderhelper.database.AppInfo

class NormalAppAdapter(layoutResId: Int) : BaseQuickAdapter<AppInfo, BaseViewHolder>(layoutResId) {
    override fun convert(holder: BaseViewHolder, item: AppInfo) {
        holder.itemView.findViewById<ImageView>(R.id.icon).apply {
            setImageDrawable(item.drawable)
        }

        holder.itemView.findViewById<TextView>(R.id.tvName).apply {
            text=item.name
        }

        holder.itemView.findViewById<TextView>(R.id.tvPackage).apply {
            text=item.package_name+" "+item.appVersion
        }

        holder.itemView.findViewById<TextView>(R.id.tvAdd).apply {
            if (item.add==1){
                text="已添加到桌面"
                setBackgroundResource(R.drawable.bg_added)
            }else{
                text="添加"
                setBackgroundResource(R.drawable.bg_add)
            }
        }


    }
}