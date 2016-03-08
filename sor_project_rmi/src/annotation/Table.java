package annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
// Annotation accessible à l'execution

@Target(ElementType.TYPE)
// Annotation associée à une classe



public @interface Table {

	String name();

}