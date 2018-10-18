package io.github.the724;

import java.util.regex.Pattern;

/**
 * The type io.github.the724.Irbill.
 */
public final class Irbill {

    private Irbill(){
        throw new AssertionError();
    }

    /**
     * Parses a barcode representing a bill.
     *
     * @param barcode the barcode
     * @return the bill
     */
    public static Bill parseBillBarCode(String barcode) {

        String billId = getBillId(barcode) ;
        String payId = getPaymentId(barcode) ;

        if(validateBillId(billId) && validatePaymentId(billId,payId)) {

            BillType billType = BillType.typeOf(billId.charAt(billId.length() - 2) - '0') ;

            return new Bill()
                    .setBillId(billId)
                    .setPaymentId(payId)
                    .setType(billType)
                    .setAmount(getAmount(payId)) ;
        }

        return null ;
    }

    /**
     * Validate a bill object instance, by checking its billId and paymentId
     *
     * @param bill the bill
     * @return the boolean
     */
    public static boolean validateBill(Bill bill) {

        if(bill != null)
            return validateBillId(bill.getBillId()) && validatePaymentId(bill.getBillId(),bill.getPaymentId()) ;

        return false ;
    }

    /**
     * Validate a bill, by providing billId and paymentId
     *
     * @param billId    the bill id
     * @param paymentId the payment id
     * @return the boolean
     */
    public static boolean validateBill(String billId, String paymentId) {
        return validateBillId(billId) && validatePaymentId(billId, paymentId) ;
    }

    /**
     * Gets amount of the bill from paymentId
     *
     * @param paymentId the payment id
     * @return the amount
     */
    public static long getAmount(String paymentId) {

        if(checkAllDigits(paymentId)) {

            if (paymentId.length() < 6) {
                return 0L;
            }

            return Long.parseLong(paymentId.substring(0, paymentId.length() - 5)) * 1000;
        }

        return -1L;
    }

    private static boolean validateBillId(String billId) {

        if (checkAllDigits(billId)) {

            if ( checkIdLength(billId) && billId.charAt(billId.length() - 2) != '0') {

                int checkDigit = getCheckDigit(billId.substring(0, billId.length() - 1)) ;
                int billIdLastDigit = billId.charAt(billId.length() - 1) ;

                return (checkDigit == billIdLastDigit);
            }

            return false ;
        }

        return false ;
    }

    private static boolean validatePaymentId(String billId, String paymentId) {

        if(checkAllDigits(billId) && checkAllDigits(paymentId)) {

            if (checkIdLength(billId) && checkIdLength(paymentId)) {

                int checkDigit = getCheckDigit(billId + paymentId.substring(0, paymentId.length() - 1)) ;
                int payIdLastDigit = paymentId.charAt(paymentId.length() - 1);

                return validateBillId(billId) && checkDigit == payIdLastDigit;
            }

            return false ;
        }

        return false ;
    }

    private static boolean checkAllDigits(String expression) {

        return expression != null && Pattern.compile("\\d+").matcher(expression).matches() ;
    }

    private static boolean checkIdLength(String id) {
        return id.length() >= 6 && id.length() <= 13 ;
    }

    private static int getCheckDigit(String billId) {

        int i = 2;
        int checker = 0;

        for (int index = billId.length() - 1; index >= 0; index--) {
            checker += (billId.charAt(index) - '0') * i;
            i++;
            if (i > 7) {
                i = 2;
            }
        }
        checker = checker % 11;
        if (checker == 1 || checker == 0) {
            checker = 0;
        } else {
            checker = 11 - checker;
        }
        return checker + '0';
    }

    private static String getBillId(String barcode) {

        if (barcode != null && barcode.length() == 26) {

            return barcode.substring(0, 13);
        }

        return null;
    }

    private static String getPaymentId(String barcode) {

        if (barcode != null && barcode.length() == 26) {

            return barcode.substring(17, 26);
        }

        return null;
    }

}
