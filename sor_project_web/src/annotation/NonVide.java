package annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
// Annotation accessible à l'execution

@Target(ElementType.FIELD)
// Annotation associée à un champ



public @interface NonVide {

	String mess();
	
}
