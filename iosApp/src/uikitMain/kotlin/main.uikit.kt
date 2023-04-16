import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cmota.sample.MR
import com.cmota.sample.ui.main.MainScreen
import com.cmota.sample.ui.schedule.ScheduleContent
import com.cmota.sample.ui.schedule.ScheduleScreen
import com.cmota.sample.ui.speakers.SpeakersContent
import com.cmota.theme.BLUE
import com.cmota.theme.MultiplatformTheme
import com.cmota.theme.icBack
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.cinterop.autoreleasepool
import kotlinx.cinterop.cstr
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toCValues
import moe.tlaster.precompose.PreComposeApplication
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import platform.Foundation.NSLog
import platform.Foundation.NSStringFromClass
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationDelegateProtocol
import platform.UIKit.UIApplicationDelegateProtocolMeta
import platform.UIKit.UIApplicationMain
import platform.UIKit.UIResponder
import platform.UIKit.UIResponderMeta
import platform.UIKit.UIScreen
import platform.UIKit.UIWindow
import platform.posix.exit

fun main() {
    val args = emptyArray<String>()
    memScoped {
        val argc = args.size + 1
        val argv = (arrayOf("skikoApp") + args).map { it.cstr.ptr }.toCValues()
        autoreleasepool {
            UIApplicationMain(argc, argv, null, NSStringFromClass(SkikoAppDelegate))
        }
    }
}

class SkikoAppDelegate : UIResponder, UIApplicationDelegateProtocol {
    companion object : UIResponderMeta(), UIApplicationDelegateProtocolMeta

    @OverrideInit
    constructor() : super()

    private var _window: UIWindow? = null
    override fun window() = _window
    override fun setWindow(window: UIWindow?) {
        _window = window
    }

    override fun application(application: UIApplication, didFinishLaunchingWithOptions: Map<Any?, *>?): Boolean {
        window = UIWindow(frame = UIScreen.mainScreen.bounds)
        window!!.rootViewController = PreComposeApplication("Sample") {
            Surface(modifier = Modifier.fillMaxSize()) {
                CompositionLocalProvider {

                    MultiplatformTheme {
                        MainScreen(
                            onBackAction = { exit(0) }
                        )
                    }
                }
            }
        }
        window!!.makeKeyAndVisible()
        return true
    }
}