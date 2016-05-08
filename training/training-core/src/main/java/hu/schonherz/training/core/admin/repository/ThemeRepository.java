package hu.schonherz.training.core.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.admin.entity.Theme;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface ThemeRepository extends JpaRepository<Theme, Long> {

	Theme findByName( String themeName );
	
	List<Theme> findByType( String type );
	
	Theme findByThemeCode( String themeCode );

	@Query("SELECT t FROM Theme t WHERE t.themeCode=?1 AND type='item'")
	List<Theme> findItemThemesByThemeCode(String themeCode);

}
