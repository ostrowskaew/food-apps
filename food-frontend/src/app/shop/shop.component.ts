import {Component, OnInit, ViewChild} from '@angular/core';
import {ProductsComponent} from "./products/products.component";
import {ShoppingCartComponent} from "./shopping-cart/shopping-cart.component";
import {OrdersComponent} from "./orders/orders.component";
import {DeliveryDetailsComponent} from "./delivery-details/delivery-details.component";

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {

  collapsed = true;
  orderFinished = false;
  orderDetailsFinished = false

  @ViewChild('productsC')
  productsC: ProductsComponent;

  @ViewChild('shoppingCartC')
  shoppingCartC: ShoppingCartComponent;

  @ViewChild('ordersC')
  ordersC: OrdersComponent;

  @ViewChild('deliveryDetailsC')
  deliveryDetailsC: DeliveryDetailsComponent;


  constructor() { }

  ngOnInit() {
  }

  toggleCollapsed(): void {
    this.collapsed = !this.collapsed;
  }

  finishOrder(orderFinished: boolean){
    this.orderFinished = orderFinished;
  }

  finishOrderDetails(orderDetailsFinished: boolean){
    this.orderDetailsFinished = orderDetailsFinished;
  }

  reset(){
    this.orderFinished = false;
    this.productsC.reset();
    this.shoppingCartC.reset();
    this.ordersC.paid = false;
  }

}
