package nds.frm.web;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

public class SelectedAnnotationHandlerMapping extends DefaultAnnotationHandlerMapping {

	private List<String> urls;

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public String[] getFiltered(String strs[]) {
		if (strs == null) {
			return null;
		}
		List<String> tmpList = new ArrayList<String>();
		int i = strs.length;
		for (int j = 0; j < i; j++) {
			String str = strs[j];
			for (String url : urls) {
				Pattern pattern = Pattern
						.compile(url, Pattern.CASE_INSENSITIVE);
				if (pattern.matcher(str).find()) {
					tmpList.add(str);
				}
			}
		}
		return (String[]) tmpList.toArray(new String[tmpList.size()]);
	}

	protected String[] determineUrlsForHandler(String s) {
		return getFiltered(super.determineUrlsForHandler(s));
	}
}