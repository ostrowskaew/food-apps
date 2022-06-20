import {ProductOrders} from "./product-orders";
import {ShippingDetails} from "./shipping-details";

export class Wrapper {
  constructor(productOrders: ProductOrders, shippingDetails: ShippingDetails, userId: string, paymentMethod: number) {
    this.order = productOrders;
    this.shippingDetails = shippingDetails;
    this.userId = userId;
    this.paymentMethod = paymentMethod;
  }
  order: ProductOrders;
  shippingDetails: ShippingDetails;
  userId: string;
  paymentMethod: number;
}
