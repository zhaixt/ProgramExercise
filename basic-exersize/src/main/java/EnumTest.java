import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by zhaixiaotong on 2016-4-28.
 */
public class EnumTest {
    public enum Color{Red,Yellow,Green,Blue,Black,White}
    public enum ModelTypeEnum{
        POLICY("policy"),
        RULE("rule");

        @Getter
        private String value;

        ModelTypeEnum(String value) {
            this.value = value;
        }
//
//        @Override
//        public String toString() {
//            return value;
//        }
//
//
//        public static ModelTypeEnum getEnumFromString(String text) {
//            if (text != null) {
//                for (ModelTypeEnum b : ModelTypeEnum.values()) {
//                    if (text.equalsIgnoreCase(b.value)) {
//                        return b;
//                    }
//                }
//            }
//            return null;
//        }

    }

    public enum ProductEnum {

        SPEEDLOAN("speedloan","魔借"),
        MCA("mca","银联"),
        DUOLABAO("duolabao","哆啦宝"),
        TAXLOAN("taxloan","税务贷");


        private String name;
        private String desc;


        public String getName() {
            return name;
        }

        public String getDesc() {
            return desc;
        }

        ProductEnum(String name, String desc) {
            this.name = name;
            this.desc = desc;
        }

        public static boolean hasElement(String eleName)
        {
            long elemCount = Arrays.stream(ProductEnum.values())
                    .map(productEnum -> productEnum.name)
                    .filter(t -> t.equals(eleName))
                    .count();
            return elemCount>0?true:false;

        }
    }
    public static void main(String[] args)
    {
        String a = "Green";
        System.out.println("enum Color:"+Color.Green.toString());
        System.out.println("enum:"+Color.Green.toString());
        if("Green".equals(Color.Green)) {
            System.out.println(Color.Black);//false
        }else{
            System.out.println("false");//false
        }

        String name = ProductEnum.DUOLABAO.getName();
        String desc = ProductEnum.MCA.getDesc();
        System.out.println("name:"+name);
        System.out.println("desc:"+desc);

        System.out.println("count:"+ProductEnum.hasElement("speedloa"));

        ModelTypeEnum modelTypeEnum = ModelTypeEnum.valueOf("RULE");
        System.out.println(modelTypeEnum.toString());

    }
}
