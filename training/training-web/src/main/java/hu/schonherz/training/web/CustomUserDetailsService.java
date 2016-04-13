package hu.schonherz.training.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hu.schonherz.training.service.UserService;
import hu.schonherz.training.vo.RoleGroupVo;
import hu.schonherz.training.vo.RoleVo;
import hu.schonherz.training.vo.UserVo;

@Service("customUserDetailsService")
@EJB(name = "UserService", beanInterface = UserService.class)
public class CustomUserDetailsService implements UserDetailsService {

	@EJB
	UserService userService;
	

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		UserVo user;
		try {
			user = userService.findUserByName(username);
			if (user == null) {
				throw new UsernameNotFoundException(username);
			}
			
			Collection<RoleGroupVo> rolegroups = user.getRoleGroups();
			
			Set<RoleVo> roles = new HashSet<>();
			for (RoleGroupVo roleGroupVo : rolegroups) {
				Collection<RoleVo> notAllRoles = roleGroupVo.getRoles();
				for (RoleVo roleVo : notAllRoles) {
					roles.add(roleVo);
				}
			}
			List<GrantedAuthority> authorities = buildUserAuthority(roles);
			return buildUserForAuthentication(user, authorities);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

	private User buildUserForAuthentication(UserVo user, List<GrantedAuthority> authorities) {
		return new User(user.getUserName(), user.getPassword(), true, true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<RoleVo> userRoles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		for (RoleVo userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getName()));
		}
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		return Result;
	}

}