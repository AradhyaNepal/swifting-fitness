package com.a2.swifting_fitness.common.exception;

import com.a2.swifting_fitness.common.constants.StringConstants;

public class OTPResendLimitException extends CustomException {

    public OTPResendLimitException() {
        super(StringConstants.newOTPCanOnlyBeSentAfter, null);
    }
}
