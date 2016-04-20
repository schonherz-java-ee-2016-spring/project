package hu.schonherz.training.service.supervisor;

import java.util.Date;
import java.util.List;

import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.vo.HomeworkResultVo;
import hu.schonherz.training.service.supervisor.vo.HomeworkVo;

/**
 * Service for managing the results of homeworks
 * 
 * @author Mark Bohan
 *
 */
public interface HomeworkResultService {
	/**
	 * Creates a homework result
	 * 
	 * @param homeworkResultVo
	 *            the homework result
	 * @return the created homeworkResult
	 */
	public HomeworkResultVo createHomeworkResult(HomeworkResultVo homeworkResultVo);

	/**
	 * Finds and returns the result of homework by it's id
	 * 
	 * @param id
	 *            the id of homework result
	 * @return the homework result
	 */
	public HomeworkResultVo getHomeworkResultById(Long id);

	/**
	 * Returns the list of homework result belongs to user
	 * 
	 * @param userVo
	 *            the user
	 * @return the list of homeworks
	 */
	public List<HomeworkResultVo> getHomeworkResultsByUser(UserVo userVo);

	/**
	 * Returns the results of a homework.
	 * 
	 * @param homeworkVo
	 *            the homework
	 * @return the list of homework results
	 */

	public List<HomeworkResultVo> getHomeworkResultsByHomework(HomeworkVo homeworkVo);

	/**
	 * Returns list of all homework results
	 * 
	 * @return list of all homework results
	 */
	public List<HomeworkResultVo> getAllHomeworkResults();

	/**
	 * List of homework results belongs to homeworks that deadlines.
	 * 
	 * @param from
	 *            the first date
	 * @param to
	 *            the second date
	 * @return the list of homework results that has deadline between the dates
	 */
	public List<HomeworkResultVo> getHomeworkResultsBetween(Date from, Date to);

	/**
	 * Returns the list of homework results by users.
	 * 
	 * @param userVos
	 *            users
	 * @return list of homework results
	 */
	public List<HomeworkResultVo> getHomeworkResultsByUsers(List<UserVo> userVos);

}
