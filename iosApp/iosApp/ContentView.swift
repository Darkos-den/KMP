import SwiftUI
import appDi

class VM: ObservableObject {
    private var processor: appDi.AlertProcessor
    
    @Published var isShow = false
    
    init(injector: CommonInjector) {
        processor = injector.getAlertProcessor()
    }
    
    func run(){
        self.processor.onAlertShowRequest { (msg: String) in
            
            print("show")
            self.isShow = true
        }
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
    var processor: appDi.AlertProcessor
    var injector: CommonInjector
    @ObservedObject var vm: VM
    
    init(injector: CommonInjector) {
        self.injector = injector
        processor = injector.getAlertProcessor()
        vm = VM(injector: injector)
        vm.run()
    }
    
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
            AlertView(isShow: $vm.isShow)
            
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
