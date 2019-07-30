package me.homelus.spring.convert;

/**
 * @author playjun
 * @since 2019 07 30
 */
public interface TypeConverter {

    <T> T convertIfNecessary(Object value, Class<T> requiredType);

}
