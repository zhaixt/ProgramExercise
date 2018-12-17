//package groovy;
//
//import javax.script.Compilable;
//import javax.script.CompiledScript;
//import javax.script.ScriptEngineManager;
//import javax.script.ScriptException;
//import javax.script.SimpleBindings;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
///**
// * Created by zhaixt on 2018/4/13.
// */
//public class ScriptEngineManagerTest {
//    private static final ScriptEngineManager MANAGER = new ScriptEngineManager();
//
//    public static void main(String[] args) {
//        Compilable engine = (Compilable) MANAGER.getEngineByName("groovy");
//        final CompiledScript compiledScript;
//        try {
//            compiledScript = engine.compile("hello groovy");
//            return execute(compiledScript, params, scriptLanguage);
//        } catch (ScriptException e) {
//            throw new RuleExecutionException("Script execution failure", e);
//        }
//    }
//
//    private Map<String, Object> execute(CompiledScript compiledScript,
//                                        Map<String, Object> params,
//                                        ScriptLanguageEnum language)
//            throws ScriptException {
//
//        final SimpleBindings bindings = new SimpleBindings();
//        for (Map.Entry<String, Object> param : params.entrySet()) {
//            Object val = param.getValue();
//            bindings.put(param.getKey(), val);
//        }
//
//        compiledScript.eval(bindings);
//        /*
//        Get rid of verbose buildin values.
//        These data structures contain self-cyclic fields. And it causes Serializable problem.
//         */
//
//            return bindings;
//
//    }
//}
