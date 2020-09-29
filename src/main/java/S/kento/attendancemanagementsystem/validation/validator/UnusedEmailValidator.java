package S.kento.attendancemanagementsystem.validation.validator;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import S.kento.attendancemanagementsystem.repository.AdminUserRepository;
import S.kento.attendancemanagementsystem.service.AdminUserService;
import S.kento.attendancemanagementsystem.validation.annotation.UnusedEmail;

/**
 * @author s.kento
 *管理者メールアドレス登録時、更新時に既に使用されているメールアドレスだった場合
 *更新時にのみリクエストのメールアドレスに紐づいているIDがDBに保存されてあるIDと同じ場合処理可能になる
 */
public class UnusedEmailValidator implements ConstraintValidator<UnusedEmail, Object> {
	@Autowired
	AdminUserRepository adminUserRepository;
	@Autowired
	AdminUserService adminUserService;

	private String id;
	private String email;
	private String message;

	public void initialize(UnusedEmail constraintAnnotation) {
		id = "id";
		email = "email";
		message = constraintAnnotation.message();
	}

	public boolean isValid(Object value, ConstraintValidatorContext context) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);
		Object requestEmail = beanWrapper.getPropertyValue(email);
		Object requestId = beanWrapper.getPropertyValue(id);
		if (Objects.isNull(adminUserRepository.findByOneEmail(requestEmail))) {
			//重複なし
			return true;
		} else {
			//重複あり
			String getUseId = adminUserRepository.getId(requestEmail);
			if (requestId == null) {
				return false;
			}
			if (requestId.toString().contains(getUseId)) {
				return true;
			}
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message)
				.addPropertyNode(email).addConstraintViolation();
			return false;
		}
	}
}
