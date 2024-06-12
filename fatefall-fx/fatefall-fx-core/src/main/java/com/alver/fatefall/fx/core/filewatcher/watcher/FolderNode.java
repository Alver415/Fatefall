package com.alver.fatefall.fx.core.filewatcher.watcher;

import java.nio.file.Path;
import java.util.Set;

public final class FolderNode extends PathNode {
	private FolderNode(Path path, Set<PathNode> children) {
		super(path, children);
	}
}