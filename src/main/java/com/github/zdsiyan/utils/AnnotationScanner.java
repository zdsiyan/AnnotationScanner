package com.github.zdsiyan.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

public abstract class AnnotationScanner<A extends Annotation> {

  /**
   * scan field in object.
   * @param object
   * @param annotationClass
   */
  public void field(Object object, Class<A> annotationClass){
    Objects.requireNonNull(annotationClass);
    Objects.requireNonNull(object);
    

    Field[] fields = object.getClass().getDeclaredFields();
    
    for (Field field : fields) {
      A a = AnnotationUtils.getAnnotation(field, annotationClass);
      boolean Accessible = field.isAccessible();
      if(Accessible==false){
        field.setAccessible(true);
      }
      Object value = ReflectionUtils.getField(field, object);
      if(value==null){
        continue;
      }
      
      if(Accessible==false){
        field.setAccessible(false);
      }
      if (value != null && a!=null) {
        record(field, value, a);
        continue;
      }
      
      Class fieldClass = value.getClass();
      String className = fieldClass.getName();
      
      if(className.startsWith("java.lang")){
        continue;
      }
      if(fieldClass.isArray()){
        Arrays.stream((Object[]) value).filter(v->(v!=null)).forEach(v->field(v,annotationClass));
      }else if(Collection.class.isAssignableFrom(fieldClass)){
        ((Collection<?>)value).stream().filter(v->(v!=null)).forEach(v->field(v,annotationClass));
      }else{
        //custom type
        field(value, annotationClass);
      }
    }
  }

  protected abstract void record(Field field, Object value, A a);
}
