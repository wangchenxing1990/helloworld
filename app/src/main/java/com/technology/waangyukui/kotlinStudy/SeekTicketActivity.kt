package com.technology.waangyukui.kotlinStudy

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.technology.waangyukui.database.MyDataBaseHelper
import com.technology.waangyukui.kotlinStudy.fragment.ChoicenessFragment
import com.technology.waangyukui.kotlinStudy.fragment.MineFragment
import com.technology.waangyukui.kotlinStudy.fragment.SeekTicketFragment
import com.technology.waangyukui.mycyclerapp.R

/**
 * Created by lenvo on 2018/6/12.
 */
class SeekTicketActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    var fragments: MutableList<Fragment>? = null
    val FRAGMENT_CHOICES:String?="fragmentChoices"
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var itemId = item.itemId
        when (itemId) {
            R.id.navigation_home -> {
                switchFragment(0)
                return true
            }
            R.id.navigation_dashboard -> {
                switchFragment(1)
                return true
            }
            R.id.navigation_notifications -> {
                switchFragment(2)
                return true
            }
        }
        return false
    }

    var currentIndex: Int? =0
    private fun switchFragment(targetIndex: Int) {
        var targetFragment: Fragment = fragments!![targetIndex]
        var currentFragment: Fragment = fragments!![currentIndex!!]
        var fragmentTransaction : FragmentTransaction= supportFragmentManager.beginTransaction()
        if (targetFragment.isAdded) {
            fragmentTransaction .hide(currentFragment).show(targetFragment)
        } else {
            fragmentTransaction.add(R.id.frame_content, targetFragment).hide(currentFragment).show(targetFragment)
        }
        fragmentTransaction.commit()
        currentIndex = targetIndex
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seek_ticket)
        MyDataBaseHelper(applicationContext)
        initFragment()
        var navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(this)
       var fragment=supportFragmentManager.findFragmentByTag(FRAGMENT_CHOICES)
        if (fragment==null){
            supportFragmentManager.beginTransaction().add(R.id.frame_content,fragments!![0],FRAGMENT_CHOICES).show(fragments!![0]).commit()
        }
    }

    private fun initFragment() {
        fragments = ArrayList<Fragment>()
        (fragments as ArrayList<Fragment>).add(ChoicenessFragment())
        (fragments as ArrayList<Fragment>).add(SeekTicketFragment())
        (fragments as ArrayList<Fragment>).add(MineFragment())
    }
}
