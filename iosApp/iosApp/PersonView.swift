//
//  PersonView.swift
//  iosApp
//
//  Created by Jens Klingenberg on 04.03.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct PersonView : View {
    var viewModel: PeopleInSpaceViewModel

    var person: Drink
    
    var body: some View {
        Text(person.strDrink )
    }
}
