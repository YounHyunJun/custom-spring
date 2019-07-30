package me.homelus.spring.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author homelu
 * @since 2019 07 29
 */
public class SimpleBeanFactory implements BeanFactory {

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    public SimpleBeanFactory() {
        // BeanDefinition 검색/생성
    }

    /**
     * Return bean instance (shared or independent)
     */
    @Override
    public Object getBean(String name) {

        Object bean = null;

        // 1. 싱글톤 빈이 있다면 반환
        if (singletonObjects.containsKey(name)) {
            return singletonObjects.get(name);
        }

        if (!beanDefinitionMap.containsKey(name)) {
            throw new RuntimeException("No Such Bean Definition");
        }

        // 2. 빈 정의가 존재한다면 생성 준비
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition.isSingleton()) {
            // 2-1. 빈 생성 및 싱글톤 목록에 추가
            bean = createBean(name, beanDefinition);
            singletonObjects.put(name, bean);
        } else if (beanDefinition.isPrototype()) {
            // 2-2. 빈 생성
            bean = createBean(name, beanDefinition);
        } else {
            // 2-3. Scope 에 따른 빈 생성 처리
        }

        return bean;
    }

    /**
     * 1. Create bean Instance with constructor
     * 2. populateBean
     * 3. initializeBean
     */
    private Object createBean(String beanName, BeanDefinition bd) {
        RootBeanDefinition rbd = (RootBeanDefinition) bd;
        Class<?> clazz = rbd.getBeanClass();
        Constructor<?> ctor = null;
        Object bean;
        try {
            ctor = clazz.getDeclaredConstructor((Class[]) null);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("No default Constructor");
        }

        try {
            bean = ctor.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Is it an abstract class?");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Is the constructor accessible?");
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Constructor threw exception");
        }

        populateBean(beanName, bean);
        initializeBean(beanName, bean);

        return bean;
    }

    /**
     * Populate the Bean instance in the given BeanWrapper with the property values from the bean definition
     * Property Dependency Injection
     * Populate : 채우다
     */
    private void populateBean(String beanName, Object bean) {
        // 1. Type, Name 에 의한 Property 자동 주입
        // 2/ InstantiationAwareBeanPostProcessor 로 구현된 빈을 이용해 빈의 Property 주입
    }

    /**
     * Initialize the given bean instance
     */
    private void initializeBean(String beanName, Object bean) {
        // 1. Bean 이 InitializingBean 을 구현했다면 afterPropertiesSet() 실행
        // 2. Bean 이 BeanNameAware 를 구현했다면 setBeanName() 실행
        // 3. Bean 이 BeanClassLoader 를 구현했다면 setBeanClassLoader() 실행
        // 4. Bean 이 BeanFactoryAware 를 구현했다면 setBeanFactory() 실행
    }

    /**
     * if exist singletonObjects or beanDefinitionMap
     */
    @Override
    public boolean containsBean(String name) {
        return this.singletonObjects.containsKey(name) || this.beanDefinitionMap.containsKey(name);
    }

    @Override
    public boolean isSingleton(String name) {

        if (singletonObjects.containsKey(name)) {
            return true;
        }

        if (!beanDefinitionMap.containsKey(name)) {
            throw new RuntimeException("No Such Bean Definition");
        }

        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        return beanDefinition.isSingleton();
    }

    @Override
    public boolean isPrototype(String name) {
        if (!beanDefinitionMap.containsKey(name)) {
            throw new RuntimeException("No Such Bean Definition");
        }

        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        return beanDefinition.isPrototype();
    }

    @Override
    public Class<?> getType(String name) {
        if (singletonObjects.containsKey(name)) {
            return singletonObjects.get(name).getClass();
        }

        if (!beanDefinitionMap.containsKey(name)) {
            return null;
        }

        RootBeanDefinition rbd = (RootBeanDefinition) beanDefinitionMap.get(name);
        return rbd.getBeanClass();
    }

}
