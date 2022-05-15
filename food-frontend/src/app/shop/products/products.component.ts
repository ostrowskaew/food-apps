import {Component, OnInit} from '@angular/core';
import {ProductOrder} from "../models/product-order";
import {ShopService} from "../services/ShopService";
import {Subscription} from "rxjs/internal/Subscription";
import {ProductOrders} from "../models/product-orders";
import {Product} from "../models/product";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  productOrders: ProductOrder[] = [];
  products: Product[] = [];
  selectedProductOrder: ProductOrder;
  private shoppingCartOrders: ProductOrders;
  sub: Subscription;
  productSelected: boolean = false;

  constructor(private shopService: ShopService) {
  }

  ngOnInit() {
    this.productOrders = [];
    this.loadProducts();
    this.loadOrders();
  }

  addToCart(order: ProductOrder) {
    this.shopService.SelectedProductOrder = order;
    this.selectedProductOrder = this.shopService.SelectedProductOrder;
    this.productSelected = true;
  }

  removeFromCart(productOrder: ProductOrder) {
    let index = this.getProductIndex(productOrder.product);
    if (index > -1) {
      this.shoppingCartOrders.productOrders.splice(
        this.getProductIndex(productOrder.product), 1);
    }
    this.shopService.ProductOrders = this.shoppingCartOrders;
    this.shoppingCartOrders = this.shopService.ProductOrders;
    this.productSelected = false;
  }

  getProductIndex(product: Product): number {
    return this.shopService.ProductOrders.productOrders.findIndex(
      value => value.product === product);
  }

  isProductSelected(product: Product): boolean {
    return this.getProductIndex(product) > -1;
  }

  loadProducts() {
    this.shopService.getAllProducts()
      .subscribe(
        products => {
          this.products = products as any[];
          this.products.forEach(product => {
            this.productOrders.push(new ProductOrder(product, 0));
          })
        },
        (error) => console.log(error)
      );
  }

  loadOrders() {
    this.sub = this.shopService.OrdersChanged.subscribe(() => {
      this.shoppingCartOrders = this.shopService.ProductOrders;
    });
  }

  reset() {
    this.productOrders = [];
    this.loadProducts();
    this.shopService.ProductOrders.productOrders = [];
    this.loadOrders();
    this.productSelected = false;
  }
}
