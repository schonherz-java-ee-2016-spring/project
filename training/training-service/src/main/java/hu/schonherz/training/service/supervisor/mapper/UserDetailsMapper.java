package hu.schonherz.training.service.supervisor.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.training.core.supervisor.entity.UserDetails;
import hu.schonherz.training.service.supervisor.vo.UserDetailsVo;

public class UserDetailsMapper {

	private static Mapper mapper = new DozerBeanMapper();

	public static UserDetailsVo toVo(UserDetails userDetailsDto) {
		if (userDetailsDto == null) {
			return null;
		}
		return mapper.map(userDetailsDto, UserDetailsVo.class);
	}

	public static UserDetails toDto(UserDetailsVo userDetailsVo) {
		if (userDetailsVo == null) {
			return null;
		}
		return mapper.map(userDetailsVo, UserDetails.class);
	}

	public static List<UserDetailsVo> toVo(List<UserDetails> userDetailsDtos) {
		List<UserDetailsVo> userDetailsVos = new ArrayList<>();
		for (UserDetails userDetails : userDetailsDtos) {
			userDetailsVos.add(toVo(userDetails));
		}
		return userDetailsVos;
	}

	public static List<UserDetails> toDto(List<UserDetailsVo> userDetailsVos) {
		List<UserDetails> userDetailsDtos = new ArrayList<>();
		for (UserDetailsVo userDetailsVo : userDetailsVos) {
			userDetailsDtos.add(toDto(userDetailsVo));
		}
		return userDetailsDtos;
	}

}
