package S.kento.attendancemanagementsystem.request;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AdminUserRequest implements Serializable {
	private Integer id;
	@NotEmpty(message = "メールアドレスが空白です")
	@Size(min = 8,max = 20, message = "メールアドレスは8文字から20文字以内で入力してください。")
	private String email;//Todo ユニークキーになっているので被ったらエラーを出力するようにする
	@NotEmpty(message = "ユーザ名が空白です")
	@Size(min = 3,max = 40, message = "ユーザ名は3文字から40文字以内で入力してください。")
	private String name;
	@NotEmpty(message = "パスワードが空白です")
	@Size(min = 6,max = 30, message = "パスワードは6文字から30文字以内で入力してください。")
	private String password;
}
