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
    public static Bill parseBarcode(String barcode) {

        String billId = getBillId(barcode) ;
        String payId = getPaymentId(barcode) ;

        if(validateBillId(billId) && validatePaymentId(billId,payId)) {

            BillType billType = BillType.typeOf(billId.charAt(billId.length() - 2) - '0') ;

            return new Bill()
                    .setBillId(billId)
                    .setPaymentId(removeLeadingZeroesFromPaymentId(payId))
                    .setType(billType)
                    .setAmount(getAmount(payId)) ;
        }

        return null ;
    }

    public static Bill parseBillData(String billId, String paymentId) {

        if(validateBillId(billId) && validatePaymentId(billId,paymentId)) {

            BillType billType = BillType.typeOf(billId.charAt(billId.length() - 2) - '0') ;

            return new Bill()
                    .setBillId(billId)
                    .setPaymentId(removeLeadingZeroesFromPaymentId(paymentId))
                    .setType(billType)
                    .setAmount(getAmount(paymentId)) ;
        }

        return null ;
    }

    public static boolean validateBillId(String billId) {

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

    public static boolean validatePaymentId(String billId, String paymentId) {

        if(checkAllDigits(billId) && checkAllDigits(paymentId)) {

            String formattedPaymentId = removeLeadingZeroesFromPaymentId(paymentId) ;

            if (checkIdLength(billId) && checkIdLength(formattedPaymentId)) {

                int checkDigit = getCheckDigit(billId + formattedPaymentId.substring(0, formattedPaymentId.length() - 1)) ;
                int payIdLastDigit = formattedPaymentId.charAt(formattedPaymentId.length() - 1);

                return validateBillId(billId) && checkDigit == payIdLastDigit;
            }

            return false ;
        }

        return false ;
    }

    private static long getAmount(String paymentId) {

        if(checkAllDigits(paymentId)) {

            if (paymentId.length() < 6) {
                return 0L;
            }

            return Long.parseLong(paymentId.substring(0, paymentId.length() - 5)) * 1000;
        }

        return -1L;
    }

    private static boolean checkAllDigits(String expression) {

        return expression != null && Pattern.compile("\\d+").matcher(expression).matches() ;
    }

    private static boolean checkIdLength(String id) {
        return id.length() >= 6 && id.length() <= 13 ;
    }

    private static String removeLeadingZeroesFromPaymentId(String paymentId) {
        try
        {
            return String.valueOf(Integer.valueOf(paymentId));

        } catch (Exception exc) {
            String temp = paymentId;

            while (temp.indexOf("0") == 0) {
                temp = temp.substring(1);
            }

            return temp;
        }

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
