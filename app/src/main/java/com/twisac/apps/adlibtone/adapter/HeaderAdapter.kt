package com.twisac.apps.adlibtone.adapter

import android.app.Activity
import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.twisac.apps.adlibtone.R
import com.twisac.apps.adlibtone.dto.Artist
import kotlinx.android.synthetic.main.headercard.view.*


class HeaderAdapter(internal var context: Context, private val mHeaders: MutableList<Artist>)//super(context, resource, objects);
    : RecyclerView.Adapter<HeaderAdapter.ViewHolder>() {
    fun clear() {
        mHeaders.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var headerImage: ImageView = v.header_image
        var txtTitle: TextView = v.txtTitle
        var txtContent : TextView = v.txtContent

        init {
            /** using lambada
             * onclick listener interface*/
            headerImage.setOnClickListener{  }
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val header = mHeaders[position]

        with (holder) {
            txtTitle.text = header.name
            txtContent.text = header.description
            val res = context.resources.getIdentifier(header.image, "drawable", context.packageName)
            headerImage.setImageResource(res)
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //   context =parent.getContext();
        val inflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.headercard, parent, false)
       // parent.inflate(R.layout.headercard, parent, false)
        return ViewHolder(view)

    }
    //extension function
    fun ViewGroup.inflate(@LayoutRes layoutRes: Int,parent: ViewGroup, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, parent, attachToRoot)
    }
    override fun getItemCount(): Int {
        return mHeaders.size
    }

}
