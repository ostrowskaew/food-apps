import {Component, OnInit} from '@angular/core';
import {ProductOrders} from "../models/product-orders";
import {Subscription} from "rxjs/internal/Subscription";
import {ShopService} from "../services/ShopService";
import {ShippingDetails} from "../models/shipping-details";
import {Restaurant} from "../models/restaurant";

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  orders: ProductOrders;
  total: number;
  paid: boolean;
  sub: Subscription;
  shippingData: ShippingDetails;
  subShipping : Subscription;

  constructor(private shopService: ShopService) {
    this.orders = this.shopService.ProductOrders;
    this.shippingData = this.shopService.ShipmentDetails;
  }

  ngOnInit() {
    this.paid = false;
    this.sub = this.shopService.OrdersChanged.subscribe(() => {
      this.orders = this.shopService.ProductOrders;
    });

    this.loadTotal();
    this.loadShippingChange();
  }

  pay() {
    this.paid = true;
    this.shopService.saveOrder(this.orders, this.shippingData, 0).subscribe(
      response => {
        console.log(response)
        window.open(response.generatedLink, "_blank");
      },
      (error) => console.log(error)
    );
  }

  loadShippingChange() {
    this.subShipping = this.shopService.ShippingDetailsChanged.subscribe(() => {
      this.shippingData = this.shopService.ShippingDetails;
    })
  }

  loadTotal() {
    this.sub = this.shopService.TotalChanged.subscribe(() => {
      this.total = this.shopService.Total;
    });
  }

  payCash() {
    this.paid = true;
    this.shopService.saveOrder(this.orders, this.shippingData, 1).subscribe(
      response => {
        console.log(response)
        window.open(response.generatedLink, "_blank");
      },
      (error) => console.log(error)
    );
  }
}
