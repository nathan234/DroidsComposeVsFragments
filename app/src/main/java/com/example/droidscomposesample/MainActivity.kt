package com.example.droidscomposesample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidscomposesample.ui.fragment.MainFragment
import com.example.droidscomposesample.ui.theme.DroidsComposeSampleTheme
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            DroidsComposeSampleTheme {
//                // A surface container using the 'background' color from the theme
//                val appNavHostController = rememberNavController()
//                NavHost(
//                    appNavHostController,
//                    startDestination = "HOLIDAY_SCREEN",
//                ) {
//                    composable(route = "HOLIDAY_SCREEN") {
//                        HolidayScreen()
//                    }
//                }
//            }
//        }
//    }
//}

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment())
                .commitNow()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DroidsComposeSampleTheme {

    }
}