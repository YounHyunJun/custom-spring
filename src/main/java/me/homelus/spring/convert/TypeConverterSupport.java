package me.homelus.spring.convert;

/**
 * @author playjun
 * @since 2019 07 30
 */
public class TypeConverterSupport implements TypeConverter {
    @Override
    public <T> T convertIfNecessary(Object value, Class<T> requiredType) {
        return null;
    }
}
