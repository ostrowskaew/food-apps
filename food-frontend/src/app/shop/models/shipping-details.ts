export class ShippingDetails {
  name: string;
  surname: string;
  email: string;
  phone: string;
  street: string;
  building: string;
  apartment?: string;
  company?: string;
  postCode: string;
  city: string;

  constructor(name: string, surname: string, email: string, phone: string, street: string, building: string,
               postCode: string, city: string, apartment?: string, company?: string) {
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.phone = phone;
    this.street = street;
    this.building = building;
    this.apartment = apartment;
    this.company = company;
    this.postCode = postCode;
    this.city = city;
  }

}
