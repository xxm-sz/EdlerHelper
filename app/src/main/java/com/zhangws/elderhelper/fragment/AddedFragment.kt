package com.zhangws.elderhelper.fragment

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemDragListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhangws.elderhelper.R
import com.zhangws.elderhelper.adapter.AddedAdapter
import com.zhangws.elderhelper.database.AppDao
import com.zhangws.elderhelper.database.AppInfo
import com.zhangws.elderhelper.database.DataBase


class AddedFragment :AppBaseFragment(){

    override val type: Int=3

    override val layoutIds: Int=R.layout.fragment_app

    lateinit var appDao: AppDao


    override fun createAdapter(): BaseQuickAdapter<AppInfo, BaseViewHolder> {
        appDao = DataBase.getInstance().appDao()

        return AddedAdapter(R.layout.item_app_added).apply {
           draggableModule.isDragEnabled=true

           draggableModule.setOnItemDragListener(object:OnItemDragListener{
               override fun onItemDragMoving(
                   source: RecyclerView.ViewHolder?,
                   from: Int,
                   target: RecyclerView.ViewHolder?,
                   to: Int
               ) {

               }

               override fun onItemDragStart(viewHolder: RecyclerView.ViewHolder?, pos: Int) {

               }

               override fun onItemDragEnd(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
                 data.forEachIndexed() {index,app->
                     app.sort=index
                 }
                   appDao.update(data)
               }
           })
       }
    }



}