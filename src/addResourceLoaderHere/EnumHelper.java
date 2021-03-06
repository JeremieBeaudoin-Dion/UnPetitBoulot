package addResourceLoaderHere;

import java.util.Random;

/**
 * A helper class to provide useful methods for enums
 *
 * @author Mia Beaudoin-Dion
 */
public class EnumHelper {

    private static Random random = new Random();

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public static String toName(String basicStringFromEnum) {

        StringBuilder name = new StringBuilder();

        for (int i = 0; i < basicStringFromEnum.length(); i++) {
            char c = basicStringFromEnum.charAt(i);

            if (Character.isUpperCase(c)) {
                name.append(" ");
                name.append(Character.toLowerCase(c));
            } else {
                name.append(c);
            }
        }

        return name.toString();

    }

    /*
     ******************************************************************************************************************
     * Test
     ******************************************************************************************************************
     */
    public static void main(String[] args) {

        System.out.println(testNames());

    }

    private static boolean testNames() {

        boolean test1 = "hello".equals(EnumHelper.toName("hello"));

        boolean test2 = "hello boy".equals(EnumHelper.toName("helloBoy"));

        boolean test3 = "hello my boy".equals(EnumHelper.toName("helloMyBoy"));

        return test1 && test2 && test3;
    }

}
