package me.homelus.spring.convert;

import java.beans.PropertyEditor;

/**
 * @author playjun
 * @since 2019 07 30
 */
public class TypeConverterSupport extends PropertyEditorRegistrySupport implements TypeConverter {
        @Override
        public <T> T convertIfNecessary(Object value, Class<T> requiredType) {
            PropertyEditor editor = findCustomEditor(requiredType);
            editor.setValue(value);

            if (value instanceof String) {
                editor.setAsText((String) value);
            }

            return (T) editor.getValue();
        }

}
