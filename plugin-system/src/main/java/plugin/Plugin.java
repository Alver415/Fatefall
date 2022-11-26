package plugin;

public abstract class Plugin {

	protected final String name;
	protected final String version;

	protected Plugin(String name, String version){
		this.name = name;
		this.version = version;
	}

	public String getName() {
		return name;
	}
	public String getVersion() {
		return version;
	}
}
