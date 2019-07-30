package me.homelus.spring.factory;

/**
 * exclude Factory Bean, Parent Factory, RootBeanDefinition
 *
 * @author homelus
 * @since 2019 07 29
 */
public interface BeanFactory {

    Object getBean(String name);

    boolean containsBean(String name);

    boolean isSingleton(String name);

    boolean isPrototype(String name);

    Class<?> getType(String name);

}
