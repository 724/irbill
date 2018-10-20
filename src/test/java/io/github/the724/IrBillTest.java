package io.github.the724;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class IrBillTest {

    private String nullBillId = null ;
    private String nullPaymentId = null;

    private String nonNullInvalidBillId = "01230" ;
    private String nonNullInvalidPaymentId = "0000982903ujkmkm";

    private String nonNullValidBillId = "9403272004129";
    private String nonNullValidPaymentId = "95570163";

    private String nonNullValidPaymentIdWithLeadingZeroes="00095570163" ;
    private String nonNullInValidPaymentIdWithLeadingZeroes = "000005254" ;

    private String nullBarcode = null;
    private String nonNullInvalidBarcode = "09283490jkklsdmfonjidfvnq2" ;
    private String nonNullValidBarcode = "10643481031150000097570237";

    @Test(expected = InvocationTargetException.class)
    public void instantiatingIrBillThrowsAssertionError() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        Constructor<IrBill> constructor = IrBill.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void nullBarcodeReturnsNullBill() {
        Assert.assertNull(IrBill.parseBarcode(nullBarcode));
    }

    @Test
    public void nonNullInvalidBarcodeReturnsNullBill(){
        Assert.assertNull(IrBill.parseBarcode(nonNullInvalidBarcode));
    }

    @Test
    public void nonNullValidBarcodeReturnsNonNullValidBill(){

        Bill bill = IrBill.parseBarcode(nonNullValidBarcode);

        Assert.assertNotNull(bill);
        Assert.assertNotNull(bill.getBillId());
        Assert.assertNotNull(bill.getPaymentId());
        Assert.assertNotEquals(0L,bill.getAmount());
        Assert.assertNotEquals(BillType.UNKNOWN,bill.getType());
        Assert.assertNotNull(bill.getType().getPersianDescription());
        Assert.assertNotNull(bill.getType().getEnglishDescription());
    }

    @Test
    public void nonNullValidBarcodeReturnsCorrectValidBillType(){

        Bill bill = IrBill.parseBarcode(nonNullValidBarcode);

        Assert.assertNotNull(bill);
        Assert.assertNotNull(bill.getBillId());
        Assert.assertNotNull(bill.getPaymentId());
        Assert.assertNotEquals(BillType.UNKNOWN,bill.getType());
        Assert.assertEquals(BillType.WATER,bill.getType());
    }

    @Test
    public void nonNullValidBarcodeReturnsCorrectNonZeroAmountForBill(){

        Bill bill = IrBill.parseBarcode(nonNullValidBarcode);
        Assert.assertNotNull(bill);
        Assert.assertNotNull(bill.getBillId());
        Assert.assertNotNull(bill.getPaymentId());
        Assert.assertNotEquals(0L,bill.getAmount());

        assert bill.getAmount() > 0L ;
    }

    @Test
    public void nullBillIdReturnsNullBill(){

        Bill bill = IrBill.parseBillData(nullBillId,nonNullInvalidPaymentId) ;
        Assert.assertNull(bill);
    }

    @Test
    public void nullPaymentIdReturnsNullBill(){

        Bill bill = IrBill.parseBillData(nonNullInvalidBillId,nullPaymentId) ;
        Assert.assertNull(bill);
    }

    @Test
    public void nullBillIdAndNullPaymentIdReturnsNullBill(){

        Bill bill = IrBill.parseBillData(nullBillId,nullPaymentId) ;
        Assert.assertNull(bill);
    }

    @Test
    public void nonNullValidBillIdWithNonNullInvalidPaymentIdReturnsNullBill() {
        Bill bill = IrBill.parseBillData(nonNullValidBillId,nonNullInvalidPaymentId) ;
        Assert.assertNull(bill);
    }

    @Test
    public void nonNullInvalidBillIdWithNonNullValidPaymentIdReturnsNullBill(){
        Bill bill = IrBill.parseBillData(nonNullInvalidBillId,nonNullValidPaymentId) ;
        Assert.assertNull(bill);
    }

    @Test
    public void nonNullValidBillIdWithNonNullValidPaymentIdReturnsValidNotUnknownBillType(){
        Bill bill = IrBill.parseBillData(nonNullValidBillId,nonNullValidPaymentId);

        Assert.assertNotNull(bill);
        Assert.assertNotNull(bill.getBillId());
        Assert.assertNotNull(bill.getPaymentId());
        Assert.assertNotEquals(0L,bill.getAmount());
        Assert.assertNotEquals(BillType.UNKNOWN,bill.getType());
        Assert.assertNotNull(bill.getType().getPersianDescription());
        Assert.assertNotNull(bill.getType().getEnglishDescription());
    }

    @Test
    public void nonNullValidBillIdWithNonNullValidPaymentIdReturnCorrectNonZeroAmountForBill(){

        Bill bill = IrBill.parseBillData(nonNullValidBillId,nonNullValidPaymentId);
        Assert.assertNotNull(bill);
        Assert.assertNotNull(bill.getBillId());
        Assert.assertNotNull(bill.getPaymentId());
        Assert.assertNotEquals(0L,bill.getAmount());

        assert bill.getAmount() > 0L ;
    }

    @Test
    public void nullBillIdValidationReturnsFalse(){
        Assert.assertFalse(IrBill.validateBillId(nullBillId));
    }

    @Test
    public void nonNullInvalidBillIdValidationReturnsFalse(){
        Assert.assertFalse(IrBill.validateBillId(nonNullInvalidBillId));
    }

    @Test
    public void nonNullValidBillIdValidationReturnsTrue(){
        Assert.assertTrue(IrBill.validateBillId(nonNullValidBillId));
    }

    @Test
    public void nullPaymentIdValidationReturnsFalse(){
        Assert.assertFalse(IrBill.validatePaymentId(nonNullValidBillId,nullPaymentId));
    }

    @Test
    public void nonNullInvalidPaymentIdValidationReturnsFalse(){
        Assert.assertFalse(IrBill.validatePaymentId(nonNullValidBillId,nonNullInvalidPaymentId));
    }

    @Test
    public void nonNullValidPaymentIdValidationReturnsTrue(){
        Assert.assertTrue(IrBill.validatePaymentId(nonNullValidBillId,nonNullValidPaymentId));
    }

    @Test
    public void nonNullInvalidPaymentIdWithLeadingZeroesValidationReturnsFalse() {
        Assert.assertFalse(IrBill.validatePaymentId(nonNullValidBillId,nonNullInValidPaymentIdWithLeadingZeroes));
    }

    @Test
    public void nonNullValidPaymentIdWithLeadingZeroesValidationReturnsTrue(){
        Assert.assertTrue(IrBill.validatePaymentId(nonNullValidBillId,nonNullValidPaymentIdWithLeadingZeroes));
    }
}
