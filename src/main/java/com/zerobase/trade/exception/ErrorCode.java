package com.zerobase.trade.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    //signup
    ALREADY_REGISTER_ACCOUNT(HttpStatus.BAD_REQUEST, "이미 가입된 계정입니다."),
    ALREADY_REGISTER_EMAIL(HttpStatus.BAD_REQUEST, "이미 가입된 이메일입니다."),
    ALREADY_REGISTER_PHONE(HttpStatus.BAD_REQUEST, "이미 가입된 번호입니다."),
    WRONG_VERIFICATION(HttpStatus.BAD_REQUEST, "잘못된 인증입니다."),



    // login
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "일치하는 회원이 없습니다."),
    NOT_VALID_TOKEN(HttpStatus.BAD_REQUEST, "유효한 접근이 아닙니다."),
    NOT_MATCH_ID_PASSWORD(HttpStatus.BAD_REQUEST, "아이디나 패스워드를 확인해주세요."),

    // product

    PRODUCT_NOT_FOUND(HttpStatus.BAD_REQUEST, "제품을 찾을 수 없습니다.");



    private final HttpStatus httpStatus;
    private final String detail;
}
