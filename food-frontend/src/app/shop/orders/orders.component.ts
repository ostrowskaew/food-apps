import {Component, OnInit} from '@angular/core';
import {ProductOrders} from "../models/product-orders";
import {Subscription} from "rxjs/internal/Subscription";
import {ShopService} from "../services/ShopService";

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

  constructor(private shopService: ShopService) {
    this.orders = this.shopService.ProductOrders;
  }

  ngOnInit() {
    this.paid = false;
    this.sub = this.shopService.OrdersChanged.subscribe(() => {
      this.orders = this.shopService.ProductOrders;
    });
    this.loadTotal();
  }

  pay() {
    this.paid = true;
    this.shopService.saveOrder(this.orders).subscribe();
  }

  loadTotal() {
    this.sub = this.shopService.TotalChanged.subscribe(() => {
      this.total = this.shopService.Total;
    });
  }
}
