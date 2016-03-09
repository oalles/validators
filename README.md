# Bean Validation / Validators

Own custom constraints tailored to our specific requirements.

####@Iban constraint
[International Bank Account Number (IBAN) validator](https://en.wikipedia.org/wiki/International_Bank_Account_Number) checks if the value of a string is valid IBAN number.

####@E164 constraint
Based on [libphonenumber](https://github.com/googlei18n/libphonenumber) checks if the value of a telephone number is in E164 format.

####@Cif and @Nif constraints
Based on valnif library from  [Agencia Tributaria Española](http://www.agenciatributaria.es/AEAT.desarrolladores/Desarrolladores/Desarrolladores.html). 
  
#### Requirements

Hibernate Validator 5.1.1.Final and newer is supported.

#### License

This project is licensed under MIT license.