package io.github.the724;

import jdk.internal.instrumentation.ClassInstrumentation;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class IrbillTest {

    @Test(expected = InvocationTargetException.class)
    public void instantiatingIrBillThrowsAssertionError() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        Constructor<Irbill> constructor = Irbill.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void nullBillIdValidationReturnsFalse(){

    }

    @Test
    public void nullPaymentIdValidationReturnsFalse(){

    }

    @Test
    public void nullBarCodeReturnsNullBill(){

    }

    @Test
    public void correctBillIdValidationReturnsTrueTest(){

    }


}
