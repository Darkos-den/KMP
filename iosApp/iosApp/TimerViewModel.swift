//
//  TimerViewModel.swift
//  iosApp
//
//  Created by dev on 12/15/20.
//  Copyright © 2020 orgName. All rights reserved.
//

import Foundation
import appDi

class TimerViewModel: ObservableObject {
    
    let component = CommonInjector().timerDiFacade().getComponent()
    @Published var state: TimerState
    
    init() {
        state = component.createInitialState() as! TimerState
        
        component.applyStateListener { (state: CoreMVUState) in
            print("new state: \((state as! TimerState).value)")
            self.state = state as! TimerState
        }
        component.start()
    }
}
