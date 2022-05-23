import { ProductOrder } from '../models/product-order';
import { ProductOrders } from '../models/product-orders';
import { Subject } from 'rxjs/internal/Subject';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {FormGroup} from "@angular/forms";
import {ShippingDetails} from "../models/shipping-details";

@Injectable()
export class ShopService {

  private staticUri = "http://localhost:8081";

  private productOrder: ProductOrder;
  private orders: ProductOrders = new ProductOrders();
  private shippingDetails: ShippingDetails;

  private productOrderSubject = new Subject<void>();
  private ordersSubject = new Subject<void>();
  private totalSubject = new Subject<void>();
  private shippingDetailsSubject = new Subject<void>();

  private total: number;

  ProductOrderChanged = this.productOrderSubject.asObservable();
  OrdersChanged = this.ordersSubject.asObservable();
  TotalChanged = this.totalSubject.asObservable();
  ShippingDetailsChanged = this.shippingDetailsSubject.asObservable();

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

  set ShippingDataChanged(value: ShippingDetails) {
    this.shippingDetails = value;
    this.shippingDetailsSubject.next();
    console.log('shipping data changed in service' + this.shippingDetails)

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

  set ShipmentDetails(shipmentDetails: ShippingDetails) {
    this.shippingDetails = shipmentDetails;
  }

  get ShippingDetails() {
    return this.shippingDetails;
  }

  saveOrderDetails(shippingDetails: FormGroup): Observable<Object> {
    return this._httpClient.post(this.staticUri+'/api/shipment-details', shippingDetails);

  }



}
