package S.kento.attendancemanagementsystem.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import S.kento.attendancemanagementsystem.validation.validator.ConfirmPasswordValidator;

/**
 * @author s.kento
 * 管理者パスワード登録、更新時のアノテーションクラス
 * ConfirmPasswordValidatorに処理内容記述
 */
@Documented
@Constraint(validatedBy = {ConfirmPasswordValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfirmPassword {
	String message() default "パスワードが確認用パスワードと一致しません";

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

	@Target({ElementType.TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public @interface List{
		ConfirmPassword[] value();
	}
}
