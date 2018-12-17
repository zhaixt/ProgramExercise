package annotation;

/**
 * Created by zhaixt on 2018/4/11.
 */
public class AnnotationUtils {
    @MethodAnnotation(id="47",value = "Passwords must contain at least one numeric")
    public boolean validatePassword(String password) {
        return (password.matches("\\w*\\d\\w*"));
    }
}
