package me.homelus.spring.convert;

import org.junit.Test;

import java.beans.PropertyEditorSupport;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author playjun
 * @since 2019 07 31
 */
public class TypeConverterSupportTest {

    @Test
    public void convertIfNecessary() {

        TypeConverterSupport typeConverterSupport = new TypeConverterSupport();
        typeConverterSupport.registerCustomEditor(NewString.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(new NewString(text));
            }
        });

        NewString wow = typeConverterSupport.convertIfNecessary("wow", NewString.class);
        assertThat(wow.toString()).isEqualTo("test wow");
    }

    private static class NewString {

        public NewString(String test) {
            this.test = "test " + test;
        }

        String test = "";

        public String getTest() {
            return test;
        }

        public void setTest(String test) {
            this.test = test;
        }

        @Override
        public String toString() {
            return test;
        }
    }
}