package com.andy312.linuxsandroid

import android.os.Bundle
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment


class add_new_activity : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addSlide(
            AppIntroFragment.createInstance(
            title = "Welcome",
            description = getString(R.string.welcomeText)
        ))
    }
}