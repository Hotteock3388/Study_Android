package com.example.usagestatusAppUsageStatistics

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


class ListViewAdapter(context: Context, arrayList : ArrayList<UsageStats>) : BaseAdapter() {

    var listViewItemList = arrayList
    var context = context

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var convertView = inflater.inflate(R.layout.usage_row, parent, false)

        var appIconImageView = convertView?.findViewById<ImageView>(R.id.imageView_appIcon)
        var appNameTextView = convertView?.findViewById<TextView>(R.id.textview_PackageName)
        var totalUseTimeTextView = convertView?.findViewById<TextView>(R.id.textView_TotalUsedTime)

        //appNameTextView?.text = listViewItemList[position].appName
        appIconImageView?.setImageDrawable(listViewItemList[position].appIcon)
        totalUseTimeTextView?.text = listViewItemList[position].useTime.toString()



        val pm: PackageManager = context.packageManager
        val ai: ApplicationInfo?
        ai = try {
            pm.getApplicationInfo(listViewItemList[position].appName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            null
        }
        val applicationName = (if (ai != null) pm.getApplicationLabel(ai) else "(unknown)") as String
        appNameTextView?.text = applicationName

        return convertView
    }

    override fun getItem(position: Int): Any {
        return listViewItemList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listViewItemList.size
    }

}