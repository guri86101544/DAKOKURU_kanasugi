package com.example.demo.validator;

import java.util.stream.Stream;

import com.example.demo.annotation.ValidOldName;
import com.example.demo.form.UserForm;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OldNameConsistencyValidator implements ConstraintValidator<ValidOldName, UserForm> {

    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 255;
    
    @Override
    public boolean isValid(UserForm form, ConstraintValidatorContext context) {
        String jp = trim(form.getOlnJp());
        String hira = trim(form.getOlnJpHira());
        String kata = trim(form.getOlnJpKata());
        String en = trim(form.getOlnEn());

        boolean hasAnyOldName = Stream.of(jp, hira, kata, en).anyMatch(s -> !s.isEmpty());

        if (!hasAnyOldName) {
            return true;
        }

        boolean allFilled = Stream.of(jp, hira, kata, en).allMatch(s -> !s.isEmpty());

        context.disableDefaultConstraintViolation();
        boolean valid = true;
        
        //空欄エラー
        if (jp.isEmpty()) {
            addViolation(context, "旧姓の各欄に一つでも入力があった場合はすべて必須です。", "olnJp");
            valid = false;
        }
        if (hira.isEmpty()) {
            addViolation(context, "旧姓の各欄に一つでも入力があった場合はすべて必須です。", "olnJpHira");
            valid = false;
        }
        if (kata.isEmpty()) {
            addViolation(context, "旧姓の各欄に一つでも入力があった場合はすべて必須です。", "olnJpKata");
            valid = false;
        }
        if (en.isEmpty()) {
            addViolation(context, "旧姓の各欄に一つでも入力があった場合はすべて必須です。", "olnEn");
            valid = false;
        }
        
        //全角バリデーションを呼び出し、いずれかのフォームに入力ありの場合に適用
        ZenkakuValidator validatorZen = new ZenkakuValidator();
        boolean isValidZenkaku = validatorZen.isValid(jp, null);
        if (!jp.isEmpty() && !isValidZenkaku) {
            addViolation(context, "旧姓(正式表示)は全角で入力してください。 ", "olnJp");
            valid = false;
        }
        
        HiraganaValidator validatorHira = new HiraganaValidator();
        boolean isValidHiragana = validatorHira.isValid(hira, null);
        if (!hira.isEmpty() && !isValidHiragana) {
            addViolation(context, "旧姓(ひらがな)は全角ひらがなで入力してください。 ", "olnJpHira");
            valid = false;
        }

        KatakanaValidator validatorKata = new KatakanaValidator();
        boolean isValidKatakana = validatorKata.isValid(kata, null);
        if (!kata.isEmpty() && !isValidKatakana) {
            addViolation(context, "旧姓(カタカナ)は全角カタカナで入力してください。 ", "olnJpKata");
            valid = false;
        }
        
        HalfAlphaValidator validatorHalfAlpha = new HalfAlphaValidator();
        boolean isValidHalfAlpha = validatorHalfAlpha.isValid(en, null);
        if (!en.isEmpty() && !isValidHalfAlpha) {
            addViolation(context, "旧姓(英語)は半角英字で入力してください。 ", "olnEn");
            valid = false;
        }

        //文字数制限
        if (!isLengthValid(jp)) {
            addViolation(context, "旧姓(正式表示)は1文字以上、255文字以内で入力してください。", "olnJp");
            valid = false;
        }
        if (!isLengthValid(hira)) {
            addViolation(context, "旧姓(全角ひらがな)は1文字以上、255文字以内で入力してください。", "olnJpHira");
            valid = false;
        }
        if (!isLengthValid(kata)) {
            addViolation(context, "旧姓(全角カタカナ)は1文字以上、255文字以内で入力してください。", "olnJpKata");
            valid = false;
        }
        if (!isLengthValid(en)) {
            addViolation(context, "旧姓(英語)は1文字以上、255文字以内で入力してください。", "olnEn");
            valid = false;
        }
        return valid;
    }

    private String trim(String s) {
        return s == null ? "" : s.trim();
    }

    private boolean isLengthValid(String s) {
        return s.isEmpty() || (s.length() >= MIN_LENGTH && s.length() <= MAX_LENGTH);
    }

    private void addViolation(ConstraintValidatorContext context, String message, String property) {
        context.buildConstraintViolationWithTemplate(message).addPropertyNode(property)
                .addConstraintViolation();
    }
}