package com.example.demo.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.annotation.HalfAlphaNumOnly;
import com.example.demo.annotation.HalfAlphaOnly;
import com.example.demo.annotation.HiraganaOnly;
import com.example.demo.annotation.KatakanaOnly;
import com.example.demo.annotation.UniqueEmail;
import com.example.demo.annotation.UniqueEmployeeNo;
import com.example.demo.annotation.ValidMidName;
import com.example.demo.annotation.ValidOldName;
import com.example.demo.annotation.ZenkakuOnly;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Valid
@ValidOldName
@ValidMidName
public class UserForm implements ValidationGroups {

    private Long id;

    @NotBlank(message = "名前(正式表示)を入力してください。")
    @ZenkakuOnly(message = "名前(正式表示)は全角で入力してください。")
    @Size(min = 1, max = 255, message = "名前(正式表示)は1文字以上、255文字以内で入力してください。")
    private String fnJp;

    @NotBlank(message = "名前(ひらがな)を入力してください。")
    @HiraganaOnly(message = "名前(ひらがな）は全角ひらがなで入力してください。")
    @Size(min = 1, max = 255, message = "名前(ひらがな)は1文字以上、255文字以内で入力してください。")
    private String fnJpHira;

    @NotBlank(message = "名前(カタカナ)を入力してください。")
    @KatakanaOnly(message = "名前(カタカナ)は全角カタカナで入力してください。")
    @Size(min = 1, max = 255, message = "名前(カタカナ)は1文字以上、255文字以内で入力してください。")
    private String fnJpKata;

    @NotBlank(message = "名前(英語)を入力してください。")
    @HalfAlphaOnly(message = "名前(英語)は半角英字で入力してください。")
    @Size(min = 1, max = 255, message = "名前(英語)は1文字以上、255文字以内で入力してください。")
    private String fnEn;

    @NotBlank(message = "姓(正式表示)を入力してください。")
    @ZenkakuOnly(message = "姓(正式表示)は全角で入力してください。")
    @Size(min = 1, max = 255, message = "姓(正式表示)は1文字以上、255文字以内で入力してください。")
    private String lnJp;

    @NotBlank(message = "姓(ひらがな)を入力してください。")
    @HiraganaOnly(message = "姓(ひらがな）は全角ひらがなで入力してください。")
    @Size(min = 1, max = 255, message = "姓(ひらがな)は1文字以上、255文字以内で入力してください。")
    private String lnJpHira;

    @NotBlank(message = "姓(カタカナ)を入力してください。")
    @KatakanaOnly(message = "姓(カタカナ)は全角カタカナで入力してください。")
    @Size(min = 1, max = 255, message = "姓(カタカナ)は1文字以上、255文字以内で入力してください。")
    private String lnJpKata;

    @NotBlank(message = "姓(英語)を入力してください。")
    @HalfAlphaOnly(message = "姓(英語)は半角英字で入力してください。")
    @Size(min = 1, max = 255, message = "姓(英語)は1文字以上、255文字以内で入力してください。")
    private String lnEn;

    // 旧姓の入力が必要な場合にチェック
    private String olnJp;
    private String olnJpHira;
    private String olnJpKata;
    private String olnEn;
    
    private String mnJp;
    private String mnJpHira;
    private String mnJpKata;
    private String mnEn;

    @NotBlank(message = "メールアドレスを入力してください。")
    @Email(message = "メールアドレスは正しい形式で入力してください。")
    @Size(min = 1, max = 255, message = "メールアドレスは1文字以上、255文字以内で入力してください。")
    @UniqueEmail
    private String email;

    @NotBlank(message = "パスワードを入力してください。")
    @HalfAlphaNumOnly(message = "パスワードは半角英数字で入力してください。")
    @Size(min = 8, max = 255, message = "パスワードは8文字以上255文字以内で入力してください。")
    private String password;

    @NotBlank(message = "社員番号を入力してください。")
    @Pattern(regexp = "^[0-9]{1,10}$", message = "社員番号は1桁以上、10桁以内で入力してください。")
    @HalfAlphaNumOnly(message = "社員番号は半角英数字で入力してください。")
    @UniqueEmployeeNo
    private String employeeNo;

    private Integer currentEmployeeNo;

    @NotNull(message = "入社日を入力してください。")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate joiningDate;

    private Boolean englishNotation;

    // 旧姓フィールドのいずれかが入力された場合は、olnJp も必須
    @AssertTrue(message = "旧姓の名前(正式表示)を入力してください。")
    public boolean isOldNameValid() {
        if ((olnJpHira != null && !olnJpHira.isEmpty()) || (olnJpKata != null && !olnJpKata.isEmpty())
                || (olnEn != null && !olnEn.isEmpty())) {
            return olnJp != null && !olnJp.isEmpty();
        }
        return true;
    }

}
