/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.security;

/**
 * @author SuslovAI
 */
public class UserPrincipal {

    private String login;
    private Long userId;
    private Long userGroupId;

    public UserPrincipal() {
    }

    public UserPrincipal(String login, Long userId, Long userGroupId) {
        this.login = login;
        this.userId = userId;
        this.userGroupId = userGroupId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }


}
