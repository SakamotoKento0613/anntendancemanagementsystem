package S.kento.attendancemanagementsystem.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import S.kento.attendancemanagementsystem.validation.validator.UnusedEmailValidator;

/**
 * @author s.kento
 * 管理者メールアドレス登録時のアノテーション
 * UsendEmailValidatorに処理内容記述
 */
@Documented
@Constraint(validatedBy = {UnusedEmailValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UnusedEmail {
	String message() default "既に登録があるメールアドレスです";

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

	@Target({ElementType.TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public @interface List{
		UnusedEmail[] value();
	}
}
