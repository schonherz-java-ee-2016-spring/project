package hu.schonherz.training.web;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import hu.schonherz.training.service.admin.vo.UserGroupVo;
import hu.schonherz.training.service.admin.vo.UserVo;

@FacesConverter(value = "UserGroupConverter")
public class UserGroupConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Object ret = null;
		if (component instanceof PickList) {
			Object dualList = ((PickList) component).getValue();
			DualListModel dl = (DualListModel) dualList;
			for (Object o : dl.getSource()) {
				String groupname = ((UserGroupVo) o).getGroupName();
				if (value.equals(groupname)) {
					ret = o;
					break;
				}
			}
			if (ret == null)
				for (Object o : dl.getTarget()) {
					String groupname = ((UserGroupVo) o).getGroupName();
					if (value.equals(groupname)) {
						ret = o;
						break;
					}
				}
		}
		return ret;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((UserGroupVo) value).getGroupName().toString();
	}

}
