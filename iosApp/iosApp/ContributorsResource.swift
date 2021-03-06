//
//  ContributorsResource.swift
//  iosApp
//
//  Created by Jens Klingenberg on 04.03.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import Combine
import shared

final class PeopleInSpaceViewModel: ObservableObject {
   
    @Published var drinks = [Meal]()

    private let repository: MealDataSource

       init(repository: MealDataSource) {
           self.repository = repository
       }
       
       func startObservingPeopleUpdates() {
        repository.getMeals { cocktailResult,_ in
            self.drinks = cocktailResult!.meals
        }
        
       }
       
    
        func getPersonImage(personName: String) -> String {
            return repository.getIngredientImageUrl(name: personName)
        }
       
}
