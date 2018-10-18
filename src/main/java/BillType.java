public enum BillType {

    WATER                   (1, "\u0642\u0628\u0636\u0622\u0628", "water bill"),
    ELECTRICITY             (2, "\u0642\u0628\u0636\u0628\u0631\u0642", "electricity bill"),
    GAS                     (3, "\u0642\u0628\u0636\u06AF\u0627\u0632", "gas bill"),
    LANDLINE_PHONE          (4, "\u0642\u0628\u0636\u062A\u0644\u0641\u0646\u062B\u0627\u0628\u062A", "landline phone bill"),
    CELL_PHONE              (5, "\u0642\u0628\u0636\u062A\u0644\u0641\u0646\u0647\u0645\u0631\u0627\u0647", "cellphone bill"),
    URBAN_SERVICES          (6, "\u0642\u0628\u0636\u0639\u0648\u0627\u0631\u0636\u0634\u0647\u0631\u062F\u0627\u0631\u06CC", "urban services bill"),
    URBAN_SERVICES_ALT      (7, "\u0642\u0628\u0636\u0639\u0648\u0627\u0631\u0636\u0634\u0647\u0631\u062F\u0627\u0631\u06CC\u062C\u062F\u06CC\u062F", "urban services bill, alternative category"),
    RESERVED_FOR_PURCHASE   (8, "\u0642\u0628\u0636\u0646\u0639\u0631\u06CC\u0641\u0646\u0634\u062F\u0647 - UD", "UD bill, reserved for specific purchase scenarios"),
    TRAFFIC_TICKET          (9, "\u0642\u0628\u0636\u062C\u0631\u06CC\u0645\u0647\u062E\u0648\u062F\u0631\u0648", "traffic ticket bill"),
    UNKNOWN                 (-1,"\u0646\u0648\u0639\u0642\u0628\u0636\u0642\u0627\u0628\u0644\u062A\u0634\u062E\u06CC\u0635\u0646\u06CC\u0633\u062A!","bill type is wrong and cannot be detected !") ;

    private final int type ;
    private final String persianDescription ;
    private final String englishDescription ;

    BillType(int type, String persianDesc, String englishDesc) {
        this.type = type ;
        this.persianDescription = persianDesc ;
        this.englishDescription = englishDesc ;
    }

    public int getType() {
        return type;
    }

    public String getPersianDescription() {
        return persianDescription;
    }

    public String getEnglishDescription() {
        return englishDescription;
    }

    public BillType typeOf(int type) {

        for(BillType item : values()) {
            if(item.getType() == type)
                return item ;
        }

        return BillType.UNKNOWN ;

    }

    @Override
    public String toString() {
        return "BillType{" +
                "type=" + type +
                ", persianDescription='" + persianDescription + '\'' +
                ", englishDescription='" + englishDescription + '\'' +
                '}';
    }
}
