package annotation;

import org.reflections.scanners.MethodAnnotationsScanner;
import sun.reflect.Reflection;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhaixt on 2018/4/4.
 */
public class AnnotationTest {

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<Integer>();
        Collections.addAll(useCases, 47, 48, 49, 50);
        trackUseCases(useCases, AnnotationUtils.class);
    }

    public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
        for (Method m : cl.getDeclaredMethods()) {
            MethodAnnotation methodAnnotation = m.getAnnotation(MethodAnnotation.class);
            if (methodAnnotation != null) {
                System.out.println("Found id:" + methodAnnotation.id() + "," + methodAnnotation.value());
                useCases.remove(new Integer(methodAnnotation.id()));
            }
        }

        for (int i : useCases) {
            System.out.println("Warning: Missing use case-" + i);
        }
    }
    final String SCAN_PACKAGE = "annotation";
//    for(Method method:new Reflections(SCAN_PACKAGE, new MethodAnnotationsScanner()){
//
//    }
}


