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
// you can treat it as a view model
final class PeopleInSpaceViewModel: ObservableObject {
   
    var cocktail = CocktailApiImpl()
    @Published var drinks = [Drink]()

    private let repository: PeopleInSpaceRepository

       init(repository: PeopleInSpaceRepository) {
           self.repository = repository
       }
       
       func startObservingPeopleUpdates() {
        repository.startObservingPeopleUpdates(success: { data in
            self.drinks = data.drinks
           })
       }
       
       func stopObservingPeopleUpdates() {
           repository.stopObservingPeopleUpdates()
       }
       
}
