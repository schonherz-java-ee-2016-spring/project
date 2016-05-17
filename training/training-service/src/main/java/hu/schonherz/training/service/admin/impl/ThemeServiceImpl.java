package hu.schonherz.training.service.admin.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.training.core.admin.entity.Theme;
import hu.schonherz.training.core.admin.repository.ThemeRepository;
import hu.schonherz.training.service.admin.ThemeService;
import hu.schonherz.training.service.admin.mapper.ThemeMapper;
import hu.schonherz.training.service.admin.vo.ThemeVo;

@Stateless(mappedName = "ThemeService", name = "ThemeService")
@Transactional(value = TxType.REQUIRED)
@Local(ThemeService.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class ThemeServiceImpl implements ThemeService {
	
	Logger logger = Logger.getLogger(ThemeServiceImpl.class);

	@Autowired
	ThemeRepository themeRepository;
	
	@Override
	public List<ThemeVo> findAllTheme() {
		List<ThemeVo> themes = null;
//		try {
			themes = ThemeMapper.toVo(themeRepository.findAll());
//		} catch (Exception e) {
//			logger.error("Nem sikerült megtalálni az összes tematikát!", e);
//			e.printStackTrace();
//		}
//		if( themes == null ){
//			logger.error("ThemeServiceImpl: nem található egyetlen tematika sem!");
//		} else {
//			logger.info("ThemeServiceImpl: tematikák sikeresen lekérdezve!");
//		}
		return themes;
	}

	@Override
	public void createTheme(ThemeVo theme) {
		Theme themeDto = ThemeMapper.toDto(theme);
		logger.info("ThemeThServiceImpl: tematika mentése: " + theme.getName());
		themeRepository.save(themeDto);
	}

	@Override
	public void deleteTheme(Long themeId) {
		logger.info("ThemeThServiceImpl: tematika törlése: " + themeId);
		themeRepository.delete(themeId);
	}

	@Override
	public ThemeVo getThemeByName(String themeName) {
		// nem volt findByName a repository-ban, raktam!! BA
		
		ThemeVo theme = null;
//		try {
			theme = ThemeMapper.toVo(themeRepository.findByName(themeName));
//		} catch (Exception e) {
//			logger.error("Nem sikerült megtalálni a " + themeName + " nevű tematikát!", e);
//			e.printStackTrace();
//		}
//		if( theme == null ){
//			logger.error("ThemeServiceImpl: nem található a " + themeName + " nevű tematika!");
//		} else {
//			logger.info("ThemeServiceImpl: a " + themeName + " nevű tematika sikeresen lekérdezve!");
//		}
		return theme;

	}

	@Override
	public List<ThemeVo> getThemesByType(String type) {
		// nem volt findByType a repository-ban, raktam!! BA
		
		List<ThemeVo> themes = null;
//		try {
			themes = ThemeMapper.toVo(themeRepository.findByType(type));
//		} catch (Exception e) {
//			logger.error("Nem sikerült megtalálni az összes + " + type + " típusú tematikát!", e);
//			e.printStackTrace();
//		}
//		if( themes == null ){
//			logger.error("ThemeServiceImpl: nem található egyetlen " + type + " típusú tematika sem!");
//		} else {
//			logger.info("ThemeServiceImpl: a " + type + " típusú tematikák sikeresen lekérdezve!");
//		}
		return themes;
		
	}

	@Override
	public List<ThemeVo> getItemThemesByThemeCode(String themeCode) {
		return ThemeMapper.toVo(themeRepository.findItemThemesByThemeCode(themeCode));
	}
}
