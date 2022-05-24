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

  @ViewChild('productsC')
  productsC: ProductsComponent;

  @ViewChild('shoppingCartC')
  shoppingCartC: ShoppingCartComponent;

  @ViewChild('ordersC')
  ordersC: OrdersComponent;

  @ViewChild('deliveryDetailsC')
  deliveryDetailsC: DeliveryDetailsComponent;

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


  ngOnDestroy(){
    if(this.facebookSubscription)
      this.facebookSubscription.unsubscribe()
  }


  toggleCollapsed(): void {
    this.collapsed = !this.collapsed;
  }

  finishOrder(orderFinished: boolean) {
    this.orderFinished = orderFinished;
  }

  finishOrderDetails(orderDetailsFinished: boolean) {
    this.orderDetailsFinished = orderDetailsFinished;
  }

  loginWithFacebook(): void {
    this.socialAuthService.signIn(FacebookLoginProvider.PROVIDER_ID);
  }

  signOut(): void {
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
  }

}
