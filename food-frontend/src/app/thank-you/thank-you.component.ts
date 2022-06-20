import { Component, OnInit } from '@angular/core';
import {ProductOrder} from "../shop/models/product-order";
import {ShopService} from "../shop/services/ShopService";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-thank-you',
  templateUrl: './thank-you.component.html',
  styleUrls: ['./thank-you.component.css']
})
export class ThankYouComponent implements OnInit {
  collapsed = true;
  status: any;
  paymentStatus: any;
  orderId: any;
  type: any;

  constructor(private shopService: ShopService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.route.queryParams
      .subscribe(params => {
          this.orderId = params['OrderID'];
          this.type = params['type'];
        }
      );

    if (this.type != 1){
      this.refreshStatus();
    }
  }

  toggleCollapsed()
    :
    void {
    this.collapsed = !this.collapsed;
  }

  refreshStatus() {
    this.shopService.getPaymentStatus(this.orderId)
      .subscribe(
        response => {
          console.log(response)
          this.paymentStatus = response;
          this.saveStatus();
        },
        (error) => console.log(error)
      );
  }

  saveStatus(){
    this.shopService.saveStatus(this.orderId, this.paymentStatus).subscribe(
      response => {
        console.log(response)
      },
      (error) => console.log(error)
    );
  }

  goHome() {
    this.router.navigate(['/shop']);
  }
}
