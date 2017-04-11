package io.reactivesw.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * application entrance.
 */
@SpringBootApplication(scanBasePackages = "io.reactivesw")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
