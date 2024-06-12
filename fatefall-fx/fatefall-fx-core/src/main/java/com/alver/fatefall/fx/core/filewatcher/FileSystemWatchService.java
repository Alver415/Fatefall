package com.alver.fatefall.fx.core.filewatcher;

import javafx.util.Subscription;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.WatchEvent.Kind;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static java.nio.file.StandardWatchEventKinds.*;

@SuppressWarnings("unchecked")
public class FileSystemWatchService {

	public static void main(String... args) throws IOException, InterruptedException {
		FileSystemWatchService fileSystemWatchService = new FileSystemWatchService();
		Path targetPath = Path.of(".wd");
		System.out.println("Starting PathWatchService for path: " + targetPath.toAbsolutePath());
		fileSystemWatchService.register(targetPath, (path, kind) -> System.out.println(kind + ": " + path));
		fileSystemWatchService.register(targetPath, (path, kind) -> System.err.println(kind + ": " + path));
		Thread.sleep(1000000);
	}

	private final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
	private final WatchService watchService = FileSystems.getDefault().newWatchService();
	private final Map<Path, Map<Kind<Path>, List<BiConsumer<Path, Kind<Path>>>>> pathMap = new ConcurrentHashMap<>();

	public FileSystemWatchService() throws IOException {
		Thread thread = new Thread(() -> {
			WatchKey key;
			while (true) {
				try {
					if ((key = watchService.take()) != null) {
						for (WatchEvent<?> watchEvent : key.pollEvents()) {
							WatchEvent<Path> event = (WatchEvent<Path>) watchEvent;
							Path dirPath = (Path) key.watchable();
							Path path = dirPath.resolve(event.context());
							Kind<Path> kind = event.kind();

							Optional.ofNullable(pathMap.get(dirPath))
									.map(kindMap -> kindMap.get(kind))
									.stream().flatMap(List::stream)
									.map(consumer -> (Runnable) () -> consumer.accept(path, kind))
									.forEach(executorService::submit);
						}
						key.reset();
					}
				} catch (InterruptedException e) {
					log(e);
				}
			}
		});
		thread.setDaemon(true);
		thread.start();
	}

	public Subscription register(
			Path path, BiConsumer<Path, Kind<Path>> eventConsumer) throws IOException {
		return register(path, Set.of(ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY), eventConsumer, false);
	}

	public Subscription register(
			Path path,
			Set<Kind<Path>> kinds,
			BiConsumer<Path, Kind<Path>> eventConsumer,
			boolean recursive) throws IOException {
		Subscription subscription = Subscription.EMPTY;
		for (Kind<Path> kind : kinds) {
			subscription = subscription.and(register(path, kind, eventConsumer, recursive));
		}
		return subscription;
	}

	private Subscription register(
			Path path,
			Kind<Path> kind,
			BiConsumer<Path, Kind<Path>> eventConsumer, boolean recursive) throws IOException {
		List<Subscription> subscriptions = new ArrayList<>();
		int maxDepth = recursive ? Integer.MAX_VALUE : 0;
		try (Stream<Path> stream = Files.walk(path, maxDepth)) {
			stream.filter(Files::isDirectory).forEach(rPath -> {
				try {
					subscriptions.add(register(rPath, kind, eventConsumer));
				} catch (IOException e) {
					// TODO: Batch exceptions
					throw new RuntimeException(e);
				}
			});
		}
		return () -> subscriptions.forEach(Subscription::unsubscribe);
	}

	private Subscription register(
			Path path,
			Kind<Path> kind,
			BiConsumer<Path, Kind<Path>> eventConsumer) throws IOException {
		pathMap.computeIfAbsent(path, (k) -> new ConcurrentHashMap<>())
				.computeIfAbsent(kind, (k) -> new ArrayList<>())
				.add(eventConsumer);

		WatchKey watchKey = path.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY, OVERFLOW);

		return () -> {
			Map<Kind<Path>, List<BiConsumer<Path, Kind<Path>>>> kindMap = pathMap.get(path);
			if (kindMap != null) {
				List<BiConsumer<Path, Kind<Path>>> consumerList = kindMap.get(kind);
				if (consumerList != null) {
					consumerList.remove(eventConsumer);
					if (consumerList.isEmpty()) {
						kindMap.remove(kind);
					}
				}
				if (kindMap.isEmpty()) {
					pathMap.remove(path);
					watchKey.cancel();
				}
			}
		};
	}

	private static void log(Exception e) {
		//TODO: Implement proper logging
		//noinspection CallToPrintStackTrace
		e.printStackTrace();
	}

}


