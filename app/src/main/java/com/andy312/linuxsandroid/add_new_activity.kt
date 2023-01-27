package com.andy312.linuxsandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment
import com.github.appintro.AppIntroPageTransformerType
import com.github.appintro.SlidePolicy
import com.google.android.material.snackbar.Snackbar


class add_new_activity : AppIntro2() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isWizardMode = true
        setTransformer(AppIntroPageTransformerType.Zoom)
        setProgressIndicator()
        addSlide(
            AppIntroFragment.createInstance(
                title = "Welcome",
                description = getString(R.string.welcomeText)
            )
        )
        if (!add_new_script.requestRoot()) {
            addSlide(askRootInstance())
        }
        addSlide(
            AppIntroFragment.createInstance(
                title = "Distribution",
                description = "Enjoy!"
            )
        )
        addSlide(
            AppIntroFragment.createInstance(
                title = "Desktop Environment",
                description = "Enjoy!"
            )
        )
        addSlide(
            AppIntroFragment.createInstance(
                title = "Termux?",
                description = "Enjoy!"
            )
        )
        addSlide(
            AppIntroFragment.createInstance(
                title = "GPU Acceleration Method",
                description = "Enjoy!"
            )
        )
        addSlide(
            AppIntroFragment.createInstance(
                title = "Done",
                description = "Enjoy!"
            )
        )

    }

    class askRootInstance : Fragment(), SlidePolicy {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? = inflater.inflate(R.layout.ask_root_layout, container, false)

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val askRootButton: Button = view.findViewById(R.id.RequestRoot)
            askRootButton.setOnClickListener {add_new_script.requestRoot()}

            val moreRootInfo: TextView = view.findViewById(R.id.requestRootInfo)
            moreRootInfo.setOnClickListener {/*TODO*/}//說明Root的重要
        }

        override val isPolicyRespected: Boolean
            get() = add_new_script.requestRoot()

        override fun onUserIllegallyRequestedNextPage() {
            Snackbar.make(requireView() , "No root Access", Snackbar.LENGTH_SHORT).show()
        }
    }
    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show()
        // Decide what to do when the user clicks on "Done"
        finish()
    }
}