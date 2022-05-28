
export class Restaurant {
  id: number;
  restaurant: string;
  address: string;
  longitude: string;
  latitude: string;

  constructor(id: number, restaurant: string, address: string, longitude: string, latitude: string) {
    this.id = id;
    this.restaurant = restaurant;
    this.address = address;
    this.longitude = longitude;
    this.latitude = latitude;
  }
}
