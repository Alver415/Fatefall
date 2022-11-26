package plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class PluginManager {

	protected final Map<String, Plugin> plugins = new HashMap<>();

	public Map<String, Plugin> getPlugins(){
		return plugins;
	}

	public void loadPlugins(){
		ServiceLoader<Plugin> load = ServiceLoader.load(Plugin.class);
	}
}
