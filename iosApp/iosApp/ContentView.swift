//
//  ContentView.swift
//  SampleiOS
//
//  Created by mlaskowski on 08/02/2020.
//  Copyright © 2020 Michal Laskowski. All rights reserved.
//

import SwiftUI
import shared

enum ContentViewAction {
    case goToList
    case goToNetwork
}


struct ContentView: View {
    
    @StateObject var peopleInSpaceViewModel = PeopleInSpaceViewModel(repository:MealRepository())


    var body: some View {
        
            PeopleListView(viewModel: peopleInSpaceViewModel)
                .tabItem {
                    Label("People", systemImage: "person")
                }
        
    }
}

struct PeopleListView: View {
    
    @ObservedObject var viewModel : PeopleInSpaceViewModel

    var body: some View {
        NavigationView {
                   VStack {
                       
                       List(viewModel.drinks, id: \.strMeal) { person in
                           NavigationLink(destination: MealDetailsView(viewModel: viewModel, meal: person)) {
                               PersonView(viewModel: viewModel, meal: person)
                           }
                       }
                       .navigationBarTitle(Text("Meal Catalog"))
                       .onAppear {
                           viewModel.startObservingPeopleUpdates()
                       }
                   }
               }
    }
}

struct MealDetailsView: View {
    var viewModel: PeopleInSpaceViewModel
    var meal: Meal
    
    var body: some View {
        ScrollView {
            VStack() {
                Text(meal.strMeal).font(.title)
                ImageView(withURL: meal.strMealThumb, width: 240, height: 240)

                
                Text("Ingredients")
                HStack{
                    
                    ForEach(meal.getIngredients(), id: \.self) { ingredientName in
                        ImageView(withURL: viewModel.getPersonImage(personName: ingredientName), width: 50, height: 50)
                    }
                }
                
                Text("Category: "+meal.strCategory)

                Text(meal.strInstructions ?? "").fixedSize().lineLimit(nil)
                   
                Spacer()
            }
            
        }
    }
}
struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
