package me.homelus.spring.convert;

import java.beans.PropertyEditor;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author playjun
 * @since 2019 07 31
 */
public class PropertyEditorRegistrySupport implements PropertyEditorRegistry {

    private Map<Class<?>, PropertyEditor> customEditors;

    @Override
    public void registerCustomEditor(Class<?> requiredType, PropertyEditor propertyEditor) {
        if (customEditors == null) {
            this.customEditors = new LinkedHashMap<>(16);
        }
        this.customEditors.put(requiredType, propertyEditor);
    }

    @Override
    public PropertyEditor findCustomEditor(Class<?> requiredType) {
        if (requiredType == null || this.customEditors == null) {
            return null;
        }
        return this.customEditors.get(requiredType);
    }
}
