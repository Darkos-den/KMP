import SwiftUI
import appDi

struct ContentView: View {
    
    var body: some View {
        return NavigationView {
            VStack{
                SampleScreen()
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
       EmptyView()
    }
}
