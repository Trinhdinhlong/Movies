package com.mocktest.until;

import com.mocktest.exceptions.BadRequestException;
import com.mocktest.exceptions.MethodArgumentNotValidException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateDatabase {
    private static final String PHONE_REGEX = "\\d{10}";
    public static boolean isValidIdentityCard(String identityCard) throws MethodArgumentNotValidException, BadRequestException {
        if (identityCard == null){
            throw new BadRequestException("The identity card should not be blanked!", "BAD_REQUEST");
        }
        if(identityCard.matches("[0-9]{9,12}")){
            return true;
        }else{
            throw new MethodArgumentNotValidException("IdentityCard mustn't has _$^", "BAD_REQUEST");
        }
    }
    public static boolean isValidNumberPhone(String phone) throws BadRequestException, MethodArgumentNotValidException {
        if(phone == null){
            throw new BadRequestException("The phone should not be blanked!", "BAD_REQUEST");
        }
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            throw new MethodArgumentNotValidException("Invalid phone number", "BAD_REQUEST");
        }else {
            return true;
        }
    }

}
