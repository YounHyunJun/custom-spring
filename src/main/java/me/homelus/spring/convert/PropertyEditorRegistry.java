package me.homelus.spring.convert;

import java.beans.PropertyEditor;

/**
 * @author playjun
 * @since 2019 07 30
 */
public interface PropertyEditorRegistry {

    void registerCustomEditor(Class<?> requiredType, PropertyEditor propertyEditor);

    void registerCustomEditor(Class<?> requiredType, String propertyPath, PropertyEditor propertyEditor);

    PropertyEditor findCustomEditor(Class<?> requiredType, String propertyPath);



}
