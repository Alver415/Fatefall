package com.alver.fatefall.fx.core.interfaces;

import com.alver.fatefall.fx.core.model.EntityFX;
import com.alver.fsfx.FileSystemEntry;

public interface EntityLoader<T extends EntityFX> {

	T load(FileSystemEntry entry);
}
