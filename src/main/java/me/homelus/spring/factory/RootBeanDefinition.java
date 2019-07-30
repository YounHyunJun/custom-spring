package me.homelus.spring.factory;

/**
 * @author homelus
 * @since 2019 07 29
 */
public class RootBeanDefinition implements BeanDefinition {

    private volatile Class<?> beanClass;

    private String scope = "";

    public Class<?> getBeanClass() {
        return (Class<?>) beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public String getBeanClassName() {
        return (String) getBeanClassName();
    }

    @Override
    public void setBeanClassName(String beanClassName) {
        setBeanClassName(beanClassName);
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public boolean isSingleton() {
        return SCOPE_SINGLETON.equals(scope);
    }

    @Override
    public boolean isPrototype() {
        return SCOPE_PROTOTYPE.equals(scope);
    }
}
