//
//  ContributorsView.swift
//  SampleiOS
//
//  Created by Michał Laskowski on 24/03/2020.
//  Copyright © 2020 Michał Laskowski. All rights reserved.
//

import Foundation
import SwiftUI
import Combine
import shared

struct Contributor: Codable {
    let login: String
    let contributions: Int
}

protocol ContributorsProviding {
    func contributors(owner: String, repo: String) -> AnyPublisher<[Contributor], Error>
}

private class ContributorsProvider: ContributorsProviding {

    private let baseUrl = URL(string: UserDefaults.standard.string(forKey: "contributors_url") ?? "http://api.github.com")!

    func contributors(owner: String, repo: String) -> AnyPublisher<[Contributor], Error> {
        let url = baseUrl.appendingPathComponent("/repos/\(owner)/\(repo)/contributors")
        return URLSession.shared.dataTaskPublisher(for: url).tryMap { (data, _) -> [Contributor] in
            try JSONDecoder().decode([Contributor].self, from: data)
        }.eraseToAnyPublisher()
    }
    // not used, but kept if we need to remove Combine (for iOS < 13)
    func contributors(owner: String, repo: String,
                      callback: @escaping ([Contributor]) -> Void) {
        let url = baseUrl.appendingPathComponent("/repos/\(owner)/\(repo)/contributors")

        let request = URLSession.shared.dataTask(with: url) { (data, response, error) in
            if let error = error {
                assertionFailure(error.localizedDescription)
                callback([])
                return
            }

            guard let data = data else {
                assertionFailure("Missing data but no error")
                callback([])
                return
            }

            do {
                let decodedResponse = try JSONDecoder().decode([Contributor].self, from: data)
                callback(decodedResponse)
            } catch {
                assertionFailure("Failed to decode response: \(error.localizedDescription)")
                callback([])
            }
        }

        request.resume()
    }
}

// you can treat it as a view model
final class ContributorsResource: ObservableObject {
    @Published var contributors = "Loading..."
   
    var cocktail = CocktailApiImpl()
    @Published var people = [Drink]()

    private let contributorsProvider: ContributorsProviding = ContributorsProvider()

    private var cancellables = Set<AnyCancellable>()
    private let repository: PeopleInSpaceRepository

    func getContributors() {
        
        
        contributorsProvider.contributors(owner: "foso", repo: "Jetpack-Compose-Playground")
        .map { contributors in
            contributors.map {
                $0.login
        }.joined(separator: ", ")
        }.replaceError(with: "Error!")
        .receive(on: RunLoop.main)
        .sink(receiveValue: { [weak self] in
            self?.contributors = $0
        })
        .store(in: &cancellables)
    }
    
    func getDrinks() {
       
           contributorsProvider.contributors(owner: "foso", repo: "Jetpack-Compose-Playground")
           .map { contributors in
               contributors.map {
                   $0.login
           }.joined(separator: ", ")
           }.replaceError(with: "Error!")
           .receive(on: RunLoop.main)
           .sink(receiveValue: { [weak self] in
               self?.contributors = $0
           })
           .store(in: &cancellables)
       }
    
           
       init(repository: PeopleInSpaceRepository) {
           self.repository = repository
           
       }
       
       func startObservingPeopleUpdates() {
        repository.startObservingPeopleUpdates(success: { data in
            self.people = data.drinks
           })
       }
       
       func stopObservingPeopleUpdates() {
           repository.stopObservingPeopleUpdates()
       }
       
}

struct ContributorsView: View {

    @ObservedObject private var peopleInSpaceViewModel = ContributorsResource(repository: PeopleInSpaceRepository())

    var body: some View {
        VStack {

            
            List(peopleInSpaceViewModel.people, id: \.strDrink) { person in
                PersonView(person: person)
            }
            .onAppear {
                self.peopleInSpaceViewModel.startObservingPeopleUpdates()
            }.onDisappear {
                self.peopleInSpaceViewModel.stopObservingPeopleUpdates()
            }
        
        }
       

    }
}

struct ContributorsView_Previews: PreviewProvider {
    static var previews: some View {
        ContributorsView()
    }
}


struct PersonView : View {
    var person: Drink
    
    var body: some View {
        Text(person.strDrink + " (" + person.strDrink + ")")
    }
}

