package dds.utn.ju_ma.group7.QueMePongo.Web;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class HandlebarsViewBuilder {
	
	private String viewName;
	
	private Map<String, Object> model = new HashMap<String, Object>();
	
	public HandlebarsViewBuilder view(String view) {
		this.viewName = view;
		return this;
	}
	
	public HandlebarsViewBuilder attribute(String key, Object value) {
		this.model.put(key, value);
		return this;
	}
	
	public String render() {
		return new HandlebarsTemplateEngine().render(new ModelAndView(this.model, this.viewName));
	}

}
