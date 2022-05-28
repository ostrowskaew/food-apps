import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ShopComponent } from './shop/shop.component';
import { ProductsComponent } from './shop/products/products.component';
import { ShoppingCartComponent } from './shop/shopping-cart/shopping-cart.component';
import { OrdersComponent } from './shop/orders/orders.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {ShopService} from "./shop/services/ShopService";
import { DeliveryDetailsComponent } from './shop/delivery-details/delivery-details.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatButtonModule} from "@angular/material/button";
import {MatSelectModule} from "@angular/material/select";
import {MatInputModule} from "@angular/material/input";
import {MatRadioModule} from "@angular/material/radio";
import {MatCardModule} from "@angular/material/card";
import {LoginComponent} from "./login/login.component";
import {
  FacebookLoginProvider,
  SocialLoginModule,
  SocialAuthServiceConfig,
} from 'angularx-social-login';
import {AppRoutingModule} from "./app-routing.module";
import {ToastrModule} from "ngx-toastr";
import { RestaurantListComponent } from './shop/restaurant-list/restaurant-list.component';

@NgModule({
  declarations: [
    AppComponent,
    ShopComponent,
    OrdersComponent,
    ProductsComponent,
    ShoppingCartComponent,
    DeliveryDetailsComponent,
    LoginComponent,
    RestaurantListComponent
  ],
  imports: [
    SocialLoginModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatRadioModule,
    MatCardModule
  ],
  providers: [
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: FacebookLoginProvider.PROVIDER_ID,
            provider: new FacebookLoginProvider('1729059420773078'),
          },
        ],
      } as SocialAuthServiceConfig,
    },
    ShopService],
  bootstrap: [AppComponent]
})
export class AppModule { }
