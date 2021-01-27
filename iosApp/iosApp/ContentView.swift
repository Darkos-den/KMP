import SwiftUI
import appDi

class SplashView {
    var component = CommonInjector.init().splashDiFacade().getComponent()
    
    init() {
        component.applyStateListener { (state: CoreMVUState) in
            //todo
        }
        component.start()
    }
    
    func createView() -> some View {
        return VStack {
            EmptyView()
        }
    }
}

class ContentView {
    
    func createView() -> some View {
        return NavigationView {
            EmptyView()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView().createView()
    }
}
