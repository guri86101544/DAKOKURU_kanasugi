package com.example.demo.validator;

import java.util.stream.Stream;

import com.example.demo.annotation.ValidMidName;
import com.example.demo.form.UserForm;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MidNameConsistencyValidator implements ConstraintValidator<ValidMidName, UserForm> {

    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 255;

    @Override
    public boolean isValid(UserForm form, ConstraintValidatorContext context) {
        String jp = trim(form.getMnJp());
        String hira = trim(form.getMnJpHira());
        String kata = trim(form.getMnJpKata());
        String en = trim(form.getMnEn());

        boolean hasAnyMidName = Stream.of(jp, hira, kata, en).anyMatch(s -> !s.isEmpty());

        if (!hasAnyMidName) {
            return true;
        }
        boolean allFilled = Stream.of(jp, hira, kata, en).allMatch(s -> !s.isEmpty());

        context.disableDefaultConstraintViolation();
        boolean valid = true;

        // 空欄エラー
        if (jp.isEmpty()) {
            addViolation(context, "ミドルネームの各欄に一つでも入力があった場合はすべて必須です。", "mnJp");
            valid = false;
        }
        if (hira.isEmpty()) {
            addViolation(context, "ミドルネームの各欄に一つでも入力があった場合はすべて必須です。", "mnJpHira");
            valid = false;
        }
        if (kata.isEmpty()) {
            addViolation(context, "ミドルネームの各欄に一つでも入力があった場合はすべて必須です。", "mnJpKata");
            valid = false;
        }
        if (en.isEmpty()) {
            addViolation(context, "ミドルネームの各欄に一つでも入力があった場合はすべて必須です。", "mnEn");
            valid = false;
        }

        // 全角バリデーションを呼び出し、いずれかのフォームに入力ありの場合に適用
        ZenkakuValidator validatorZen = new ZenkakuValidator();
        boolean isValidZenkaku = validatorZen.isValid(jp, null);
        if (!jp.isEmpty() && !isValidZenkaku) {
            addViolation(context, "ミドルネーム(正式表示)は全角で入力してください。 ", "mnJp");
            valid = false;
        }

        HiraganaValidator validatorHira = new HiraganaValidator();
        boolean isValidHiragana = validatorHira.isValid(hira, null);
        if (!hira.isEmpty() && !isValidHiragana) {
            addViolation(context, "ミドルネーム(ひらがな)は全角ひらがなで入力してください。 ", "mnJpHira");
            valid = false;
        }

        KatakanaValidator validatorKata = new KatakanaValidator();
        boolean isValidKatakana = validatorKata.isValid(kata, null);
        if (!kata.isEmpty() && !isValidKatakana) {
            addViolation(context, "ミドルネーム(カタカナ)は全角カタカナで入力してください。 ", "mnJpKata");
            valid = false;
        }

        HalfAlphaValidator validatorHalfAlpha = new HalfAlphaValidator();
        boolean isValidHalfAlpha = validatorHalfAlpha.isValid(en, null);
        if (!en.isEmpty() && !isValidHalfAlpha) {
            addViolation(context, "ミドルネーム(英語)は半角英字で入力してください。 ", "mnEn");
            valid = false;
        }

        // 文字数制限
        if (!isLengthValid(jp)) {
            addViolation(context, "ミドルネーム(正式表示)は1文字以上、255文字以内で入力してください。", "mnJp");
            valid = false;
        }
        if (!isLengthValid(hira)) {
            addViolation(context, "ミドルネーム(全角ひらがな)は1文字以上、255文字以内で入力してください。", "mnJpHira");
            valid = false;
        }
        if (!isLengthValid(kata)) {
            addViolation(context, "ミドルネーム(全角カタカナ)は1文字以上、255文字以内で入力してください。", "mnJpKata");
            valid = false;
        }
        if (!isLengthValid(en)) {
            addViolation(context, "ミドルネーム(英語)は1文字以上、255文字以内で入力してください。", "mnEn");
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
        context.buildConstraintViolationWithTemplate(message).addPropertyNode(property).addConstraintViolation();
   }
}
