package com.ralf.vieweventtest1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        single_view.setOnClickListener {
            startActivity(Intent(this, SingleViewActivity::class.java))
        }

        single_view_group.setOnClickListener {
            startActivity(Intent(this, SingleViewGroupActivity::class.java))
        }

        viewgroup_view.setOnClickListener {
            startActivity(Intent(this, ViewGroupAndViewActivity::class.java))
        }

        viewgroup_viewgroup.setOnClickListener {
            startActivity(Intent(this, ViewGroupAndVGActivity::class.java))
        }
        viewgroup_viewgroup_view.setOnClickListener {
            startActivity(Intent(this, EventTransmitActivity::class.java))
        }
    }
}
