package com.alver.fatefall.api.base;

import org.immutables.value.Value;

@Value.Style(
        typeAbstract = "*",
        typeImmutable = "Immutable*",
        typeModifiable = "Modifiable*",
        privateNoargConstructor = true,
        visibility = Value.Style.ImplementationVisibility.PUBLIC,
        packageGenerated ="*.implementation")
public @interface StandardStyle {
}
