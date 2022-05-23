import {Component, EventEmitter, HostListener, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ShopService} from "../services/ShopService";
import {ShippingDetails} from "../models/shipping-details";

@Component({
  selector: 'app-delivery-details',
  templateUrl: './delivery-details.component.html',
  styleUrls: ['./delivery-details.component.css']
})
export class DeliveryDetailsComponent implements OnInit {

  contactForm: FormGroup;
  disabledSubmitButton: boolean = true;
  shippingDetails: ShippingDetails;
  orderDetailsFinished = false;

  @Output() onDetailsFinished: EventEmitter<boolean>;

  @HostListener('input') oninput() {
    if (this.contactForm.valid) {
      this.disabledSubmitButton = false;
    }
  }

  constructor(private fb: FormBuilder, private shopService: ShopService) {
    this.orderDetailsFinished = false;
    this.onDetailsFinished = new EventEmitter<boolean>();
    this.contactForm = fb.group({
      'contactFormName': ['', Validators.required],
      'contactFormSurname': ['', Validators.required],
      'contactFormEmail': ['', Validators.compose([Validators.required, Validators.email])],
      'contactFormPhone': [''],
      'contactFormStreet': ['', Validators.required],
      'contactFormBuilding': ['', Validators.required],
      'contactFormPostCode': ['', Validators.compose([Validators.required, Validators.maxLength(6), Validators.minLength(6)])],
      'contactFormCity': ['', Validators.required],
      'contactFormCompany': [''],
      'contactFormApartment': ['']

    });
  }

  onSubmit() {
     this.shippingDetails = new ShippingDetails(
       this.contactForm.get('contactFormName')?.value,
       this.contactForm.get('contactFormSurname')?.value,
       this.contactForm.get('contactFormEmail')?.value,
       this.contactForm.get('contactFormPhone')?.value,
       this.contactForm.get('contactFormStreet')?.value,
       this.contactForm.get('contactFormBuilding')?.value,
       this.contactForm.get('contactFormPostCode')?.value,
       this.contactForm.get('contactFormCity')?.value,
       this.contactForm.get('contactFormApartment')?.value,
       this.contactForm.get('contactFormCompany')?.value
     )
    this.orderDetailsFinished= true;
    this.onDetailsFinished.emit(this.orderDetailsFinished);
    this.shopService.ShippingDataChanged = this.shippingDetails;
    console.log('shipping data changed in deliverydetails' + this.shippingDetails)
  }

  ngOnInit(): void {
  }

}
