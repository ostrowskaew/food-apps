import { ProductOrder } from '../models/product-order';
import { ProductOrders } from '../models/product-orders';
import { Subject } from 'rxjs/internal/Subject';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {PaymentLink} from "../models/payment-link";

@Injectable()
export class ShopService {

  private staticUri = "http://localhost:8081";

  private productOrder: ProductOrder;
  private orders: ProductOrders = new ProductOrders();

  private productOrderSubject = new Subject<void>();
  private ordersSubject = new Subject<void>();
  private totalSubject = new Subject<void>();

  private total: number;

  ProductOrderChanged = this.productOrderSubject.asObservable();
  OrdersChanged = this.ordersSubject.asObservable();
  TotalChanged = this.totalSubject.asObservable();

  constructor(private _httpClient: HttpClient){}

  getAllProducts(){
    return this._httpClient.get(this.staticUri+'/api/products');
  }

  saveOrder(order: ProductOrders): Observable<any>{
    return this._httpClient.post(this.staticUri+'/api/orders', order);
  }

  set SelectedProductOrder(value: ProductOrder){
    this.productOrder = value;
    this.productOrderSubject.next();
  }


  get SelectedProductOrder(){
    return this.productOrder;
  }


  set ProductOrders(value: ProductOrders){
    this.orders = value;
    this.ordersSubject.next();
  }

  get ProductOrders(){
    return this.orders;
  }

  get Total(){
    return this.total;
  }

  set Total(value: number){
    this.total = value;
    this.totalSubject.next();
  }

}
