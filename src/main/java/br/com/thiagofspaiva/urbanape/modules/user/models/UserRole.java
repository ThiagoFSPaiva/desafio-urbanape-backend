package br.com.thiagofspaiva.urbanape.modules.user.models;

public enum UserRole {
    USER("user"),
    ADMIN("admin");

    final private String role;

    UserRole(String type){
        this.role = type;
    }

    public String getRole(){
        return role;
    }
}
