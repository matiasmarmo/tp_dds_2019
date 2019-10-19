package dds.utn.ju_ma.group7.QueMePongo.Web;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AuthToken {

	@Id
	@GeneratedValue
	private Long token;

	public Long getToken() {
		return token;
	}
	
}
