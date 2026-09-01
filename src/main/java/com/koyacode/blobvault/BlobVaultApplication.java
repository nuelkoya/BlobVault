package com.koyacode.blobvault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BlobVaultApplication {

    public static void main(String[] args) {


        UserService userService = new UserService();
        User user1 = userService.createNewUser("Adetola");
        User user2 = userService.createNewUser("Eniola");
        System.out.println(user1.getUserId() + " " + user1.getName());
        System.out.println(userService.getAllUsers());
        System.out.println(userService.getUserById(user2.getUserId()));

        ApplicationContext context =
                SpringApplication.run(BlobVaultApplication.class, args);

        BlobStorageService bss =
                context.getBean(BlobStorageService.class);
        bss.testConnection();
        bss.listFiles();

        bss.uploadFile(
                user2.getUserId(),
                "AWS Certified Solutions Architect - Associate certificate.pdf"
        );


        bss.uploadFile(
                    user1.getUserId(),
                "Docker Guide.pdf"
        );

        System.out.println("User2:" + bss.getFilesForUser(user2.getUserId()));
        System.out.println("User1:" + bss.getFilesForUser(user1.getUserId()));

        //SpringApplication.run(BlobVaultApplication.class, args);
    }

}
