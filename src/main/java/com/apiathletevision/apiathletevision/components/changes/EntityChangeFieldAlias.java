package com.apiathletevision.apiathletevision.components.changes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface EntityChangeFieldAlias {
    String value();
}
