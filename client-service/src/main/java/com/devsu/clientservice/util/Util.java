package com.devsu.clientservice.util;

import com.devsu.clientservice.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.http.HttpStatus;

import java.beans.PropertyDescriptor;

@Slf4j
public class Util {
    private Util() {
    }

    public static void copyNonNullProperties(Object dest, Object source) {
        try {
            PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(source.getClass());
            for (PropertyDescriptor descriptor : descriptors) {
                String name = descriptor.getName();
                if (descriptor.getReadMethod() != null) {
                    Object value = PropertyUtils.getProperty(source, name);
                    if (value != null && PropertyUtils.isWriteable(dest, name)) {
                        PropertyDescriptor destDescriptor = PropertyUtils.getPropertyDescriptor(dest, name);
                        if (destDescriptor != null && destDescriptor.getPropertyType().isEnum()) {
                            Object enumValue = Enum.valueOf((Class<Enum>) destDescriptor.getPropertyType(), value.toString());
                            BeanUtils.setProperty(dest, name, enumValue);
                        } else {
                            BeanUtils.setProperty(dest, name, value);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
