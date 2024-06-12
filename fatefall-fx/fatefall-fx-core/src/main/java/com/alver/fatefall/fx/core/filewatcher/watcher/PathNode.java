package com.alver.fatefall.fx.core.filewatcher.watcher;

import java.nio.file.Path;
import java.util.Set;

public sealed class PathNode permits FileNode, FolderNode {

	protected PathNode(Path path, Set<PathNode> children) {
		this.path = path;
		this.children = children;
	}

	private final Path path;
	private final Set<PathNode> children;

}

