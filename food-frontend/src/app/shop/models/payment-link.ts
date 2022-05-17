export class PaymentLink {
  generatedLink: string;
  paymentOrderId: string;

  constructor(generatedLink: string, paymentOrderId: string) {
    this.generatedLink = generatedLink;
    this.paymentOrderId = paymentOrderId;
  }
}
