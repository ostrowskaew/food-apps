import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ShopService} from "../services/ShopService";
import {Restaurant} from "../models/restaurant";

@Component({
  selector: 'app-restaurant-list',
  templateUrl: './restaurant-list.component.html',
  styleUrls: ['./restaurant-list.component.css']
})
export class RestaurantListComponent implements OnInit {
  @Input()
  @Output() onRestaurantChosen: EventEmitter<Restaurant>;

  selectedRestaurant?: Restaurant
  restaurants : Restaurant[] = []
  constructor(private shopService: ShopService) {
    this.onRestaurantChosen = new EventEmitter<Restaurant>();
  }

  ngOnInit(): void {

    this.loadRestaurants()
  }

  onSelect(restaurant: Restaurant): void {
    this.selectedRestaurant = restaurant;
    this.onRestaurantChosen.emit(this.selectedRestaurant);
  }

  loadRestaurants() {
    this.shopService.getAllRestaurnats()
      .subscribe(
        restaurants => {
          this.restaurants = restaurants as any[];
        },
        (error) => console.log(error)
      );
  }

}
