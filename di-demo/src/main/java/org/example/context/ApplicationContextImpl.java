package org.example.context;

import lombok.SneakyThrows;
import org.example.annotation.Autowired;
import org.example.annotation.Component;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ApplicationContextImpl implements ApplicationContext {
    private Map<String, Object> context = new HashMap<>();

    public ApplicationContextImpl(String path) {
        Reflections reflections = new Reflections(path);
        reflections.getTypesAnnotatedWith(Component.class)
                .forEach(this::insertIntoContext);
        processAutowiring();
    }

    @Override
    public <T> Map<String, T> getAllBeans(Class<T> type) {
        return context.entrySet().stream()
                .filter(entry -> type.isAssignableFrom(entry.getValue().getClass()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> type.cast(e.getValue())));
    }

    @Override
    public <T> T getBean(Class<T> type) {
        Map<String, T> beans = getAllBeans(type);
        if (beans.size() > 1) {
            throw new RuntimeException("Multiple beans for the type: " + type.getSimpleName());
        }
        return beans.values().stream().findAny().orElseThrow();
    }

    @Override
    public <T> T getBean(Class<T> type, String name) {
        return type.cast(context.get(name));
    }

    @SneakyThrows
    public void insertIntoContext(Class<?> type) {
        String beanName = resolveBeanName(type);
        Object bean = type.getConstructor().newInstance();
        context.put(beanName, bean);
    }

    private String resolveBeanName(Class<?> type) {
        String name = type.getAnnotation(Component.class).name();
        return name.isEmpty() ? type.getSimpleName() : name;
    }

    @SneakyThrows
    private void processAutowiring() {
        for (var entry: context.entrySet()) {
            Class<?> beanType = entry.getValue().getClass();
            for (Field declaredField : beanType.getDeclaredFields()) {
                if (declaredField.isAnnotationPresent(Autowired.class)) {
                    Object bean = getBean(declaredField.getType());
                    declaredField.setAccessible(true);
                    declaredField.set(entry.getValue(), bean);
                    declaredField.setAccessible(false);
                }
            }
        }
    }
}
