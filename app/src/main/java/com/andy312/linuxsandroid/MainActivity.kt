package com.andy312.linuxsandroid

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.andy312.linuxsandroid.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.topjohnwu.superuser.CallbackList
import com.topjohnwu.superuser.Shell
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    init{
        Shell.enableVerboseLogging = BuildConfig.DEBUG
        Shell.setDefaultBuilder(Shell.Builder.create()
            .setFlags(Shell.FLAG_REDIRECT_STDERR)
            .setTimeout(10)
        );
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        /*Shell.getShell { shell: Shell? ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }*/

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAnchorView(R.id.fab)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun requestRoot(view: View) {
        val textOut = findViewById<TextView>(R.id.logsee)
        var result: Shell.Result?
        result = Shell.cmd("su").exec()
        textOut.text = result.getOut().toString()
    }

    fun enterCommand(view: View) {
        val commandText = findViewById<EditText>(R.id.commandBox)
        val textOut = findViewById<TextView>(R.id.logsee)
        var result:String? = ""

        var callbackList: List<String?> = object : CallbackList<String?>() {
            override fun onAddElement(s: String?) {
                result += s + "\n"
                textOut.text = result
            }
        }

        Shell.cmd(commandText.text.toString())
            .to(callbackList)
            .submit()
    }
}