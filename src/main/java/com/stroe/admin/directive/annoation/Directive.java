/**
 * 
 */
package com.stroe.admin.directive.annoation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 模板标签注解
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年9月18日下午10:34:03
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Directive {

	String name();
}
