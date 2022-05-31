import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {ProductsComponent} from "./products/products.component";
import {ShoppingCartComponent} from "./shopping-cart/shopping-cart.component";
import {OrdersComponent} from "./orders/orders.component";
import {DeliveryDetailsComponent} from "./delivery-details/delivery-details.component";
import {
  SocialAuthService,
  FacebookLoginProvider,
  SocialUser,
} from 'angularx-social-login';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";
import {ShopService} from "./services/ShopService";
import {Restaurant} from "./models/restaurant";
import {RestaurantListComponent} from "./restaurant-list/restaurant-list.component";
import {Loader} from "@googlemaps/js-api-loader";

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit, OnDestroy {

  collapsed = true;
  orderFinished = false;
  orderDetailsFinished = false
  socialUser!: SocialUser;
  isLoggedin?: boolean = false;
  facebookSubscription: Subscription;
  selectedRestaurant: Restaurant;
  isRestaurantSelected: boolean = false;

  @ViewChild('productsC')
  productsC: ProductsComponent;

  @ViewChild('shoppingCartC')
  shoppingCartC: ShoppingCartComponent;

  @ViewChild('restaurantListC')
  restaurantListC: RestaurantListComponent;

  @ViewChild('ordersC')
  ordersC: OrdersComponent;

  @ViewChild('deliveryDetailsC')
  deliveryDetailsC: DeliveryDetailsComponent;

  title = 'google-maps';

  private map: google.maps.Map;
  private geocoder: google.maps.Geocoder;
  private location = {lat: 51.111156258906945, lng: 17.059641231497707};
  private restaurants: Restaurant[] = [];

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private socialAuthService: SocialAuthService,
    private shopService: ShopService
  ) {
    if (localStorage.getItem('socialusers'))
      this.isLoggedin = true;
    console.log(this.isLoggedin);
  }

  ngOnInit() {

    this.facebookSubscription = this.socialAuthService.authState.subscribe((user) => {
      if (user) {
        console.log(user);
        this.socialUser = user;
        this.isLoggedin = user != null;
        this.saveData();
      }
    });

    this.loadRestaurants()
    this.initMap();
  }

  initMap() {
    let loader = new Loader({
      apiKey: 'APIKEYGOOGLEs'
    })
    loader.load().then(() => {

      this.map = new google.maps.Map(document.getElementById("map")!,
        {
          center: this.location,
          zoom: 15
        }
      )
      this.geocoder = new google.maps.Geocoder();

      this.codeAddress(this.geocoder, this.map);
    });
  }

  codeAddress(geocoder: google.maps.Geocoder, map: google.maps.Map) {
    this.restaurants.forEach((restaurant) => {
      geocoder.geocode({'address': restaurant.address}, (results, status) => {
        {
          console.log(results);
          if (results != null) {
            var latLng = {lat: results[0].geometry.location.lat()!, lng: results[0].geometry.location.lng()};
            console.log(latLng);
            if (status == 'OK') {
              var marker = new google.maps.Marker({
                position: latLng,
                map: map
              });

              marker.addListener("click", () => {
                map.setZoom(20);
                map.setCenter(marker.getPosition() as google.maps.LatLng);
                this.restaurantChosen(restaurant);
              });
            }
          } else {
            alert('Geocode was not successful for the following reason: ' + status);
          }
        }
      })
    });
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

  saveData() {
    localStorage.setItem('socialusers', JSON.stringify(this.socialUser));
    localStorage.setItem('authToken', JSON.stringify(this.socialUser.authToken));
    this.shopService.loginUser(this.socialUser).subscribe(
      response => {
        console.log(response)
      },
      (error) => console.log(error)
    );
  }


  ngOnDestroy() {
    if (this.facebookSubscription)
      this.facebookSubscription.unsubscribe()
  }


  toggleCollapsed()
    :
    void {
    this.collapsed = !this.collapsed;
  }

  finishOrder(orderFinished
                :
                boolean
  ) {
    this.orderFinished = orderFinished;
  }

  finishOrderDetails(orderDetailsFinished
                       :
                       boolean
  ) {
    this.orderDetailsFinished = orderDetailsFinished;
  }

  loginWithFacebook()
    :
    void {
    this.socialAuthService.signIn(FacebookLoginProvider.PROVIDER_ID);
  }

  restaurantChosen(restaurant
                     :
                     Restaurant
  ) {
    this.selectedRestaurant = restaurant;
    this.isRestaurantSelected = true;
  }

  signOut()
    :
    void {
    localStorage.removeItem('socialusers');
    localStorage.removeItem('authToken');
    this.isLoggedin = false;
    this.socialAuthService.signOut();
    this.router.navigate(['']);
  }

  reset() {
    this.orderFinished = false;
    this.productsC.reset();
    this.shoppingCartC.reset();
    this.ordersC.paid = false;
    this.isRestaurantSelected = false;
  }

}
