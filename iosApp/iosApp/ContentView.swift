import SwiftUI
import appDi

class MessageProcessor: ObservableObject {
    var isShow = false
    private let processor = CommonInjector.init().getAlertProcessor()
    
    var message: String = ""
    
    init(){
        self.processor.onAlertShowRequest { (msg: String) in
            self.message = msg
            self.isShow = true
        }
    }
    
    func createView() -> AlertView {
        let isAlertShow = Binding<Bool>(get: { () -> Bool in
            self.isShow
        }, set: { (b: Bool) in
            self.isShow = b
        })
        
        let alertMessage = Binding<String>(get: { () -> String in
            self.message
        }) { (String) in }
        
        return AlertView(message: alertMessage, isShow: isAlertShow)
    }
}

struct AlertView: View {
    @Binding var message: String
    @Binding var isShow: Bool
    
    var body: some View {
        VStack(){EmptyView()}.alert(isPresented: $isShow) { () -> Alert in
            Alert(title: Text(message))
        }
    }
}

struct ContentView: View {
    @ObservedObject var viewModel: TimerViewModel = TimerViewModel()
    let processor = CommonInjector.init().getAlertProcessor()
    @ObservedObject var prc = MessageProcessor()
    
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
            prc.createView()
            
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
            
            
            
        }.onDisappear {
            self.viewModel.stop()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
       EmptyView()
    }
}
