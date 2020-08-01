package org.techdev.mobius.users.domain.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * En el dominio de este caso de uso es necesario añadir métodos de validación para el usuario,
 * con el fin de contrarrestar las entradas inconsistentes del usuario.
 * Con esto me refiero a algunas reglas básicas:
 *  . La existencia del perfil con su formulario completo
 *  . La existencia de una cuenta con su formulario completo
 *  . La existencia de un nombre de usuario
 *
 * OBS: Diseño petición JSON para generar los POJOS.
 *
 * Fuente: http://www.jsonschema2pojo.org
 */

public class User {

    @SerializedName("profile")
    @Expose
    private UserProfile profile;
    @SerializedName("account")
    @Expose
    private UserAccount account;
    @SerializedName("username")
    @Expose
    private String userName;

    public User(UserProfile profile, UserAccount account, String userName) {
        this.profile = profile;
        this.account = account;
        this.userName = userName;
    }

    public boolean emptyUserAccount() {
        return account == null;
    }

    public boolean emptyUserProfile() {
        return profile == null;
    }

    public boolean invalidUserName() {
        return userName == null || userName.isEmpty();
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public UserAccount getAccount() {
        return account;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
