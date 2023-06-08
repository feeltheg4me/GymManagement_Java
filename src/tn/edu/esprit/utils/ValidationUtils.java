/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.utils;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javax.xml.bind.ValidationException;

/**
 *
 * @author agn
 */
   

public class ValidationUtils {

        /**
         *
         * @param obj
         * @throws ValidationException
         */
        public static boolean validate(Object obj) throws ValidationException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(RegexValidation.class)) {
                RegexValidation annotation = field.getAnnotation(RegexValidation.class);
                String pattern = annotation.pattern();
                String message = annotation.message();

                field.setAccessible(true);

                try {
                    Object value = field.get(obj);

                    if (value != null && !matchesPattern(pattern, value.toString())) {
                          Alert alert = new Alert(Alert.AlertType.ERROR);
                          alert.setTitle("Error Message");
                          alert.setHeaderText(null);
                          alert.setContentText(message);
                          alert.showAndWait();
                          return false;
                    }
                } catch (IllegalAccessException e) {
                    throw new ValidationException("Validation error");
                }
            }
        }
        return true;
    }

    private static boolean matchesPattern(String pattern, String value) {
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(value);
        return matcher.matches();
    }
    }