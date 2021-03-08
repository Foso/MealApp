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

final class MealViewModel: ObservableObject {
    
    @Published var meals = [Meal]()
    @Published var categories = [shared.Category]()
    
    
    private let repository: MealDataSource
    
    init(repository: MealDataSource) {
        self.repository = repository
    }
    
    func startObservingPeopleUpdates() {
        repository.getMeals { cocktailResult,_ in
            self.meals = cocktailResult ?? [Meal]()
        }
    }
    
    func loadCategories() {
        repository.getCategories{ categories,_ in
            self.categories = categories!
        }
    }
    
    func searchMealsByName(name:String) {
        repository.getMealsByName(categoryName: name){ categories,_ in
            self.meals = categories  ??  [Meal]()
        }
    }
    
    func getIngredientImageUrl(ingredientName: String) -> String {
        return repository.getIngredientImageUrl(name: ingredientName)
    }
    
}
