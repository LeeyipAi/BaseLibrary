package jmm.baselibrary.injection;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * user:Administrator
 * time:2018 05 03 17:18
 * package_name:jmm.baselibrary.injection
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface PreCompoentScope {}
