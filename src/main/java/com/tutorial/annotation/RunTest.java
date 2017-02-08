package com.tutorial.annotation;

import java.lang.reflect.Method;

/**
 * Created by kishore on 8/2/17.
 */
public class RunTest {

    public static void main(String[] args) {
        TestExample testExample = new TestExample();

        System.out.println("Testing...");

        int passed = 0, failed = 0, count = 0, ignore = 0;

        Class<? extends TestExample> clazz = testExample.getClass();
        if (clazz.isAnnotationPresent(TesterInfo.class)) {
            TesterInfo testerInfo = clazz.getAnnotation(TesterInfo.class);
            System.out.printf("%nPriority : %s", testerInfo.priority());
            System.out.printf("%nCreatedBy : %s%n", testerInfo.createdBy());
            System.out.println("Tags : ");

            int tagLength = testerInfo.tags().length;
            for (String tag : testerInfo.tags()) {
                if (tagLength > 1)
                    System.out.print(tag + ", ");
                else
                    System.out.printf(tag);
                tagLength--;
            }

            System.out.printf("%nLastModified : %s%n%n", testerInfo.lastModified());

            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Test.class)) {
                    Test test = method.getAnnotation(Test.class);
                    if (test.enabled()) {
                        try {
                            method.invoke(testExample);
                            System.out.printf("%s - Test '%s' - passed%n", ++count, method.getName());
                            passed++;
                        } catch (Throwable ex) {
                            System.out.printf("%s - Test '%s' - failed%n", ++count, method.getName());
                            failed++;
                        }
                    } else {
                        System.out.printf("%s - Test '%s' - ignored%n", ++count, method.getName());
                        ignore++;
                    }
                }
            }
            System.out.printf("%nResult : Total : %d, Passed: %d, Failed: %d, Ignored: %d%n", count, passed, failed, ignore);
        }
    }
}
