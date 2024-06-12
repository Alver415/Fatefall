package com.alver.fatefall.fx.core.filewatcher.watcher;

import java.nio.file.Path;
import java.util.Set;

public final class FileNode extends PathNode {
	public FileNode(Path path) {
		super(path, Set.of());
	}
}