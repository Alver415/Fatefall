package com.alver.fatefall.fx.core.filewatcher.watcher;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class FileSystemWatcher {

	private final DirWatcher dirWatcher;

	public FileSystemWatcher(Path root) throws IOException {
		this.dirWatcher = new DirWatcher();
		this.dirWatcher.startWatch(root);

		Executors.newVirtualThreadPerTaskExecutor().submit(() -> {
			while (true){
				WatchEvent<Path> event = this.dirWatcher.getEventQueue().poll();
				if (ENTRY_CREATE.equals(event.kind())){

				}
			}
		});
	}

}

