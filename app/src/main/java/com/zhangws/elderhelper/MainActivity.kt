package com.zhangws.elderhelper

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextClock
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.listener.OnItemDragListener
import com.zhangws.elderhelper.database.AppDao
import com.zhangws.elderhelper.database.AppInfo
import com.zhangws.elderhelper.database.DataBase
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: AppAdapter
    lateinit var appDao: AppDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        initListener()
    }

    private fun initListener() {
        findViewById<ImageView>(R.id.ivSetting).apply {
            setOnClickListener {
                startActivity(Intent(this@MainActivity, AppListActivity::class.java))
            }
        }
    }

    private fun initView() {

        findViewById<TextClock>(R.id.tvDate).apply {
            addTextChangedListener(object:TextWatcher{
                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        this@MainActivity.findViewById<TextView>(R.id.tvLunar).text=Lunar(Calendar.getInstance()).toString()
                }
            })
        }

        appDao = DataBase.getInstance().appDao()
        val recyclerView = findViewById<RecyclerView>(R.id.rvApp)
        adapter = AppAdapter(R.layout.item_app, mutableListOf()).apply {
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

        adapter.setOnItemClickListener { adapter, view, position ->
            try {
                var intent =
                    packageManager.getLaunchIntentForPackage((adapter.data[position] as AppInfo).package_name)
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "当前应用不存在，请到设置中移除", Toast.LENGTH_LONG).show()
            }
        }

        recyclerView.layoutManager = GridLayoutManager(this, 4)
        adapter.onAttachedToRecyclerView(recyclerView)
        adapter.setEmptyView(R.layout.layout_main_empty)
        adapter.emptyLayout?.findViewById<TextView>(R.id.ivToSetting)?.setOnClickListener {
            startActivity(Intent(this@MainActivity, AppListActivity::class.java))
        }

        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.bottom = 49
            }
        })

    }

    override fun onResume() {
        super.onResume()
        adapter.setNewInstance(getAppList(3))
    }
}