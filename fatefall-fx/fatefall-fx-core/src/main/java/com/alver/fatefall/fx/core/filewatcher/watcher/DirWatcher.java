package com.alver.fatefall.fx.core.filewatcher.watcher;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import static java.nio.file.StandardWatchEventKinds.*;

public class DirWatcher {

	public static void main(String... args) throws IOException, InterruptedException {
		DirWatcher fileSystemWatchService = new DirWatcher();
		Path targetPath = Path.of(".wd");
		System.out.println("Starting PathWatchService for path: " + targetPath.toAbsolutePath());
		fileSystemWatchService.startWatch(targetPath);

		Thread.sleep(1000000);
	}

	private final WatchService watchService = FileSystems.getDefault().newWatchService();
	private final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
	private final BlockingQueue<WatchEvent<Path>> eventQueue = new LinkedBlockingQueue<>();

	public DirWatcher() throws IOException {
		executorService.submit(() -> {
			WatchKey key;
			while (true) {
				try {
					if ((key = watchService.take()) != null) {
						for (WatchEvent<?> watchEvent : key.pollEvents()) {
							try {
								//noinspection unchecked
								WatchEvent<Path> event = (WatchEvent<Path>) watchEvent;
								boolean offer = eventQueue.offer(event);
								if (!offer) {
									throw new RuntimeException("Failed to queue event: " + event);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						key.reset();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void startWatch(Path path) throws IOException {
		path.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY, OVERFLOW);
	}

	public BlockingQueue<WatchEvent<Path>> getEventQueue(){
		return eventQueue;
	}
}


