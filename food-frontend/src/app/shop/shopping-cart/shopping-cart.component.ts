import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {ProductOrders} from "../models/product-orders";
import {ProductOrder} from "../models/product-order";
import {ShopService} from "../services/ShopService";
import {Subscription} from "rxjs/internal/Subscription";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit, OnDestroy {
  orderFinished: boolean;
  orders: ProductOrders;
  total: number;
  sub: Subscription;

  @Input()
  @Output() onOrderFinished: EventEmitter<boolean>;

  constructor(private shopService: ShopService,
              private toastr: ToastrService) {
    this.total = 0;
    this.orderFinished = false;
    this.onOrderFinished = new EventEmitter<boolean>();
  }

  ngOnInit() {
    this.orders = new ProductOrders();
    this.loadCart();
    this.loadTotal();
  }

  private calculateTotal(products: ProductOrder[]): number {
    let sum = 0;
    products.forEach(value => {
      sum += (value.product.price * value.quantity);
    });
    return sum;
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  finishOrder() {
    if (this.canActivate()){
      this.orderFinished = true;
      this.shopService.Total = this.total;
      this.onOrderFinished.emit(this.orderFinished);
    }
    else {
      this.toastr.error('Zaloguj sie', 'Oooops');
    }
  }

  loadTotal() {
    this.sub = this.shopService.OrdersChanged.subscribe(() => {
      this.total = this.calculateTotal(this.orders.productOrders);
    });
  }

  loadCart() {
    this.sub = this.shopService.ProductOrderChanged.subscribe(() => {
      let productOrder = this.shopService.SelectedProductOrder;
      if (productOrder) {
        this.orders.productOrders.push(new ProductOrder(
          productOrder.product, productOrder.quantity));
      }
      this.shopService.ProductOrders = this.orders;
      this.orders = this.shopService.ProductOrders;
      this.total = this.calculateTotal(this.orders.productOrders);
    });
  }

  reset() {
    this.orderFinished = false;
    this.orders = new ProductOrders();
    this.orders.productOrders = []
    this.loadTotal();
    this.total = 0;
  }

  canActivate() {
    if (localStorage.getItem("socialusers")) { return true; }
    return false;
  }
}
