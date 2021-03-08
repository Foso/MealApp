

import SwiftUI
import shared


struct ContentView: View {
    
    @StateObject var mealViewModel = MealViewModel(repository:MealRepository())
    
    
    var body: some View {
        MealListView(viewModel: mealViewModel)
    }
}

struct MealListView: View {
    
    @ObservedObject var viewModel : MealViewModel
    @State private var fullText: String = "Search"
    var body: some View {
        NavigationView {
            
            VStack {
                ScrollView(.horizontal, showsIndicators: false){
                    HStack{
                        ForEach(viewModel.categories, id: \.strCategoryThumb) { ingredientName in
                            ImageView(withURL: ingredientName.strCategoryThumb, width: 50, height: 50)
                        }
                    }.onAppear {
                        
                        viewModel.loadCategories()
                    }
                }
                
                TextEditor(text: $fullText)
                    .onTapGesture {
                        fullText=""
                    }
                    .onChange(of: fullText) { value in
                        if(!fullText.isEmpty){
                            viewModel.searchMealsByName(name: fullText)
                        }else{
                            viewModel.startObservingPeopleUpdates()
                        }
                    }.frame(height:50)
                
                List(viewModel.meals, id: \.strMeal) { meal in
                    NavigationLink(destination: MealDetailsView(viewModel: viewModel, meal: meal)) {
                        MealView(viewModel: viewModel, meal: meal)
                    }
                }
                
                
                .navigationBarTitle(Text("Meal App"))
                .onAppear {
                    viewModel.startObservingPeopleUpdates()
                }
            }
        }
    }
}

struct MealDetailsView: View {
    var viewModel: MealViewModel
    var meal: Meal
    
    var body: some View {
        ScrollView {
            VStack() {
                Text(meal.strMeal).font(.title)
                ImageView(withURL: meal.strMealThumb, width: 240, height: 240)
                
                
                Text("Ingredients")
                
                
                ForEach(meal.ingredients, id: \.self) { ingredient in
                    HStack{
                        CheckboxField(
                            id: "test",
                            label: "",
                            size: 14,
                            textSize: 14,
                            callback: checkboxSelected
                        )
                        Text(ingredient.measure)
                        
                        Text(ingredient.name)
                        
                        ImageView(withURL: viewModel.getIngredientImageUrl(ingredientName: ingredient.name), width: 50, height: 50)
                    }
                    
                }
                
                Text("Category: "+meal.strCategory)
                
                Text(meal.strInstructions ).padding(.horizontal,20)
                
            }
            
        }
    }
    
    func checkboxSelected(id: String, isMarked: Bool) {
        print("\(id) is marked: \(isMarked)")
    }
}
struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
