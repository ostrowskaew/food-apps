import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ShopComponent } from './shop/shop.component';
import { ProductsComponent } from './shop/products/products.component';
import { ShoppingCartComponent } from './shop/shopping-cart/shopping-cart.component';
import { OrdersComponent } from './shop/orders/orders.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {ShopService} from "./shop/services/ShopService";

@NgModule({
  declarations: [
    AppComponent,
    ShopComponent,
    OrdersComponent,
    ProductsComponent,
    ShoppingCartComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [ShopService],
  bootstrap: [AppComponent]
})
export class AppModule { }
