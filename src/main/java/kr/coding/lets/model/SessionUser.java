package kr.coding.lets.model;
import lombok.Getter;

import java.io.Serializable;

/**
 * 직렬화 기능을 가진 User클래스
 */
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;
    private String phone;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.phone = user.getPhone();
    }
}