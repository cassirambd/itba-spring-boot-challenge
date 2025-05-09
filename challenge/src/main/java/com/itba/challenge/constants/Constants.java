package com.itba.challenge.constants;

public class Constants {

    //SERVICE
    public static final String PRODUCT_NOT_FOUND = "Product with id %d not found.";

    public static final String PRODUCT_NAME_EMPTY = "(Product name is empty)";

    public static final String PRODUCT_BRAND_EMPTY = "(Brand is empty)";

    public static final String PRODUCT_EXPIRATION_DATE_EMPTY = "(Expiration date is empty)";

    //DTO
    public static final String NAME_REQUIRED = "Product name is required";

    public static final String NAME_SIZE = "Product name must be less than 80 characters";

    public static final String BRAND_REQUIRED = "Product brand is required";

    public static final String BRAND_SIZE = "Product brand must be less than 50 characters";

    public static final String SUITABLE_REQUIRED = "Product suitability is required";

    public static final String EXPIRATION_FUTURE = "Product expiration date must be in the future";

    //CONTROLLER TEST
    public static final String STATUS_MSG = "Response status should be %d but was %d";

    public static final String BODY_NULL_MSG = "Response body should not be null";

    public static final String BODY_SIZE_MSG = "Response body should have %d products but has %d";

    public static final String BODY_NAME_MSG = "Response body should have product name %s but has %s";

}
