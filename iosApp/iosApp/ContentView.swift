import SwiftUI
import appDi

class VM: ObservableObject {
    private let processor = CommonInjector.init().getAlertProcessor()
    
    @Published var isShow = false
    
    init(){
        self.processor.onAlertShowRequest { (msg: String) in
            
            print("show")
            self.isShow = true
        }
    }
}

struct MessageProcessor: View {
    @ObservedObject var vm: VM
    
    var body: some View {
        AlertView(isShow: $vm.isShow)
    }
}

struct AlertView: View {
    @Binding var isShow: Bool
    
    var body: some View {
        Button("title" , action: {
            //self.show.toggle()
        }).alert(isPresented: $isShow) { () -> Alert in
            Alert(title: Text("text"))
        }
    }
}

struct ContentView: View {
    @ObservedObject var viewModel: TimerViewModel = TimerViewModel()
    let processor = CommonInjector.init().getAlertProcessor()
    @ObservedObject var vm = VM()
    
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
            MessageProcessor(vm: vm)
            
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
                    
                    print("call")
                    self.processor.showAlert(message: "stop")
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
