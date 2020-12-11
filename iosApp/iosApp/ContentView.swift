import SwiftUI
import shared

struct ContentView: View {
    var component = TimerDI().getComponent()
    
    var body: some View {
        
        component.onEach
        
        Text("stub")
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
