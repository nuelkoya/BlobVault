package com.koyacode.blobvault;
import java.util.UUID;

public class User {

    private UUID userId;
    private String name;

    public User (String name) {
        this.userId = UUID.randomUUID();
        this.name = name;
    }

    /*
    public void setName(String name){
        this.name = name;
    }
    */

    public  String getName() {
        return name;
    }

    public UUID getUserId(){
        return userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }


}
