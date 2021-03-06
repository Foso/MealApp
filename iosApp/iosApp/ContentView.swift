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

// navigation works once on simulators till xcode11.4
// https://stackoverflow.com/questions/59279176/navigationlink-works-only-for-once
struct ContentView: View {
    
    @StateObject var peopleInSpaceViewModel = PeopleInSpaceViewModel(repository:PeopleInSpaceRepository())


    var body: some View {
        TabView {
            PeopleListView(viewModel: peopleInSpaceViewModel)
                .tabItem {
                    Label("People", systemImage: "person")
                }
        }
    }
}

struct PeopleListView: View {
    
    @ObservedObject var viewModel : PeopleInSpaceViewModel


    var body: some View {
        NavigationView {
                   VStack {
                       
                       List(viewModel.drinks, id: \.strDrink) { person in
                           NavigationLink(destination: PersonDetailsView(viewModel: viewModel, person: person)) {
                               PersonView(viewModel: viewModel, person: person)
                           }
                       }
                       .navigationBarTitle(Text("People In Space"))
                       .onAppear {
                           viewModel.startObservingPeopleUpdates()
                       }.onDisappear {
                           viewModel.stopObservingPeopleUpdates()
                       }
                   }
               }
    }
}

struct PersonDetailsView: View {
    var viewModel: PeopleInSpaceViewModel
    var person: Drink
    
    var body: some View {
        ScrollView {
            VStack(alignment: .center, spacing: 32) {
                Text(person.strDrink).font(.title)
                
               // Text(viewModel.getPersonBio(personName: person.name)).font(.body)
                Spacer()
            }
            .padding()
        }
    }
}
struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
