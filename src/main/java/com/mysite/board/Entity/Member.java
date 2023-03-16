package com.mysite.board.Entity;

import com.mysite.board.role.MemberRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idx;
    
    @Column(unique=true)
    private String username;
        
    private String name;
    
    private Integer phonenum;
    
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberRole role;
    
    private String birthday;
    
    private String addr;
    
    public boolean isAdmin() {
        return MemberRole.ADMIN.equals(this.role);
    }

    public MemberRole getRole() {
        return role;
    }

    public void setRole(MemberRole role) {
        this.role = role;
    }

}
