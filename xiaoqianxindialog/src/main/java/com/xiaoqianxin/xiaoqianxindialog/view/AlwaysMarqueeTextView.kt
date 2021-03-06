package com.xiaoqianxin.xiaoqianxindialog.view

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet

//文字一直滚动textView
class AlwaysMarqueeTextView : AppCompatTextView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    override fun isFocused(): Boolean {
        return true
    }
}