package Contracts;

public class Address {
	public String Street1;
	public String Street2;
	public String City;
	public String StreetNo;
	public String PostalCode;
	public String Country;

	public Address(String street1, String street2, String city, String streetNo, String postalCode, String country) {
		Street1 = street1;
		Street2 = street2;
		City = city;
		StreetNo = streetNo;
		PostalCode = postalCode;
		Country = country;
	}
}
