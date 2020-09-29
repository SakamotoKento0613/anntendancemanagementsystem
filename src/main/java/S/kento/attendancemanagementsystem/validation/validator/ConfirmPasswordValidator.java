package S.kento.attendancemanagementsystem.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import S.kento.attendancemanagementsystem.validation.annotation.ConfirmPassword;

/**
 * @author s.kento
 * 管理者パスワードと確認用パスワードが一致しているかのチェック
 */
public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, Object> {

		private String password;
		private String confirmPassword;
		private String message;

		public void initialize(ConfirmPassword constraintAnnotation) {
			password = "password";
			confirmPassword = "confirmPassword";
			message = constraintAnnotation.message();
		}

		public boolean isValid(Object value, ConstraintValidatorContext context) {
			BeanWrapper beanWrapper = new BeanWrapperImpl(value);
			Object requestPassword = beanWrapper.getPropertyValue(password);
			Object requestConfirmPass = beanWrapper.getPropertyValue(confirmPassword);

			if (requestPassword.toString().equals(requestConfirmPass)) {
				return true;
			}
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message)
				.addPropertyNode(password).addConstraintViolation();
			return false;
		}
}
