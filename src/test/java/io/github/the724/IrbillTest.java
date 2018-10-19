package io.github.the724;

import jdk.internal.instrumentation.ClassInstrumentation;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class IrbillTest {

    private String nullBillId = null ;
    private String nullPaymentId = null;
    private String nonNullInvalidBillId = "1" ;
    private String nonNullInvalidPaymentId = "1";
    private String nonNullValidBillId = "9403272004129";
    private String nonNullValidPaymentId = "95570163";
    private String nullBarcode = null;
    private String nonNullInvalidBarcode = "" ;
    private String nonNullValidBarcode ;

    @Test(expected = InvocationTargetException.class)
    public void instantiatingIrBillThrowsAssertionError() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        Constructor<Irbill> constructor = Irbill.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void nullBarcodeReturnsNullBill(){

    }

    @Test
    public void nonNullInvalidBarcodeReturnsNullBill(){

    }

    @Test
    public void nonNullValidBarcodeReturnValidNonNullBill(){

    }

    @Test
    public void nonNullValidBarcodeReturnsValidNotUnknownBillType(){

    }

    @Test
    public void nonNullValidBarcodeReturnsCorrectNonZeroAmountForBill(){

    }

    @Test
    public void nullBillIdReturnsNullBill(){

    }

    @Test
    public void nullPaymentIdReturnsNullBill(){

    }

    @Test
    public void nullBillIdAndNullPaymentIdReturnsNullBill(){

    }

    @Test
    public void nonNullValidBillIdWithNonNullInvalidPaymentIdReturnsNullBill(){

    }

    @Test
    public void nonNullInvalidBillIdWithNonNullValidPaymentIdReturnsNullBill(){

    }

    @Test
    public void nonNullValidBillIdWithNonNullValidPaymentIdReturnsValidNotUnknownBillType(){

    }

    @Test
    public void nonNullValidBillIdWithNonNullValidPaymentIdReturnCorrectNonZeroAmountForBill(){

    }

    @Test
    public void nullBillIdValidationReturnsFalse(){

    }

    @Test
    public void nonNullInvalidBillIdValidationReturnsFalse(){

    }

    @Test
    public void nonNullValidBillIdValidationReturnsTrue(){

    }

    @Test
    public void nullPaymentIdValidationReturnsFalse(){

    }

    @Test
    public void nonNullInvalidPaymentIdValidationReturnsFalse(){

    }

    @Test
    public void nonNullValidPaymentIdValidationReturnsTrue(){

    }


}
