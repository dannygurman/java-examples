package spring.examples.profiles.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Profile;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Profile({"!dev && !test && !dockerDev"})
public @interface ProdProfile {
    String NAME = "prod";
}
