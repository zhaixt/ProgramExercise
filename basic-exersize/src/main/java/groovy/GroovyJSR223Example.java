package groovy;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Created by zhaixt on 2018/4/13.
 */
public class GroovyJSR223Example {
    public static void main(String args[]) {
        try {
            ScriptEngineManager factory = new ScriptEngineManager();
            ScriptEngine engine = factory.getEngineByName("groovy");
            String HelloLanguage = "def hello(language) {return \"Hello $language\"}";
            engine.eval(HelloLanguage);
            Invocable inv = (Invocable) engine;
            Object[] params = {new String("Groovy")};
            Object result = inv.invokeFunction("hello", params);
            assert result.equals("Hello Groovy");
            System.err.println(result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
