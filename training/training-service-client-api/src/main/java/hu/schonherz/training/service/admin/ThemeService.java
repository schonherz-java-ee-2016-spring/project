package hu.schonherz.training.service.admin;

import java.util.List;

import hu.schonherz.training.service.admin.vo.ThemeVo;

public interface ThemeService {
	
	public List<ThemeVo> findAllTheme();

	public void createTheme(ThemeVo theme); 
	
	void deleteTheme( Long themeId );
	
	ThemeVo getThemeByName( String themeName );
	
	List<ThemeVo> getThemesByType( String type );
	
	ThemeVo getThemeByThemeCode( String themeCode );

	public List<ThemeVo> getThemesByThemeCode(String string);
}
