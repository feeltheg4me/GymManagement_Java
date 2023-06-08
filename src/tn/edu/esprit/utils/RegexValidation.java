/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.utils;

/**
 *
 * @author agn
 */
import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RegexValidation {
    String pattern();
    String message() default "Invalid input";

}