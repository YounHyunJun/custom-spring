package me.homelus.spring.factory;

/**
 * @author homelus
 * @since 2019 07 29
 */
public interface BeanDefinition {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    String getBeanClassName();

    void setBeanClassName(String beanClassName);

    String getScope();

    void setScope(String scope);

    boolean isSingleton();

    boolean isPrototype();

}
