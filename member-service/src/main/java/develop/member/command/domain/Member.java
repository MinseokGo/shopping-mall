package develop.member.command.domain;

import develop.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Column(length = 16, nullable = false)
    private String name;

    @Column(length = 512, nullable = false, unique = true)
    private String email;

    @Column(length = 512, nullable = false)
    private String password;

    public static Member create(
            String name,
            String email,
            String password
    ) {
        return new Member(name, email, password);
    }
}
