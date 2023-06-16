package com.Icwd.electronic.store.validate;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator.class)
public @interface ImageNameValid {

    //error messege
    String message () default "Invalid image name!!";

    //represent group of constraints
    Class<?>[] groups() default {};

    //additional information about annotation
    Class<? extends Payload>[] payload() default {};

}
