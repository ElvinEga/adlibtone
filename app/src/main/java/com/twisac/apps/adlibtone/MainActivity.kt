package com.twisac.apps.adlibtone

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.animation.AnimationUtils
import com.google.gson.Gson
import com.twisac.apps.adlibtone.adapter.HeaderAdapter
import com.twisac.apps.adlibtone.adapter.HomeAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import com.stone.vega.library.VegaLayoutManager
import com.twisac.apps.adlibtone.dto.Artist
import com.google.gson.reflect.TypeToken
import java.io.IOException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //top bar
        setupCollopsinBar()

        //recycler view
        setupRecyclerView()

    }

    private fun setupCollopsinBar() {
        //toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.title=""
        collapsing_toolbar.title = ""

        /** Setup AppBar
         *
         */
        appbar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            internal var isShow = true
            internal var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {

                    toolbar.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.colorToolBar))
                    tp_title.setText("AdLib Tone")
                    tp_title.startAnimation(1F, 0F)
                    isShow = true
                } else if (isShow) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.colorTransparent))
                    tp_title.setText("AdLib Tone")
                    tp_title.startAnimation(1F, 0F)
                    isShow = false
                }
            }
        })
        //set expand as default
        appbar.setExpanded(true)
    }

    private fun setupRecyclerView() {
        var artistList: MutableList<Artist> = ArrayList()
        var gson = Gson()
        val listType = object : TypeToken<List<Artist>>() {}.type
       artistList = gson.fromJson<MutableList<Artist>>(loadJSONFromAsset(), listType)

        val headerAdapter = HeaderAdapter(this, artistList)
        rv_top.setHasFixedSize(true)
        rv_top.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)

        val animationController = AnimationUtils.loadLayoutAnimation(this@MainActivity, R.anim.layout_slide_from_right)
        rv_top.layoutAnimation = animationController

        rv_top.adapter = headerAdapter
        headerAdapter.notifyDataSetChanged()
        rv_top.scheduleLayoutAnimation()

      /**
       *  Top recyclerviewTransition*/
        var ty = 0

        val task = object : TimerTask() {
            override fun run() {
                val items = headerAdapter.itemCount

                when { ty.compareTo(headerAdapter.itemCount) < 0 -> {
                    rv_top.smoothScrollToPosition(ty)
                    ty.inc()
                }
                    ty.equals(items) -> ty = 0
                }
            }
        }
        val timer = Timer()
        val delay: Long = 0
        val intevalPeriod = (1 * 4000).toLong()
        timer.scheduleAtFixedRate(task, delay, intevalPeriod)

        val homeAdapter = HomeAdapter(this, artistList)
        rv_home.setHasFixedSize(true)
        rv_home.layoutManager = VegaLayoutManager()

        val animationController2 = AnimationUtils.loadLayoutAnimation(this@MainActivity, R.anim.layout_slide_from_bottom)
        rv_home.layoutAnimation = animationController2

        rv_home.adapter = homeAdapter
        homeAdapter.notifyDataSetChanged()
        rv_home.scheduleLayoutAnimation()




  }


    private fun loadJSONFromAsset(): String? {
        return try {

            val `is` = assets.open("adlib.json")

            val size = `is`.available()

            val buffer = ByteArray(size)

            `is`.read(buffer)

            `is`.close()

            String(buffer,  charset("UTF-8"))

        } catch (ex: IOException) {ex.printStackTrace(); null
        }

    }
}