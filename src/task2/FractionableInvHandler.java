package task2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class FractionableInvHandler implements InvocationHandler {
    private Object obj;

    FractionableInvHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //System.out.println("It works");
        Method m = obj.getClass().getMethod(method.getName(), method.getParameterTypes());

        Annotation[] anns = m.getDeclaredAnnotations();
        /*Annotation[] anns = m.getAnnotationsByType(JustForFun.class);
        for (Annotation a: anns)
        {
            System.out.println("Ha ha ha ha ha ha ha ha ha");
        }*/
        if (Arrays.stream(anns).filter(x->((Annotation)x).annotationType().equals(Cache.class)).count()>0) {
            //Берем значение из кэша
            Field cacheField = obj.getClass().getDeclaredField("cache");
            double cache=cacheField.getDouble(obj);

            if (cache==0) {
                return method.invoke(obj, args);
            }
            else {
                return cache;
            }
        }
        return method.invoke(obj, args);
    }

}
