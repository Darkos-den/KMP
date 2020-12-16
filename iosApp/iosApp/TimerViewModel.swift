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
    
    let component = CommonInjector().getComponent()
    @Published var state: ApiTimerState
    
    init() {
        state = component.createInitialState() as! ApiTimerState
        
        component.applyStateListener { (state: CoreMVUState) in
            print("new state: \((state as! ApiTimerState).value)")
            self.state = state as! ApiTimerState
        }
        component.start()
    }
}
