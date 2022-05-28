import {ProductOrders} from "./product-orders";
import {ShippingDetails} from "./shipping-details";

export class Wrapper {
  constructor(productOrders: ProductOrders, shippingDetails: ShippingDetails, userId: string) {
    this.order = productOrders;
    this.shippingDetails = shippingDetails;
    this.userId = userId;
  }
  order: ProductOrders;
  shippingDetails: ShippingDetails;
  userId: string;
}
