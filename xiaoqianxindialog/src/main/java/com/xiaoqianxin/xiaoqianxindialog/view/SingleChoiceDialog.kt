package com.xiaoqianxin.xiaoqianxindialog.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.xiaoqianxin.xiaoqianxindialog.R
import com.xiaoqianxin.xiaoqianxindialog.adapter.SingleChoiceAdapter
import com.xiaoqianxin.xiaoqianxindialog.base.BaseDialog
import com.xiaoqianxin.xiaoqianxindialog.domain.SingleChoiceItem
import com.xiaoqianxin.xiaoqianxindialog.listener.DialogListener
import com.xiaoqianxin.xiaoqianxindialog.listener.SingleChoiceListener
import kotlinx.android.synthetic.main.dialog_siglechoice.*
import java.util.*

/**
 * @author xiaoqianxin
 * @date 2018/2/25
 */

class SingleChoiceDialog(context: Context, themeResId: Int) : BaseDialog(context, themeResId) {

    private var mListener: SingleChoiceListener? = null
    private lateinit var list: ArrayList<SingleChoiceItem>
    private lateinit var adapter: SingleChoiceAdapter

    override fun init() {
        setCancelable(false)
        setContentView(View.inflate(mContext, R.layout.dialog_siglechoice, null))
        val linearLayoutManager = LinearLayoutManager(mContext.applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        mRecyclerView.layoutManager = linearLayoutManager
        list = ArrayList()
        adapter = SingleChoiceAdapter(R.layout.md_simplelist_item, list)
        mRecyclerView.adapter = adapter
        adapter.setOnItemClickListener { adapter, _, position ->
            for (i in list) {
                i.isCheck = false
            }
            list[position].isCheck = true
            adapter.notifyDataSetChanged()
        }
        mTvCancel.setOnClickListener { dismiss() }
        mTvChoice.setOnClickListener {
            for (i in list.indices) {
                if (list[i].isCheck) {
                    mListener?.onItemSelect(i)
                    break
                }
            }
            dismiss()
        }
    }

    override fun setContent(content: Any): BaseDialog {
        if (content is String) {
            mTvTitle.text = content
        }
        if (content is Boolean) {
            if (content) {
                mTvCancel.visibility = View.VISIBLE
            } else {
                mTvCancel.visibility = View.GONE
            }
        }
        if (content is Array<*>) {
            for (i in content.indices) {
                val item = SingleChoiceItem()
                item.name = content[i] as String
                item.isCheck = i == 0
                list.add(item)
            }
            adapter.notifyDataSetChanged()
        }
        return this
    }

    override fun setListener(listener: DialogListener): BaseDialog {
        mListener = listener as SingleChoiceListener
        return this
    }
}
