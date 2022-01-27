package managers;

public class PageObjectManager {

	private Object obj;

	public Object getDynamicPageObj(String enumConst) {
		try {
			obj = Class.forName("pageobjects." + enumConst).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
