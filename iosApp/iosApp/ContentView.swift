import SwiftUI
import appDi

class MessageProcessor: ObservableObject {
    var isShow = false
    private let processor = CommonInjector.init().getAlertProcessor()
    
    var message: String = ""
    
    init(){
        self.processor.showSimpleMessage = {(msg: String) in
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

struct CoreView: View {
    var body: some View {
        EmptyView()
    }
}

struct FirstScreen: View {
    var onClick: ()->()
    
    var body: some View {
        VStack{
            Text("First")
            Button("next"){
                self.onClick()
            }
        }.navigationBarBackButtonHidden(true)
    }
}

struct SecondScreen: View {
    var body: some View {
        Text("Second")
    }
}

struct ContentView: View {
    @State var currentDestination: String? = nil
    
    var body: some View {
        return NavigationView {
            VStack{
                NavigationLink(
                    destination: FirstScreen(
                        onClick: {
                            self.currentDestination = "second"
                        }
                    ),
                    tag: "first",
                    selection: $currentDestination
                ){ EmptyView() }
                
                NavigationLink(
                    destination: SecondScreen(),
                    tag: "second",
                    selection: $currentDestination
                ){ EmptyView() }
                
                Button("init"){
                    self.currentDestination = "first"
                }
            }
        }
    }
}

struct ContentView2: View {
    @ObservedObject var viewModel: TimerViewModel = TimerViewModel()
    @ObservedObject var processor = MessageProcessor()
    
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
            processor.createView()
            
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
