import SwiftUI
import appDi

struct ContentView: View {
    @ObservedObject var viewModel: TimerViewModel = TimerViewModel()
    
    var body: some View {
        let tmpState = viewModel.state
        let textValue: String = String(tmpState.value)
        var buttonValue: String

        if(tmpState.progress){
            buttonValue = "stop"
        }else {
            buttonValue = "start"
        }
        

        
        return VStack() {
            if(tmpState.progress){
                Text(textValue)
            }else {
                TextField("Enter username...", text: Binding<String>(get: { () -> String in
                    tmpState.str
                }, set: { (newValue: String) in
                    self.viewModel.component.onTextChanged(txt: newValue)
                }))
            }
        
            Button(buttonValue){
                if(tmpState.progress){
                  self.viewModel.component.onStopClick()
                }else{
                     self.viewModel.component.onStartClick()
                }
            }
            
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
