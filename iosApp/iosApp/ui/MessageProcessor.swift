//
//  MessageProcessor.swift
//  iosApp
//
//  Created by dev on 1/14/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI

class MessageProcessor: ObservableObject {
    var isShow = false
//    private let processor = CommonInjector.init().getAlertProcessor()
    
    var message: String = ""
    
    init(){
//        self.processor.showSimpleMessage = {(msg: String) in
//            self.message = msg
//            self.isShow = true
//        }
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
