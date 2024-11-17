import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        KmpIosStarter().start()
    }
	var body: some Scene {
		WindowGroup {
            NavigationMenuPage()
		}
	}
}
