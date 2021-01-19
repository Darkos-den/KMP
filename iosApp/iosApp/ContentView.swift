import SwiftUI
import appDi

class ContentView {
    
    var component = CommonInjector.init().splashDiFacade().getComponent()
    
    func createView() -> VStack {
        EmptyView()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
       EmptyView()
    }
}
