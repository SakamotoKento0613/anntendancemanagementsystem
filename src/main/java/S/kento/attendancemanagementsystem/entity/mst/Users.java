package S.kento.attendancemanagementsystem.entity.mst;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")//管理者ID
	private Integer id;
	@Column(name = "loginId")//ログインID
	private String loginId;
	@Column(name = "name")//ユーザ名
	private String name;
	@Column(name = "password")//パスワード
	private String password;
	@Column(name = "createdAt")//作成日時
	private Date createdAt;
	@Column(name = "createdAtUser")//作成者
	private Integer createdAtUser;
	@Column(name = "updatedAt")//更新日時
	private Date updatedAt;
	@Column(name = "updatedAtUser")//更新者
	private Integer updatedAtUser;
	@Column(name = "deletedAt")//削除日時
	private Date deletedAt;
	@Column(name = "deletedAtUser")//削除者
	private Integer deletedAtUser;
}
