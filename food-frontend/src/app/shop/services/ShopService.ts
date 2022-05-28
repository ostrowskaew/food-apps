import { ProductOrder } from '../models/product-order';
import { ProductOrders } from '../models/product-orders';
import { Subject } from 'rxjs/internal/Subject';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {FormGroup} from "@angular/forms";
import {ShippingDetails} from "../models/shipping-details";
import {SocialAuthService, SocialUser} from "angularx-social-login";
import {Wrapper} from "../models/wrapper";

@Injectable()
export class ShopService {

  private staticUri = "http://localhost:8081";

  private productOrder: ProductOrder;
  private orders: ProductOrders = new ProductOrders();
  private shippingDetails: ShippingDetails;
  private socialUser: SocialUser;

  private productOrderSubject = new Subject<void>();
  private ordersSubject = new Subject<void>();
  private totalSubject = new Subject<void>();
  private shippingDetailsSubject = new Subject<void>();
  private socialUserSubject = new Subject<void>();

  private total: number;

  ProductOrderChanged = this.productOrderSubject.asObservable();
  OrdersChanged = this.ordersSubject.asObservable();
  TotalChanged = this.totalSubject.asObservable();
  ShippingDetailsChanged = this.shippingDetailsSubject.asObservable();
  socialUserChanged = this.socialUserSubject.asObservable();

  constructor(private _httpClient: HttpClient, private socialAuthService: SocialAuthService){}


  getAllProducts(){
    return this._httpClient.get(this.staticUri+'/api/products');
  }

  getProductsFromRestaurant(restaurantId: number){
    const params = new HttpParams().append('searchedRestaurantId', restaurantId);
    return this._httpClient.get(this.staticUri+'/api/product-by-restaurant-id', {params});
  }

  getAllRestaurnats(){
    return this._httpClient.get(this.staticUri+'/api/restaurants');
  }

  saveOrder(order: ProductOrders, shippingDetails: ShippingDetails): Observable<any>{
    console.log('id' + this.socialUser.id)
    return this._httpClient.post(this.staticUri+'/api/orders', new Wrapper(order, shippingDetails, this.socialUser.id) );
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

  loginUser(socialUser: SocialUser): Observable<Object> {
    this.socialUser = socialUser;
    this.socialUserSubject.next();
    return this._httpClient.post(this.staticUri+'/api/login', socialUser);
  }
}


