export class ShippingDetails {
  firstName: string;
  lastName: string;
  email: string;
  company: string;
  street: string;
  building: string;
  apartment: string;
  phone: string;
  city: string;
  postalCode: string;

  constructor(firstName: string, lastName: string, email: string, company: string, street: string, building: string, apartment: string, phone: string, city: string, postalCode: string) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.company = company;
    this.street = street;
    this.building = building;
    this.apartment = apartment;
    this.phone = phone;
    this.city = city;
    this.postalCode = postalCode;
  }

}
