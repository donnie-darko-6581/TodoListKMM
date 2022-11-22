import SwiftUI
import shared

@main
struct iOSApp: App {

    init() {
        startKoin()
    }

	var body: some Scene {
		WindowGroup {
			BreedListScreen()
		}
	}
}

func startKoin() {
    let doOnStartup = { NSLog("Starting IOS application for KMM, this will be posted from core mod DI") }

    
    let koinApplication = KoinIosKt.doInitKoinOnIos(startUpFunction: doOnStartup)
}
