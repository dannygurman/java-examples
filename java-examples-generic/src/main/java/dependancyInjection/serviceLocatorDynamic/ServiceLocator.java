package dependancyInjection.serviceLocatorDynamic;

import java.util.HashMap;
import java.util.Map;


public class ServiceLocator {

	private static ServiceLocator soleInstance;
	private Map <String,Object>services = new HashMap<String,Object> ();

	public static void load(ServiceLocator arg) {
		soleInstance = arg;
	}

	public static Object getService(String key){
		return soleInstance.services.get(key);
	}
	public void loadService (String key, Object service) {
		services.put(key, service);
	}

}
