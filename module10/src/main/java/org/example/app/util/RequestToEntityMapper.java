package org.example.app.util;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@UtilityClass
public class RequestToEntityMapper {

    public static <T> T mapToEntity(Map<String, String[]> parameterMap, Class<T> type) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T result = type.getConstructor().newInstance();
        for (Field declaredField : type.getDeclaredFields()) {
            if (parameterMap.containsKey(declaredField.getName())) {
                declaredField.setAccessible(true);
                declaredField.set(result, parameterMap.get(declaredField.getName())[0]);
                declaredField.setAccessible(false);
            }
        }
        return result;
    }
}
