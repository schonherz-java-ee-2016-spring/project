package hu.schonherz.training.web.admin.managedbeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import hu.schonherz.training.service.admin.ThemeService;
import hu.schonherz.training.service.admin.vo.ThemeVo;

@ManagedBean(name = "themesService")
@ApplicationScoped
public class ThemeServiceBean {

	@EJB
	private ThemeService themeService;

	public TreeNode createThemes() {
		//get all the "main" themes
		List<ThemeVo> maintypes = themeService.getThemesByType("main");
		//get all the "item" themes
		List<ThemeVo> itemtypes = themeService.getThemesByType("item");
		//rootVo for the root TreeNode - this will not show anything
		ThemeVo rootVo = new ThemeVo();
		TreeNode root = new DefaultTreeNode(rootVo, null);
		//adding all the main types for the node TreeNode
		for (ThemeVo main : maintypes) {
			TreeNode node = new DefaultTreeNode(main, root);
			//where themeCode is equals - creates a TreeNode item for the main TreeNode
			for (ThemeVo item : itemtypes) {
				if (item.getThemeCode().equals(main.getThemeCode())) {
					TreeNode itemNode = new DefaultTreeNode(item, node);
				}
			}
		}

		return root;
	}

	public ThemeService getThemeService() {
		return themeService;
	}

	public void setThemeService(ThemeService themeService) {
		this.themeService = themeService;
	}

}