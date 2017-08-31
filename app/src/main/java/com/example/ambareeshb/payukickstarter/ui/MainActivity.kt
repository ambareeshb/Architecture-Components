package com.example.ambareeshb.payukickstarter.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ambareeshb.payukickstarter.App
import com.example.ambareeshb.payukickstarter.DI.ActivityComponent
import com.example.ambareeshb.payukickstarter.DI.DaggerActivityComponent
import com.example.ambareeshb.payukickstarter.DI.modules.ActivityModule
import com.example.ambareeshb.payukickstarter.R
import com.example.ambareeshb.payukickstarter.helpers.FragmentUtils
import pub.devrel.easypermissions.EasyPermissions

class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {
    private lateinit var  activityComponent: ActivityComponent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildActivityComponent()
        setContentView(R.layout.activity_main)
        loadProjectListFragment()
        //scheduleJob(getApplication(),getProjectDataJobInfo(getApplication()));
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    private fun buildActivityComponent() {
        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(App.applicationComponent)
                .activityModule(ActivityModule(this))
                .build()
    }

    private fun loadProjectListFragment() {
        FragmentUtils(supportFragmentManager)
                .add(R.id.fragment_container, ProjectList())
                .setTransition(R.anim.enter_from_right, R.anim.exit_to_right)
                .commit()
    }


    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {

    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {

    }
}
