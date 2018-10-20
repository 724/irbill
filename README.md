[ ![Download](https://api.bintray.com/packages/farhad/maven/irbill/images/download.svg) ](https://bintray.com/farhad/maven/irbill/_latestVersion)

```
repositories {
	jcenter()
}
	
implementation 'io.github.the724:irbill:[latest-version]'
```
---

If you want to validate and parse a barcode representing a bill, use the `parseBarcode()` method. If the barcode contains a valid bill, the `Bill` object contains information about the bill.
```
Bill bill = IrBill.parseBarcode("the-string-data-of-barcode") ;
```

If you want to validate and parse `billId` and `paymentId`, also known as شناسه قبض  and  شناسه پرداخت, you can alternatively use the `parseBillData` method :

```
Bill bill = IrBill.parseBillData("billId","paymentId");
```

There are also two public utility methods, `validateBillId` and `validatePaymentId`, which can come handy for further debugging.

```
if(IrBill.validateBillId("billId")) ...

if(IrBill.validatePaymentId("billId","paymentId")...
```
<br/>

[For more information on Iran bill issuing algorithm, read the companion documentation.](https://github.com/the724/irbill/iran-bill.pdf) 
<br/>

### License

    Copyright (C) 2018  Farhad Faghihi

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.