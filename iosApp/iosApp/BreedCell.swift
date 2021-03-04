//
//  BreedCell.swift
//  KaMPKitiOS
//
//  Created by Ben Whitley on 6/17/20.
//  Copyright © 2020 Touchlab. All rights reserved.
//

import UIKit
import shared

protocol BreedCellDelegate: class {

}

class BreedCell: UITableViewCell {
    
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var favoriteButton: UIButton!
    
    var breed: Drink?
    weak var delegate: BreedCellDelegate?
    
    func bind(_ breed: Drink) {
        self.breed = breed
        nameLabel.text = breed.strDrink
        
       
    }
    
    @IBAction func favoriteButtonPressed(_ sender: Any) {
        if let breed = breed {
            
            
        }
    }
}
