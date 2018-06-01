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
import kotlinx.android.synthetic.main.homecard_item.view.*
import android.view.animation.AnimationUtils
import android.widget.RatingBar
import com.twisac.apps.adlibtone.dto.Artist


class HomeAdapter(internal var context: Context, private val mHeaders: MutableList<Artist>)//super(context, resource, objects);
    : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    var lastPosition = -1
    fun clear() {
        mHeaders.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var iv_thumbnail: ImageView = v.iv_thumbnail
        var tv_name: TextView = v.tv_name
        var tv_description : TextView = v.tv_description
        var rb_rating: RatingBar = v.rb_rating

        init {
            v.rl_foreground.setOnClickListener{  }
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val header = mHeaders[position]

        /**
         * With Reduce duplication*/
        with (holder) {
            tv_name.text = header.name
            tv_description.text = header.description
            rb_rating.rating= header.rating!!



            val res = context.resources.getIdentifier(header.image, "drawable", context.packageName)
            iv_thumbnail.setImageResource(res)
        }

       /**
        *  use when instead of if or switch*/
        //Animation
        when{
            position > this.lastPosition -> {
                val animation = AnimationUtils.loadAnimation(context,R.anim.up_from_bottom)
                holder.itemView.startAnimation(animation)
                this.lastPosition = position
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.homecard_item, parent, false)
       // parent.inflate(R.layout.headercard, parent, false)
        return ViewHolder(view)

    }

   override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }
    /**extension function
     * */
    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, parent: ViewGroup, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, parent, attachToRoot)
    }

    override fun getItemCount(): Int = mHeaders.size

}