export class Status {
  constructor(orderId: number, orderStatus: string) {
    this.orderId = orderId;
    this.orderStatus = orderStatus;
  }
  orderId: number;
  orderStatus: string;
}
