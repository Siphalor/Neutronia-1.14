package team.hollow.neutronia.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Config {
    String configName() default "";

    String comment() default "";

    String category() default "";

    String categoryComment() default "";

    boolean hideConfig() default false;
}
