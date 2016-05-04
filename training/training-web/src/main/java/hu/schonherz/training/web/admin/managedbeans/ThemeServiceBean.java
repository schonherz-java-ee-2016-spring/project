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
	
	//List<ThemeVo> maintypes = themeService.getThemesByType("main");
	//List<ThemeVo> itemtypes = themeService.getThemesByType("item");
   	//List<TreeNode> roots = null;
	//List<TreeNode> mains = null;
     
	
    public TreeNode createThemes() {
    	List<ThemeVo> maintypes = themeService.getThemesByType("main");
    	List<ThemeVo> itemtypes = themeService.getThemesByType("item");
       	List<TreeNode> mains = null;
    	List<TreeNode> items = null;
    	ThemeVo rootVo = new ThemeVo();
    	TreeNode root = new DefaultTreeNode(rootVo,null);
    	for (ThemeVo main : maintypes) {
			TreeNode node = new DefaultTreeNode(main, root);
			for (ThemeVo item : itemtypes) {
				if(item.getThemeCode().equals(main.getThemeCode())){
					TreeNode itemNode = new DefaultTreeNode(item, node);
				}
			}
		}


//    	TreeNode item1 = new DefaultTreeNode(maintypes.get(0), root);
//    	TreeNode item2 = new DefaultTreeNode(itemtypes.get(0),item1);
//    	TreeNode item3 = new DefaultTreeNode(itemtypes.get(1),item1);
//    	TreeNode item4 = new DefaultTreeNode(itemtypes.get(2),item1);
   

    	        
//        TreeNode documents = new DefaultTreeNode(new Document("Documents", "-", "Folder"), root);
//        TreeNode pictures = new DefaultTreeNode(new Document("Pictures", "-", "Folder"), root);
//        TreeNode movies = new DefaultTreeNode(new Document("Movies", "-", "Folder"), root);
//         
//        TreeNode work = new DefaultTreeNode(new Document("Work", "-", "Folder"), documents);
//        TreeNode primefaces = new DefaultTreeNode(new Document("PrimeFaces", "-", "Folder"), documents);
//         
//        //Documents
//        TreeNode expenses = new DefaultTreeNode("document", new Document("Expenses.doc", "30 KB", "Word Document"), work);
//        TreeNode resume = new DefaultTreeNode("document", new Document("Resume.doc", "10 KB", "Word Document"), work);
//        TreeNode refdoc = new DefaultTreeNode("document", new Document("RefDoc.pages", "40 KB", "Pages Document"), primefaces);
//         
//        //Pictures
//        TreeNode barca = new DefaultTreeNode("picture", new Document("barcelona.jpg", "30 KB", "JPEG Image"), pictures);
//        TreeNode primelogo = new DefaultTreeNode("picture", new Document("logo.jpg", "45 KB", "JPEG Image"), pictures);
//        TreeNode optimus = new DefaultTreeNode("picture", new Document("optimusprime.png", "96 KB", "PNG Image"), pictures);
//         
//        //Movies
//        TreeNode pacino = new DefaultTreeNode(new Document("Al Pacino", "-", "Folder"), movies);
//        TreeNode deniro = new DefaultTreeNode(new Document("Robert De Niro", "-", "Folder"), movies);
//         
//        TreeNode scarface = new DefaultTreeNode("mp3", new Document("Scarface", "15 GB", "Movie File"), pacino);
//        TreeNode carlitosWay = new DefaultTreeNode("mp3", new Document("Carlitos' Way", "24 GB", "Movie File"), pacino);
//         
//        TreeNode goodfellas = new DefaultTreeNode("mp3", new Document("Goodfellas", "23 GB", "Movie File"), deniro);
//        TreeNode untouchables = new DefaultTreeNode("mp3", new Document("Untouchables", "17 GB", "Movie File"), deniro);
         
        return root;
    }


	public ThemeService getThemeService() {
		return themeService;
	}



	public void setThemeService(ThemeService themeService) {
		this.themeService = themeService;
	}

}