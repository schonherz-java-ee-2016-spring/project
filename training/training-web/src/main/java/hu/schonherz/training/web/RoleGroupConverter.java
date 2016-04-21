package hu.schonherz.training.web;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import hu.schonherz.training.service.admin.vo.RoleGroupVo;

@FacesConverter(value = "RoleGroupConverter")
public class RoleGroupConverter implements Converter{
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Object ret = null;
		if (component instanceof PickList) {
			Object dualList = ((PickList) component).getValue();
			DualListModel dl = (DualListModel) dualList;
			for (Object o : dl.getSource()) {
				String username = ((RoleGroupVo) o).getName();
				if (value.equals(username)) {
					ret = o;
					break;
				}
			}
			if (ret == null)
				for (Object o : dl.getTarget()) {
					String username = ((RoleGroupVo) o).getName();
					if (value.equals(username)) {
						ret = o;
						break;
					}
				}
		}
		return ret;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((RoleGroupVo) value).getName().toString();
	}
}
