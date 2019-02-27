package com.waltervrjunior.organizzi.vc

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.waltervrjunior.organizzi.R
import com.waltervrjunior.organizzi.service.PurchaseService
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(main_activity_bottom_bat)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        main_activity_fab.onClick {
            PurchaseService.sendPurchase(
                onSuccess = { result ->
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(result.first)
                    startActivity(intent)
                },
                onError = {
                    toast("ERROUUU")
                }
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_drawer, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        Log.d("MENUCLICK", item?.itemId.toString())
        return super.onOptionsItemSelected(item)
    }
}
