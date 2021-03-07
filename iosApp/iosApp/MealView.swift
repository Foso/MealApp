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

struct MealView : View {
    var viewModel: MealViewModel
    
    var meal: Meal
    
    var body: some View {
        
        
        HStack {
            ImageView(withURL: meal.strMealThumb, width: 64, height: 64)
            VStack(alignment: .leading) {
                Text(meal.strMeal )
            }
        }
    }
}
