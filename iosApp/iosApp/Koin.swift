import Foundation
import shared

private var _koin: Koin_coreKoin?
var koin: Koin_coreKoin {
    return _koin!
}

func startKoin() {
    let doOnStartup = { NSLog("Starting IOS application for KMM, this will be posted from core mod DI") }

    let koinApplication = KoinIos.initKoinOnIos(
            startUpFunction: doOnStartup
        )
        _koin = koinApplication.koin
}